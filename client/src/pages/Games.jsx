import { Link } from 'react-router-dom'
import '../styles/games.scss'
import letteristicImg from '../../contents/letteristic.png'
import wordleImg from '../../contents/wordle.png'

export default function Games() {
  return (
    <div className="games">
      <div className="games-container">
        <h1 className="fade-in">Games</h1>
        <div className="games-grid">
          <div className="game-card fade-in-delay-1">
            <img src={letteristicImg} alt="Letteristic Game" className="game-image" />
            <h3>Letteristic</h3>
            <p>Get a random letter from the alphabet, then name cities, objects, and more starting with it. You have limited time so be fast! You get more points if you guess quickly.</p>
            <Link to="/games/letteristic" className="btn">Play Now</Link>
          </div>
          <div className="or-divider">OR</div>
          <div className="game-card fade-in-delay-2">
            <img src={wordleImg} alt="Wordle Game" className="game-image" />
            <h3>Wordle</h3>
            <p>Get a random 5-letter word and try to guess it by placing letters in the boxes. Each correctly placed letter will turn green, and if the word contains the letter but it's not well placed, it will turn yellow.</p>
            <Link to="/games/wordle" className="btn">Play Now</Link>
          </div>
        </div>
      </div>
    </div>
  )
}
