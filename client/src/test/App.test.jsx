import { describe, it, expect } from 'vitest'
import { render } from '@testing-library/react'
import App from '../App'

describe('App Component', () => {
  it('should render without crashing', () => {
    const { container } = render(<App />)
    expect(container).toBeTruthy()
  })

  it('should have app content', () => {
    const { container } = render(<App />)
    const appDiv = container.querySelector('.app')
    expect(appDiv).toBeTruthy()
  })
})
