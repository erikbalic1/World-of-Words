import { Link } from 'react-router-dom'
import '../styles/games.scss'
import letteristicImg from '../../public/contents/letteristic.png'

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
        </div>
      </div>
    </div>
  )
}
