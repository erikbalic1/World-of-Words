import '../styles/contact.scss'

export default function Contact() {
  return (
    <div className="contact">
      <div className="contact-container">
        <h1 className="fade-in">Contact Us</h1>
        <form 
          className="contact-form fade-in-delay-1"
          action="https://formsubmit.co/erikbalic1@gmail.com" 
          method="POST"
        >
          <div className="form-group">
            <label htmlFor="name">Name</label>
            <input type="text" id="name" name="name" placeholder="Your name" required />
          </div>
          <div className="form-group">
            <label htmlFor="email">Email</label>
            <input type="email" id="email" name="email" placeholder="your@email.com" required />
          </div>
          <div className="form-group">
            <label htmlFor="message">Message</label>
            <textarea id="message" name="message" rows="5" placeholder="Your message" required></textarea>
          </div>
          <button type="submit" className="btn btn-submit">Send Message</button>
        </form>
      </div>
    </div>
  )
}
