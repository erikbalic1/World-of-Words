import { describe, it, expect } from 'vitest'
import { render, screen } from '@testing-library/react'
import { BrowserRouter } from 'react-router-dom'
import { ThemeProvider } from '../contexts/ThemeContext'
import Navbar from '../components/Navbar'

describe('Navbar Component', () => {
  it('should render without crashing', () => {
    render(
      <BrowserRouter>
        <ThemeProvider>
          <Navbar />
        </ThemeProvider>
      </BrowserRouter>
    )
  })

  it('should contain navigation links', () => {
    render(
      <BrowserRouter>
        <ThemeProvider>
          <Navbar />
        </ThemeProvider>
      </BrowserRouter>
    )
    
    // Check if key navigation elements exist
    const navbar = screen.getByRole('navigation')
    expect(navbar).toBeInTheDocument()
  })
})
