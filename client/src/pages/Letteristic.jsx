import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import '../styles/letteristic.scss'

const CATEGORIES = [
  { name: 'Country', placeholder: 'Enter a country name...' },
  { name: 'City', placeholder: 'Enter a city name...' },
  { name: 'Boy Name', placeholder: 'Enter a boy\'s name...' },
  { name: 'Girl Name', placeholder: 'Enter a girl\'s name...' },
  { name: 'Animals', placeholder: 'Enter an animal name...' }
]

const GAME_TIME = 60 // seconds per round
const ALPHABET = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split('')

export default function Letteristic() {
  const navigate = useNavigate()
  const [gameState, setGameState] = useState('username') // username, intro, letterAnimation, playing, finished
  const [username, setUsername] = useState('')
  const [usernameError, setUsernameError] = useState('')
  const [usedUsernames, setUsedUsernames] = useState([]) // Simulating stored usernames
  const [animatingLetter, setAnimatingLetter] = useState('')
  const [currentLetter, setCurrentLetter] = useState('')
  const [finalLetterRevealed, setFinalLetterRevealed] = useState(false)
  const [currentCategoryIndex, setCurrentCategoryIndex] = useState(0)
  const [answers, setAnswers] = useState({})
  const [currentInput, setCurrentInput] = useState('')
  const [timeLeft, setTimeLeft] = useState(GAME_TIME)
  const [finalScore, setFinalScore] = useState(0)
  const [error, setError] = useState('')

  const getRandomLetter = () => {
    const letters = 'ABCDEFGHIJKLMNOPQRSTUVYZ'
    return letters[Math.floor(Math.random() * letters.length)]
  }

  const handleUsernameSubmit = (e) => {
    e.preventDefault()
    const trimmedUsername = username.trim()

    // Validation
    if (!trimmedUsername) {
      setUsernameError('Please enter a username!')
      return
    }

    if (trimmedUsername.length < 3) {
      setUsernameError('Username must be at least 3 characters!')
      return
    }

    if (trimmedUsername.length > 20) {
      setUsernameError('Username must be less than 20 characters!')
      return
    }

    // Check if username is already used (simulated)
    if (usedUsernames.includes(trimmedUsername.toLowerCase())) {
      setUsernameError('This username is already taken! Please choose another one.')
      return
    }

    // Store username and move to intro
    setUsedUsernames(prev => [...prev, trimmedUsername.toLowerCase()])
    setUsernameError('')
    setGameState('intro')
  }

  const startGame = () => {
    setGameState('letterAnimation')
    setFinalScore(0)
    setAnswers({})
    setCurrentCategoryIndex(0)
    setTimeLeft(GAME_TIME)
    setCurrentInput('')
    setError('')
    setFinalLetterRevealed(false)
    
    // Start letter animation
    const finalLetter = getRandomLetter()
    
    // Animate through alphabet
    let animationCount = 0
    const totalAnimations = 40 // Cycle through letters 40 times before landing
    const baseSpeed = 50 // Base ms per letter
    
    const animationInterval = setInterval(() => {
      animationCount++
      const currentIndex = animationCount % ALPHABET.length
      setAnimatingLetter(ALPHABET[currentIndex])
      
      // Gradually slow down as we approach the end
      if (animationCount >= totalAnimations - 15) {
        clearInterval(animationInterval)
        
        // Slow reveal - show each letter slower
        let slowCount = animationCount
        const slowInterval = setInterval(() => {
          slowCount++
          const slowIndex = slowCount % ALPHABET.length
          setAnimatingLetter(ALPHABET[slowIndex])
          
          if (slowCount >= totalAnimations) {
            clearInterval(slowInterval)
            // Final reveal of the chosen letter with a longer display
            setTimeout(() => {
              setAnimatingLetter(finalLetter)
              setCurrentLetter(finalLetter)
              setFinalLetterRevealed(true)
              // Keep the letter displayed for 2 seconds before moving to gameplay
              setTimeout(() => {
                setGameState('playing')
              }, 2000)
            }, 300)
          }
        }, 150) // Slower speed for final letters
      }
    }, baseSpeed)
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    const trimmedAnswer = currentInput.trim()
    if (!trimmedAnswer) {
      setError('Please enter an answer!')
      return
    }

    if (!trimmedAnswer.toUpperCase().startsWith(currentLetter)) {
      setError(`Answer must start with "${currentLetter}"!`)
      return
    }

    const category = CATEGORIES[currentCategoryIndex].name
    
    setAnswers(prev => ({
      ...prev,
      [category]: { answer: trimmedAnswer }
    }))
    
    setError('')
    setCurrentInput('')

    // Move to next category or finish game
    if (currentCategoryIndex < CATEGORIES.length - 1) {
      setCurrentCategoryIndex(prev => prev + 1)
    } else {
      /*try {
        const response = await fetch('http://localhost:8082/api/game/submit', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ 
            username: username,
            startingLetter: currentLetter,
            countryGiven: answers['Country'] ? answers['Country'].answer : null,
            countryTimeInMillis: null,
            cityGiven: answers['City'] ? answers['City'].answer : null,
            cityTimeInMillis: null,
            boyNameGiven: answers['Boy Name'] ? answers['Boy Name'].answer : null,
            boyNameTimeInMillis: null,
            girlNameGiven: answers['Girl Name'] ? answers['Girl Name'].answer : null,
            girlNameTimeInMillis: null,
            animalGiven: trimmedAnswer,
            animalTimeInMillis: null
          })
        })
        const data = await response.json()
        setFinalScore(data)
        console.log('Score submitted successfully:', data)
      } catch (error) {
        console.error('Error submitting score:', error)
        setFinalScore(0)
      }*/
      await submitAnswer()
      setGameState('finished')
    }
  }

  const submitAnswer = async () =>{
    try {
        const response = await fetch('http://localhost:8082/api/game/submit', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ 
            username: username,
            startingLetter: currentLetter,
            countryGiven: answers['Country'] ? answers['Country'].answer : null,
            countryTimeInMillis: null,
            cityGiven: answers['City'] ? answers['City'].answer : null,
            cityTimeInMillis: null,
            boyNameGiven: answers['Boy Name'] ? answers['Boy Name'].answer : null,
            boyNameTimeInMillis: null,
            girlNameGiven: answers['Girl Name'] ? answers['Girl Name'].answer : null,
            girlNameTimeInMillis: null,
            animalGiven: answers['Animals'] ? answers['Animals'].answer : null,
            animalTimeInMillis: null
          })
        })
        const data = await response.json()
        setFinalScore(data)
        console.log('Score submitted successfully:', data)
      } catch (error) {
        console.error('Error submitting score:', error)
        setFinalScore(0)
      }
  }

  const skipCategory = async () => {
    const category = CATEGORIES[currentCategoryIndex].name
    setAnswers(prev => ({
      ...prev,
      [category]: { answer: '' }
    }))
    
    setError('')
    setCurrentInput('')

    if (currentCategoryIndex < CATEGORIES.length - 1) {
      setCurrentCategoryIndex(prev => prev + 1)
    } else {
      await submitAnswer()
      setGameState('finished')
    }
  }

  // Timer effect
  useEffect(() => {
    if (gameState !== 'playing') return

    const timer = setInterval(() => {
      setTimeLeft(prev => {
        if (prev <= 1) {
          setGameState('finished')
          return 0
        }
        return prev - 1
      })
    }, 1000)

    return () => clearInterval(timer)
  }, [gameState])

  const formatTime = (seconds) => {
    const mins = Math.floor(seconds / 60)
    const secs = seconds % 60
    return `${mins}:${secs.toString().padStart(2, '0')}`
  }

  return (
    <div className="letteristic">
      <div className="letteristic-container">
        {gameState === 'username' && (
          <div className="game-username fade-in">
            <h1>Welcome to Letteristic!</h1>
            <div className="username-form-container">
              <h2>Enter Your Username</h2>
              <p className="username-hint">Choose a unique username to track your scores and compete with others!</p>
              <form onSubmit={handleUsernameSubmit} className="username-form">
                <div className="input-group">
                  <input
                    type="text"
                    value={username}
                    onChange={(e) => {
                      setUsername(e.target.value)
                      setUsernameError('')
                    }}
                    placeholder="Enter your unique username..."
                    className={`username-input ${usernameError ? 'error' : ''}`}
                    autoFocus
                    maxLength={20}
                  />
                  {usernameError && <p className="error-message">{usernameError}</p>}
                </div>
                <div className="username-requirements">
                  <p>Username requirements:</p>
                  <ul>
                    <li className={username.length >= 3 ? 'valid' : ''}>
                      At least 3 characters
                    </li>
                    <li className={username.length <= 20 ? 'valid' : ''}>
                      Maximum 20 characters
                    </li>
                    <li>Must be unique</li>
                  </ul>
                </div>
                <button type="submit" className="btn btn-continue">
                  Continue
                </button>
              </form>
              <button className="btn btn-back" onClick={() => navigate('/games')}>
                Back to Games
              </button>
            </div>
          </div>
        )}

        {gameState === 'intro' && (
          <div className="game-start fade-in">
            <h1>Hello, {username}! 👋</h1>
            <div className="game-rules">
              <h2>How to Play</h2>
              <ul>
                <li>You'll get a random letter from the alphabet</li>
                <li>Name items for each category that start with that letter</li>
                <li>You have {GAME_TIME} seconds to complete all categories</li>
                <li>Answer quickly to earn bonus points!</li>
                <li>Each correct answer gives you 10 points + time bonus</li>
              </ul>
              <h3>Categories:</h3>
              <div className="categories-preview">
                {CATEGORIES.map((cat, index) => (
                  <span key={index} className="category-badge">{cat.name}</span>
                ))}
              </div>
            </div>
            <button className="btn btn-start" onClick={startGame}>
              Start Game
            </button>
            <button className="btn btn-back" onClick={() => navigate('/games')}>
              Back to Games
            </button>
          </div>
        )}

        {gameState === 'letterAnimation' && (
          <div className="letter-animation fade-in">
            <h2>Your Letter Is...</h2>
            <div className="letter-roulette">
              <div className="roulette-letter">
                {animatingLetter}
              </div>
            </div>
            {finalLetterRevealed ? (
              <p className="animation-text final">
                <strong>Your Letter: {currentLetter}</strong>
              </p>
            ) : (
              <p className="animation-text">Selecting your letter...</p>
            )}
          </div>
        )}

        {gameState === 'playing' && (
          <div className="game-playing fade-in">
            <div className="game-header">
              <div className="game-info">
                <div className="letter-display">
                  <span className="label">Letter:</span>
                  <span className="letter">{currentLetter}</span>
                </div>
                <div className={`timer ${timeLeft <= 10 ? 'warning' : ''}`}>
                  <span className="label">Time:</span>
                  <span className="time">{formatTime(timeLeft)}</span>
                </div>
              </div>
              <div className="progress-bar">
                <div 
                  className="progress-fill"
                  style={{ width: `${((currentCategoryIndex + 1) / CATEGORIES.length) * 100}%` }}
                ></div>
              </div>
            </div>

            <div className="game-content">
              <div className="category-info">
                <h2>Category {currentCategoryIndex + 1} of {CATEGORIES.length}</h2>
                <h3 className="category-name">{CATEGORIES[currentCategoryIndex].name}</h3>
                <p className="category-hint">
                  Enter a {CATEGORIES[currentCategoryIndex].name.toLowerCase()} that starts with "{currentLetter}"
                </p>
              </div>

              <form onSubmit={handleSubmit} className="answer-form">
                <input
                  type="text"
                  value={currentInput}
                  onChange={(e) => {
                    setCurrentInput(e.target.value)
                    setError('')
                  }}
                  placeholder={CATEGORIES[currentCategoryIndex].placeholder}
                  className="answer-input"
                  autoFocus
                />
                {error && <p className="error-message">{error}</p>}
                <div className="form-actions">
                  <button type="submit" className="btn btn-submit">
                    Submit Answer
                  </button>
                  <button type="button" className="btn btn-skip" onClick={skipCategory}>
                    Skip
                  </button>
                </div>
              </form>

              <div className="answered-categories">
                <h4>Answered Categories:</h4>
                <div className="answered-list">
                  {Object.entries(answers).map(([category, data]) => (
                    <div key={category} className="answered-item">
                      <span className="category">{category}:</span>
                      <span className="answer">{data.answer}</span>
                    </div>
                  ))}
                </div>
              </div>
            </div>
          </div>
        )}

        {gameState === 'finished' && (
          <div className="game-finished fade-in">
            <h1>Game Over!</h1>
            <div className="final-score">
              <h2>Final Score</h2>
              <div className="score-value">{finalScore}</div>
              <p className="score-label">points</p>
            </div>

            <div className="results-summary">
              <h3>Your Answers (Letter: {currentLetter})</h3>
              <div className="results-list">
                {CATEGORIES.map((category, index) => {
                  const answer = answers[category.name]
                  return (
                    <div key={index} className={`result-item ${answer ? 'answered' : 'missed'}`}>
                      <div className="result-header">
                        <span className="category">{category.name}</span>
                      </div>
                      <div className="result-answer">
                        {answer ? answer.answer : 'Not answered'}
                      </div>
                    </div>
                  )
                })}
              </div>
            </div>

            <div className="game-actions">
              <button className="btn btn-play-again" onClick={startGame}>
                Play Again
              </button>
              <button className="btn btn-back" onClick={() => navigate('/games')}>
                Back to Games
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  )
}
