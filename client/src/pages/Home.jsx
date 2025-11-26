import { Link } from 'react-router-dom'
import { useState } from 'react'
import '../styles/home.scss'

export default function Home() {
  // Simulate user state - replace with actual auth context
  const [user, setUser] = useState(null) // Change to { name: 'John' } to test logged-in state

  return (
    <div className="home">
      <div className="home-content">
        <h1 className="home-title fade-in">
          {user ? (
            <>
              <span className="welcome">Welcome, {user.name}!</span>
              <span className="subtitle">to the World of Words</span>
            </>
          ) : (
            <span className="welcome-default">Welcome to the <br /> World of Words</span>
          )}
        </h1>
        <Link to="/games" className="btn btn-play fade-in-delay-1">Let's Play</Link>
      </div>
    </div>
  )
}
