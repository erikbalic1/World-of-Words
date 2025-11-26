import { useState, useEffect, useCallback } from 'react';
import { getRandomWord, isValidWord } from '../constants/wordleWords';
import '../styles/wordle.scss';

const Wordle = () => {
  const [targetWord, setTargetWord] = useState('');
  const [guesses, setGuesses] = useState(Array(6).fill(''));
  const [currentGuess, setCurrentGuess] = useState('');
  const [currentRow, setCurrentRow] = useState(0);
  const [gameOver, setGameOver] = useState(false);
  const [gameWon, setGameWon] = useState(false);
  const [message, setMessage] = useState('');
  const [username, setUsername] = useState('');
  const [isUsernameSet, setIsUsernameSet] = useState(false);
  const [totalPoints, setTotalPoints] = useState(0);
  const [_earnedPoints, setEarnedPoints] = useState(0);
  const [showLeaderboard, setShowLeaderboard] = useState(false);

  // Initialize game and load username
  useEffect(() => {
    const savedUsername = localStorage.getItem('wordleUsername');
    if (savedUsername) {
      setUsername(savedUsername);
      setIsUsernameSet(true);
      loadUserPoints(savedUsername);
    }
    startNewGame();
  }, []);

  const loadUserPoints = (user) => {
    const allScores = JSON.parse(localStorage.getItem('wordleScores') || '{}');
    const userScore = allScores[user] || 0;
    setTotalPoints(userScore);
  };

  const savePoints = (user, points) => {
    const allScores = JSON.parse(localStorage.getItem('wordleScores') || '{}');
    allScores[user] = (allScores[user] || 0) + points;
    localStorage.setItem('wordleScores', JSON.stringify(allScores));
    setTotalPoints(allScores[user]);
  };

  const calculatePoints = (guessCount) => {
    // Point system: 6 guesses max, more points for fewer guesses
    const pointsMap = {
      1: 100, // Perfect guess
      2: 80,
      3: 60,
      4: 40,
      5: 20,
      6: 10
    };
    return pointsMap[guessCount] || 0;
  };

  const handleUsernameSubmit = (e) => {
    e.preventDefault();
    if (username.trim().length >= 3) {
      localStorage.setItem('wordleUsername', username.trim());
      setIsUsernameSet(true);
      loadUserPoints(username.trim());
    } else {
      setMessage('Username must be at least 3 characters!');
      setTimeout(() => setMessage(''), 2000);
    }
  };

  const changeUsername = () => {
    setIsUsernameSet(false);
    setUsername('');
    localStorage.removeItem('wordleUsername');
  };

  const getLeaderboard = () => {
    const allScores = JSON.parse(localStorage.getItem('wordleScores') || '{}');
    return Object.entries(allScores)
      .sort(([, a], [, b]) => b - a)
      .slice(0, 10);
  };

  const startNewGame = () => {
    const word = getRandomWord();
    setTargetWord(word);
    setGuesses(Array(6).fill(''));
    setCurrentGuess('');
    setCurrentRow(0);
    setGameOver(false);
    setGameWon(false);
    setMessage('');
    setEarnedPoints(0);
  };

  const handleSubmitGuess = useCallback(() => {
    if (currentGuess.length !== 5) {
      setMessage('Word must be 5 letters!');
      setTimeout(() => setMessage(''), 2000);
      return;
    }

    if (!isValidWord(currentGuess)) {
      setMessage('Not a valid word!');
      setTimeout(() => setMessage(''), 2000);
      return;
    }

    const newGuesses = [...guesses];
    newGuesses[currentRow] = currentGuess;
    setGuesses(newGuesses);

    // Check if won
    if (currentGuess === targetWord) {
      setGameWon(true);
      setGameOver(true);
      const points = calculatePoints(currentRow + 1);
      setEarnedPoints(points);
      if (isUsernameSet) {
        savePoints(username, points);
      }
      setMessage(`🎉 Congratulations! You guessed the word! +${points} points`);
      return;
    }

    // Check if last guess
    if (currentRow === 5) {
      setGameOver(true);
      setMessage(`Game Over! The word was: ${targetWord}`);
      return;
    }

    setCurrentRow(currentRow + 1);
    setCurrentGuess('');
  }, [currentGuess, currentRow, guesses, targetWord, isUsernameSet, username]);

  // Handle keyboard input
  useEffect(() => {
    const handleKeyPress = (e) => {
      if (gameOver || !isUsernameSet) return;

      if (e.key === 'Enter') {
        handleSubmitGuess();
      } else if (e.key === 'Backspace') {
        setCurrentGuess(prev => prev.slice(0, -1));
      } else if (/^[a-zA-Z]$/.test(e.key) && currentGuess.length < 5) {
        setCurrentGuess(prev => (prev + e.key).toUpperCase());
      }
    };

    window.addEventListener('keydown', handleKeyPress);
    return () => window.removeEventListener('keydown', handleKeyPress);
  }, [currentGuess, gameOver, currentRow, handleSubmitGuess, isUsernameSet]);

  const getLetterColor = (letter, index, word) => {
    if (!word) return '';
    
    const targetLetter = targetWord[index];
    
    if (letter === targetLetter) {
      return 'correct'; // Green
    } else if (targetWord.includes(letter)) {
      return 'present'; // Yellow
    } else {
      return 'absent'; // Gray
    }
  };

  const renderGrid = () => {
    return (
      <div className="wordle-grid">
        {guesses.map((guess, rowIndex) => (
          <div key={rowIndex} className="wordle-row">
            {[0, 1, 2, 3, 4].map((colIndex) => {
              const isCurrentRow = rowIndex === currentRow;
              const letter = isCurrentRow 
                ? currentGuess[colIndex] || '' 
                : guess[colIndex] || '';
              
              const colorClass = guess 
                ? getLetterColor(guess[colIndex], colIndex, guess)
                : '';

              return (
                <div
                  key={colIndex}
                  className={`wordle-cell ${colorClass} ${letter ? 'filled' : ''}`}
                >
                  {letter}
                </div>
              );
            })}
          </div>
        ))}
      </div>
    );
  };

  const handleKeyboardClick = (key) => {
    if (gameOver) return;

    if (key === 'ENTER') {
      handleSubmitGuess();
    } else if (key === 'BACK') {
      setCurrentGuess(prev => prev.slice(0, -1));
    } else if (currentGuess.length < 5) {
      setCurrentGuess(prev => prev + key);
    }
  };

  const getKeyboardKeyColor = (key) => {
    // Check all previous guesses for this key
    for (let i = 0; i < currentRow; i++) {
      const guess = guesses[i];
      for (let j = 0; j < guess.length; j++) {
        if (guess[j] === key) {
          if (targetWord[j] === key) {
            return 'correct';
          } else if (targetWord.includes(key)) {
            return 'present';
          } else {
            return 'absent';
          }
        }
      }
    }
    return '';
  };

  const renderKeyboard = () => {
    const rows = [
      ['Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'],
      ['A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'],
      ['ENTER', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', 'BACK']
    ];

    return (
      <div className="keyboard">
        {rows.map((row, rowIndex) => (
          <div key={rowIndex} className="keyboard-row">
            {row.map((key) => (
              <button
                key={key}
                className={`keyboard-key ${getKeyboardKeyColor(key)} ${
                  key === 'ENTER' || key === 'BACK' ? 'wide' : ''
                }`}
                onClick={() => handleKeyboardClick(key)}
              >
                {key === 'BACK' ? '←' : key}
              </button>
            ))}
          </div>
        ))}
      </div>
    );
  };

  return (
    <div className="wordle-container">
      <div className="wordle-header">
        <h1>Wordle</h1>
        <p className="wordle-instructions">
          Guess the 5-letter word in 6 tries. After each guess, the color of the tiles will change to show how close your guess was to the word.
        </p>
      </div>

      {!isUsernameSet ? (
        <div className="username-modal">
          <h2>Enter Your Username</h2>
          <form onSubmit={handleUsernameSubmit}>
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              placeholder="Enter username (min 3 characters)"
              className="username-input"
              autoFocus
            />
            <button type="submit" className="username-submit-btn">
              Start Playing
            </button>
          </form>
        </div>
      ) : (
        <>
          <div className="user-stats">
            <div className="stat-item">
              <span className="stat-label">Player:</span>
              <span className="stat-value">{username}</span>
              <button className="change-username-btn" onClick={changeUsername}>
                Change
              </button>
            </div>
            <div className="stat-item">
              <span className="stat-label">Total Points:</span>
              <span className="stat-value">{totalPoints}</span>
            </div>
            <button 
              className="leaderboard-toggle-btn"
              onClick={() => setShowLeaderboard(!showLeaderboard)}
            >
              {showLeaderboard ? 'Hide' : 'Show'} Leaderboard
            </button>
          </div>

          {showLeaderboard && (
            <div className="leaderboard">
              <h3>Top 10 Players</h3>
              <div className="leaderboard-list">
                {getLeaderboard().map(([name, points], index) => (
                  <div key={name} className={`leaderboard-item ${name === username ? 'current-user' : ''}`}>
                    <span className="rank">#{index + 1}</span>
                    <span className="name">{name}</span>
                    <span className="points">{points} pts</span>
                  </div>
                ))}
              </div>
            </div>
          )}

          {message && (
            <div className={`wordle-message ${gameWon ? 'success' : gameOver ? 'error' : 'info'}`}>
              {message}
            </div>
          )}

          <div className="wordle-game">
            {renderGrid()}
            {renderKeyboard()}
          </div>

          {gameOver && (
            <div className="wordle-controls">
              <button className="new-game-btn" onClick={startNewGame}>
                Play Again
              </button>
            </div>
          )}

          <div className="wordle-legend">
            <div className="legend-item">
              <div className="legend-box correct"></div>
              <span>Correct position</span>
            </div>
            <div className="legend-item">
              <div className="legend-box present"></div>
              <span>Wrong position</span>
            </div>
            <div className="legend-item">
              <div className="legend-box absent"></div>
              <span>Not in word</span>
            </div>
          </div>
        </>
      )}
    </div>
  );
};

export default Wordle;
