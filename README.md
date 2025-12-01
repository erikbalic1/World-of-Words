# World of Words

## Overview
World of Words is a full-stack web application featuring word games inspired by the Hungarian "Ország-Város" (Country-City) game. The project combines a Java Spring Boot backend with a modern React frontend, offering both web-based gameplay and CLI support.

## Features

### Games
- **Letteristic** - A timed category-based word game where players must provide words for 5 categories (Boy Names, Girl Names, Countries, Cities, Animals) that start with a randomly generated letter. The game tracks response times in milliseconds and validates answers against the database.

### Pages
- **Home** - Landing page with game overview
- **Games** - Game selection menu
- **Leaderboard** - Rankings and high scores
- **About** - Information about the project and team
- **Contact** - Contact information

### Technical Features
- Light/Dark theme support with persistent user preferences
- Responsive design with SCSS styling
- Time tracking with millisecond precision for Letteristic game
- RESTful API for game validation and leaderboard management
- Comprehensive test coverage with Vitest and React Testing Library
- CI/CD pipeline with GitHub Actions (automated testing, linting, security audits)

## Tech Stack

### Backend
- Java 17+ (21 recommended for development)
- Spring Boot
- Maven 3.6+
- JPA/Hibernate for data persistence

### Frontend
- React 19.2.0
- React Router 7.9.4
- Vite 7.1.12
- Sass 1.94.0
- Vitest 2.1.9 (testing)

## Prerequisites
- **Backend**: Java 17+ (21 recommended), Maven 3.6+, IntelliJ IDEA (optional)
- **Frontend**: Node.js 18+ (22 recommended), npm or yarn
- Git (optional)

---

## Run Locally

### Backend Setup

1. Navigate to the project root directory
2. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```
   Or run `WorldofwordsApplication.java` from your IDE
3. Backend will start on `http://localhost:8082`

### Frontend Setup

1. Navigate to the client directory:
   ```bash
   cd client
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm run dev
   ```
4. Frontend will start on `http://localhost:5173`

### CLI Game (Optional)

Run the terminal version of the game:
```bash
# From IDE: Run gameReading.Main class
# Or use Maven to execute the class
```

---

## API Endpoints

### Game Validation
- `POST /api/game/submit` - Submit and validate game answers
- `GET /api/leaderboard` - Retrieve leaderboard entries

### Testing Endpoints
- `GET /testCountries` - List all countries
- `GET /testCities` - List all cities
- `GET /testBoyNames` - List all boy names
- `GET /testGirlNames` - List all girl names
- `GET /testAnimals` - List all animals
- `GET /testCountry?country=...` - Validate a country
- `GET /testCity?city=...` - Validate a city
- `GET /testBoyName?boyName=...` - Validate a boy name
- `GET /testGirlName?girlName=...` - Validate a girl name
- `GET /testAnimal?animal=...` - Validate an animal

---

## Development

### Frontend Commands
```bash
npm run dev          # Start development server
npm run build        # Build for production
npm run preview      # Preview production build
npm run lint         # Run ESLint
npm test             # Run tests
npm run test:ui      # Run tests with UI
npm run test:coverage # Run tests with coverage
```

### Running Tests
The project includes comprehensive test coverage:
- Component tests for App, Navbar, Footer
- Page tests for Wordle and Letteristic games
- All tests use Vitest with React Testing Library

### CI/CD
The project uses GitHub Actions for continuous integration:
- Automated linting and code quality checks
- Unit and integration testing
- Security audits and dependency checks
- Multi-version Node.js testing (18, 20, 22)

---

## Configuration

### Backend
Application properties in `src/main/resources/application.properties`:
- Server port (default: 8082)
- Database configuration
- CORS settings

### Frontend
Vite configuration in `client/vite.config.js`:
- Development server settings
- Build output configuration
- Proxy setup for API calls

---

## Project Structure

```
worldofwords/
├── src/main/java/                 # Backend Java source code
│   └── hu/unideb/inf/worldofwords/
│       ├── model/                 # Entity classes
│       ├── repository/            # Data access layer
│       ├── service/               # Business logic
│       ├── web/                   # REST controllers
│       └── gameReading/           # CLI game logic
├── client/                        # Frontend React application
│   ├── src/
│   │   ├── components/            # Reusable components
│   │   ├── pages/                 # Page components
│   │   ├── contexts/              # React contexts (Theme)
│   │   ├── styles/                # SCSS stylesheets
│   │   ├── constants/             # Game data (Wordle words)
│   │   └── test/                  # Test files
│   ├── public/                    # Static assets
│   └── package.json               # Frontend dependencies
├── .github/workflows/             # CI/CD pipeline
└── pom.xml                        # Backend dependencies
```

---

## Contributors
- Balic Erik
- Kasztl Richard
- Szabo Bence Botond

## License
This project is developed as part of a university coursework.