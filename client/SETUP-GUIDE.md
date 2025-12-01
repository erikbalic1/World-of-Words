# Quick Start Guide - Frontend CI/CD Setup

## Step 1: Install Testing Dependencies

Navigate to the client directory and install the required dependencies:

```bash
cd client
npm install --save-dev @testing-library/react @testing-library/jest-dom @testing-library/user-event vitest @vitest/ui jsdom
```

## Step 2: Verify Installation

Check that the installation was successful:

```bash
npm list --depth=0
```

You should see all testing packages listed.

## Step 3: Run Tests Locally

Test the setup by running the tests:

```bash
# Run all tests
npm test

# Run with UI (interactive)
npm run test:ui

# Generate coverage report
npm run test:coverage
```

## Step 4: Run Lint

Ensure your code passes linting:

```bash
npm run lint
```

If there are linting errors, fix them before committing.

## Step 5: Build the Application

Verify the build process works:

```bash
npm run build
```

The build output will be in the `dist/` directory.

## Step 6: Commit and Push

Commit all the new files and push to GitHub:

```bash
git add .
git commit -m "Add CI/CD pipeline and testing infrastructure"
git push origin master
```

## Step 7: Verify GitHub Actions

1. Go to your GitHub repository
2. Click on the "Actions" tab
3. You should see the "Frontend CI/CD" workflow running
4. Wait for it to complete successfully

## Step 8: View Results

Once the workflow completes:
- ✅ All tests should pass
- ✅ Linting should succeed
- ✅ Build artifacts will be available
- ✅ Security audit results will be shown

## Troubleshooting

### If tests fail:
```bash
# Run tests locally to see errors
npm test

# Check the specific failing test
npm test -- Navbar.test.jsx
```

### If build fails:
```bash
# Try building locally
npm run build

# Clear cache and reinstall
rm -rf node_modules package-lock.json
npm install
npm run build
```

### If linting fails:
```bash
# See all linting errors
npm run lint

# Some errors can be auto-fixed
npm run lint -- --fix
```

## Next Steps

1. **Add more tests** - Write tests for all your components and pages
2. **Set up deployment** - Configure automatic deployment to Vercel, Netlify, etc.
3. **Add code coverage requirements** - Set minimum coverage thresholds
4. **Enable branch protection** - Require CI to pass before merging PRs

## Common Commands

```bash
# Development
npm run dev          # Start dev server
npm test -- --watch  # Run tests in watch mode

# Before committing
npm run lint         # Check code style
npm test             # Run all tests
npm run build        # Verify build works

# CI/CD
# Just push to GitHub - the pipeline runs automatically!
```

## Files Created

- `.github/workflows/frontend-ci.yml` - GitHub Actions workflow
- `client/vitest.config.js` - Test configuration
- `client/src/test/setup.js` - Test setup file
- `client/src/test/*.test.jsx` - Example test files
- `client/CI-CD-README.md` - Detailed documentation
- `client/README.md` - Updated with badges and info

## Support

If you encounter issues:
1. Check the GitHub Actions logs for detailed error messages
2. Review `client/CI-CD-README.md` for detailed documentation
3. Run commands locally first to debug issues
4. Check that Node.js version matches (use `node -v`)

Happy coding! 🚀
