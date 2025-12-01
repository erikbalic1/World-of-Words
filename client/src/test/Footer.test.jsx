import { describe, it, expect } from 'vitest'
import { render, screen } from '@testing-library/react'
import Footer from '../components/Footer'

describe('Footer Component', () => {
  it('should render without crashing', () => {
    render(<Footer />)
  })

  it('should display footer content', () => {
    render(<Footer />)
    const footer = screen.getByRole('contentinfo')
    expect(footer).toBeInTheDocument()
  })
})
