import '../styles/footer.scss'

// helper to get current year
function getDate() {
  return new Date().getFullYear();
}

export default function Footer() {
  return (
    <footer className="footer">
      <div className="footer-content">
        <p className="copyright">Copyright ©{getDate()}. All rights reserved.</p>
        <p className="source-link">
        The source code of this website:  <br />
          <a href="https://github.com/erikbalic1/World-of-Words" target="_blank" rel="noopener noreferrer">
            World of Words GitHub Repository
          </a>
        </p>
        <p className="credits">Project created by: <br />
          <a href="https://github.com/erikbalic1" target="_blank" rel="noopener noreferrer">
            Balic Erik Gabor
          </a> <br />
          <a href="https://github.com/richardhun" target="_blank" rel="noopener noreferrer">
            Kasztl Richard
          </a> <br />
          <a href="https://github.com/thebest0622" target="_blank" rel="noopener noreferrer">
            Szabó Bence Botond
          </a>  
        </p>
      </div>
    </footer>
  )
}
