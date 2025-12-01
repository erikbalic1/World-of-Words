import { describe, it, expect, beforeEach } from 'vitest'
import { render, screen } from '@testing-library/react'
import { BrowserRouter } from 'react-router-dom'
import Letteristic from '../pages/Letteristic'

describe('Letteristic Game', () => {
  beforeEach(() => {
    render(
      <BrowserRouter>
        <Letteristic />
      </BrowserRouter>
    )
  })

  it('should render the Letteristic game component', () => {
    expect(screen.getByText(/Letteristic/i)).toBeInTheDocument()
  })

  it('should display username input on initial load', () => {
    expect(screen.getByPlaceholderText(/username/i)).toBeInTheDocument()
  })

  it('should show game instructions', () => {
    const continueButton = screen.getByRole('button', { name: /continue/i })
    expect(continueButton).toBeInTheDocument()
  })
})
