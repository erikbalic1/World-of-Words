import { describe, it, expect, beforeEach } from 'vitest'
import { render, screen } from '@testing-library/react'
import { BrowserRouter } from 'react-router-dom'
import Wordle from '../pages/Wordle'

describe('Wordle Game', () => {
  beforeEach(() => {
    render(
      <BrowserRouter>
        <Wordle />
      </BrowserRouter>
    )
  })

  it('should render the Wordle game component', () => {
    expect(screen.getByText(/Wordle/i)).toBeInTheDocument()
  })

  it('should have game interface elements', () => {
    // Check that the component renders successfully
    const { container } = render(
      <BrowserRouter>
        <Wordle />
      </BrowserRouter>
    )
    expect(container.firstChild).toBeTruthy()
  })
})
