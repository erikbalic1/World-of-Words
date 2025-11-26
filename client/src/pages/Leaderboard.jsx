import { useState, useEffect } from 'react';
import '../styles/leaderboard.scss';


const Leaderboard = () => {
  const [leaderboardData, setLeaderboardData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    loadLeaderboard();
  }, []);

  const loadLeaderboard = async () => {
    try {
      setLoading(true);
      const response = await fetch('http://localhost:8082/api/leaderboard');
      if (!response.ok) {
        throw new Error('Failed to fetch leaderboard');
      }
      const data = await response.json();
      const sorted = data.sort((a, b) => b.score - a.score);
      setLeaderboardData(sorted);
      setError(null);
    } catch (err) {
      console.error('Error loading leaderboard:', err);
      setError('Failed to load leaderboard. Please try again later.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="leaderboard-page">
      <div className="leaderboard-header">
        <h1>Letteristic Leaderboard</h1>
        <p>Top players and their highest scores</p>
      </div>

      <div className="leaderboard-container">
        {loading ? (
          <div className="no-data">
            <p>Loading leaderboard...</p>
          </div>
        ) : error ? (
          <div className="no-data error">
            <p>{error}</p>
          </div>
        ) : leaderboardData.length === 0 ? (
          <div className="no-data">
            <p>No scores yet. Play Letteristic to set a record!</p>
          </div>
        ) : (
          <table className="leaderboard-table">
            <thead>
              <tr>
                <th>Rank</th>
                <th>Player</th>
                <th>Score</th>
              </tr>
            </thead>
            <tbody>
              {leaderboardData.map((entry, index) => (
                <tr key={index} className={index < 3 ? `top-${index + 1}` : ''}>
                  <td className="rank">
                    {index === 0 && '🥇'}
                    {index === 1 && '🥈'}
                    {index === 2 && '🥉'}
                    {index > 2 && `#${index + 1}`}
                  </td>
                  <td className="player">{entry.playerName || 'Anonymous'}</td>
                  <td className="score">{entry.score}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
};

export default Leaderboard;
