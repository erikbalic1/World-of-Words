# Frontend CI/CD Pipeline

This document describes the CI/CD pipeline setup for the World of Words frontend application.

## Overview

The CI/CD pipeline is implemented using GitHub Actions and runs automatically on:
- **Push** to `master`, `main`, or `develop` branches
- **Pull Requests** to `master`, `main`, or `develop` branches
- Only when files in the `client/` directory are changed

## Pipeline Jobs

### 1. **Lint Code**
- Runs ESLint to check code quality and style
- Ensures code follows best practices
- Fails the build if linting errors are found

### 2. **Build Application**
- Builds the production-ready application
- Tests on Node.js version 20
- Uploads build artifacts for review
- Artifacts retained for 7 days

### 3. **Run Tests**
- Executes all unit tests using Vitest
- Tests React components and application logic
- Generates test reports
- Only runs if test files exist

### 4. **Security Audit**
- Runs `npm audit` to check for security vulnerabilities
- Continues even if moderate vulnerabilities are found (for information)
- Critical vulnerabilities should be addressed immediately

### 5. **Dependency Check**
- Lists all installed dependencies
- Checks for outdated packages
- Helps maintain up-to-date dependencies

### 6. **Build Matrix**
- Tests build compatibility across multiple Node.js versions (18, 20, 22)
- Ensures the application works on different environments

### 7. **Preview Build** (Pull Requests only)
- Builds the application for preview
- Comments on PRs with build status
- Helps reviewers verify the build works

### 8. **Deployment Ready** (master/main branch only)
- Creates production-ready build
- Analyzes build size
- Uploads production artifacts (retained for 30 days)
- Ready for deployment to hosting platforms

## Testing Setup

### Framework: Vitest + React Testing Library

The project uses:
- **Vitest**: Fast unit test framework for Vite projects
- **React Testing Library**: For testing React components
- **jsdom**: Browser environment simulation

### Running Tests Locally

```bash
# Run all tests
npm test

# Run tests in watch mode
npm test -- --watch

# Run tests with UI
npm test:ui

# Generate coverage report
npm test:coverage
```

### Test Files

All test files are located in `src/test/` directory:
- `App.test.jsx` - App component tests
- `Navbar.test.jsx` - Navigation component tests
- `Footer.test.jsx` - Footer component tests
- `Wordle.test.jsx` - Wordle game tests
- `Letteristic.test.jsx` - Letteristic game tests

### Writing Tests

Example test structure:

```javascript
import { describe, it, expect } from 'vitest'
import { render, screen } from '@testing-library/react'
import { BrowserRouter } from 'react-router-dom'
import YourComponent from '../components/YourComponent'

describe('YourComponent', () => {
  it('should render correctly', () => {
    render(
      <BrowserRouter>
        <YourComponent />
      </BrowserRouter>
    )
    expect(screen.getByText(/some text/i)).toBeInTheDocument()
  })
})
```

## Available Scripts

```json
{
  "dev": "vite",                    // Start development server
  "build": "vite build",            // Build for production
  "lint": "eslint .",               // Run linter
  "preview": "vite preview",        // Preview production build
  "test": "vitest",                 // Run tests
  "test:ui": "vitest --ui",         // Run tests with UI
  "test:coverage": "vitest --coverage" // Generate coverage report
}
```

## Workflow Triggers

The pipeline triggers on:
- Push to protected branches with changes in `client/**`
- Pull requests targeting protected branches with changes in `client/**`
- Manual workflow dispatch (if enabled)

## Build Artifacts

### Development Artifacts (7 days retention)
- Generated on every successful build
- Available for download from GitHub Actions

### Production Artifacts (30 days retention)
- Generated only on master/main branch
- Ready for deployment
- Includes optimized and minified code

## Deployment

The pipeline creates deployment-ready artifacts but does **not** automatically deploy. To deploy:

1. Download the production artifacts from GitHub Actions
2. Deploy to your hosting platform (Vercel, Netlify, AWS S3, etc.)

### Automated Deployment (Optional)

To add automated deployment, you can extend the `deployment-ready` job:

```yaml
- name: Deploy to Vercel
  uses: amondnet/vercel-action@v20
  with:
    vercel-token: ${{ secrets.VERCEL_TOKEN }}
    vercel-org-id: ${{ secrets.ORG_ID }}
    vercel-project-id: ${{ secrets.PROJECT_ID }}
```

## Required Dependencies

Install testing dependencies:

```bash
cd client
npm install --save-dev @testing-library/react @testing-library/jest-dom @testing-library/user-event vitest @vitest/ui jsdom
```

## Environment Variables

No environment variables are required for the CI/CD pipeline. If your application needs environment variables:

1. Add them to GitHub Secrets
2. Reference them in the workflow file:

```yaml
env:
  VITE_API_URL: ${{ secrets.API_URL }}
```

## Monitoring and Notifications

- Check GitHub Actions tab for pipeline status
- Failed builds will show detailed error logs
- PR comments include build status
- Configure GitHub notifications for build failures

## Best Practices

1. **Always run tests locally** before pushing
2. **Keep dependencies updated** regularly
3. **Fix linting errors** before committing
4. **Review security audit results** and address vulnerabilities
5. **Monitor build sizes** to prevent bloat
6. **Write tests** for new features and components

## Troubleshooting

### Build Fails on CI but Works Locally

- Ensure `package-lock.json` is committed
- Check Node.js version matches (`node -v`)
- Clear local cache and reinstall: `rm -rf node_modules package-lock.json && npm install`

### Tests Fail on CI

- Run tests locally first: `npm test`
- Check for environment-specific issues
- Verify all test dependencies are in `package.json`

### Linting Errors

- Run `npm run lint` locally
- Fix errors or configure ESLint rules
- Ensure `.eslintrc` is committed

## Support

For issues with the CI/CD pipeline:
1. Check the GitHub Actions logs
2. Review this documentation
3. Check Vitest and GitHub Actions documentation
4. Open an issue in the repository

## Version History

- **v1.0.0** - Initial CI/CD setup with linting, building, and testing
