# World of Words - Frontend

[![Frontend CI/CD](https://github.com/RichardHUN/worldofwords/actions/workflows/frontend-ci.yml/badge.svg)](https://github.com/RichardHUN/worldofwords/actions/workflows/frontend-ci.yml)

This is the frontend application for World of Words, built with React and Vite.

## Features

- **React 19** with modern hooks and patterns
- **Vite** for fast development and optimized builds
- **React Router** for client-side routing
- **Sass** for advanced styling
- **ESLint** for code quality
- **Vitest** for testing

## Games

- **Wordle** - Word guessing game
- **Letteristic** - Category-based word game with time tracking

## Getting Started

### Prerequisites

- Node.js 18+ (recommended: 20)
- npm or yarn

### Installation

```bash
# Install dependencies
npm install

# Start development server
npm run dev

# Build for production
npm run build

# Preview production build
npm run preview
```

## Development

### Available Scripts

- `npm run dev` - Start development server
- `npm run build` - Build for production
- `npm run lint` - Run ESLint
- `npm run preview` - Preview production build
- `npm test` - Run tests
- `npm run test:ui` - Run tests with UI
- `npm run test:coverage` - Generate coverage report

### Project Structure

```
client/
├── src/
│   ├── components/     # Reusable components
│   ├── pages/         # Page components
│   ├── styles/        # SCSS styles
│   ├── contexts/      # React contexts
│   ├── constants/     # Constants and config
│   ├── assets/        # Static assets
│   ├── test/          # Test files
│   ├── App.jsx        # Root component
│   └── main.jsx       # Entry point
├── public/            # Public assets
└── package.json       # Dependencies and scripts
```

## Testing

The project uses Vitest and React Testing Library for testing.

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

See [CI-CD-README.md](./CI-CD-README.md) for detailed CI/CD documentation.

## CI/CD Pipeline

The project includes a comprehensive CI/CD pipeline that:
- ✅ Lints code with ESLint
- ✅ Runs all tests
- ✅ Builds the application
- ✅ Performs security audits
- ✅ Checks dependencies
- ✅ Tests across multiple Node.js versions
- ✅ Creates deployment-ready artifacts

See the [CI/CD Documentation](./CI-CD-README.md) for more details.

## Code Quality

- **ESLint** - Enforces code style and catches errors
- **React Hooks Rules** - Ensures proper hook usage
- **React Refresh** - Fast refresh during development

## Browser Support

- Modern browsers (Chrome, Firefox, Safari, Edge)
- ES2020+ support required

## Contributing

1. Create a feature branch
2. Make your changes
3. Run tests: `npm test`
4. Run linter: `npm run lint`
5. Create a pull request

## License

This project is part of the World of Words application.

---

## React + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Babel](https://babeljs.io/) (or [oxc](https://oxc.rs) when used in [rolldown-vite](https://vite.dev/guide/rolldown)) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

## React Compiler

The React Compiler is not enabled on this template because of its impact on dev & build performances. To add it, see [this documentation](https://react.dev/learn/react-compiler/installation).

## Expanding the ESLint configuration

If you are developing a production application, we recommend using TypeScript with type-aware lint rules enabled. Check out the [TS template](https://github.com/vitejs/vite/tree/main/packages/create-vite/template-react-ts) for information on how to integrate TypeScript and [`typescript-eslint`](https://typescript-eslint.io) in your project.

