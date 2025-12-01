# CI/CD Status Dashboard

## Build Status

| Workflow | Status | Description |
|----------|--------|-------------|
| Frontend CI/CD | [![Frontend CI/CD](https://github.com/RichardHUN/worldofwords/actions/workflows/frontend-ci.yml/badge.svg)](https://github.com/RichardHUN/worldofwords/actions/workflows/frontend-ci.yml) | Main frontend pipeline |

## Pipeline Overview

### ✅ Automated Checks

Every push and pull request runs:

1. **Code Linting** - ESLint validation
2. **Unit Tests** - Vitest + React Testing Library
3. **Build Verification** - Production build test
4. **Security Audit** - npm audit check
5. **Dependency Check** - Outdated package detection
6. **Multi-version Build** - Node 18, 20, 22 compatibility

### 📦 Artifacts

- **Development Builds**: 7-day retention
- **Production Builds**: 30-day retention (master/main only)

### 🔄 Workflow Triggers

- Push to `master`, `main`, `develop` branches
- Pull requests to protected branches
- Only runs when frontend files change

## Recent Runs

Visit the [Actions page](https://github.com/RichardHUN/worldofwords/actions) to see recent workflow runs.

## Test Coverage

Run locally to generate coverage report:

```bash
cd client
npm run test:coverage
```

## Quick Links

- [GitHub Actions Workflows](https://github.com/RichardHUN/worldofwords/actions)
- [CI/CD Documentation](./client/CI-CD-README.md)
- [Setup Guide](./client/SETUP-GUIDE.md)
- [Frontend README](./client/README.md)

## Notifications

Configure GitHub notifications to receive alerts for:
- Failed builds
- Security vulnerabilities
- Dependency updates

## Metrics to Monitor

- ✅ Build success rate
- ⏱️ Build duration
- 📊 Test coverage
- 🔒 Security vulnerabilities
- 📦 Bundle size

---

Last updated: December 2025
