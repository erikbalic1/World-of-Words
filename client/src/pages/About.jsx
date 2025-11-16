import '../styles/about.scss'

export default function About() {
  return (
    <div className="about">
      <div className="about-container">
        <h1 className="fade-in">About Us</h1>
        
        <div className="about-content fade-in-delay-1">
          <section className="project-description">
            <h2>Project Description</h2>
            <p>
              <strong>World of Words</strong> is an interactive web application created as 
              an assignment for the university. Its purpose is to help users enhance their 
              vocabulary skills through engaging games and challenges.
            </p>
            <p>
              Our goal is to make language learning enjoyable and accessible, helping users 
              expand their vocabulary in a fun and educational way.
            </p>
          </section>

          <section className="team-section">
            <h2>Meet the Team</h2>
            <div className="team-cards">
              <div className="team-card">
                <h3 className="member-name">Balic Erik Gabor</h3>
                <p className="member-role">
                  Student at University of Debrecen<br />
                  Faculty of Informatics
                </p>
                <div className="member-photo">
                  <img src="/contents/balic-erik.jpg" alt="Balic Erik Gabor" />
                </div>
                <p className="member-description">
                  Passionate about web development and creating engaging user experiences. 
                  Contributed to the frontend design and game mechanics implementation.
                </p>
                <a 
                  href="https://github.com/erikbalic1" 
                  target="_blank" 
                  rel="noopener noreferrer"
                  className="github-link"
                >
                  GitHub Profile
                </a>
              </div>

              <div className="team-card">
                <h3 className="member-name">Kasztl Richard</h3>
                <p className="member-role">
                  Student at University of Debrecen<br />
                  Faculty of Informatics
                </p>
                <div className="member-photo">
                  <img src="/contents/kasztl-richard.png" alt="Kasztl Richard" />
                </div>
                <p className="member-description">
                  Focused on backend development and database management. 
                  Implemented user authentication and game logic functionality.
                </p>
                <a 
                  href="https://github.com/richardhun" 
                  target="_blank" 
                  rel="noopener noreferrer"
                  className="github-link"
                >
                  GitHub Profile
                </a>
              </div>

              <div className="team-card">
                <h3 className="member-name">Szabó Bence Botond</h3>
                <p className="member-role">
                  Student at University of Debrecen<br />
                  Faculty of Informatics
                </p>
                <div className="member-photo">
                  <img src="/contents/szabo-bence-botond.png" alt="Szabó Bence Botond" />
                </div>
                <p className="member-description">
                  Specialized in full-stack development and API integration. 
                  Developed the scoring system and leaderboard features.
                </p>
                <a 
                  href="https://github.com/thebest0622" 
                  target="_blank" 
                  rel="noopener noreferrer"
                  className="github-link"
                >
                  GitHub Profile
                </a>
              </div>
            </div>
          </section>
        </div>
      </div>
    </div>
  )
}
