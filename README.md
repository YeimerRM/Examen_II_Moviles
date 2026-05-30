# Panini Support

Android proof of concept for the UNA mobile exam. The app models internal support tickets for Panini around the FIFA World Cup 2026 album operation.

## Stack

- Kotlin
- Jetpack Compose + Material 3
- Navigation Compose
- MVVM with `StateFlow`
- Manual dependency container
- Retrofit contracts and DTOs
- In-memory mock repository

## Mock login

- Email: `soporte@panini.com`
- Password: `Panini2026`

## Run

```bash
./gradlew assembleDebug
```

To run the unit test used in this implementation:

```bash
./gradlew testDebugUnitTest
```

## Structure

- `app/`: Android application source.
- `contracts/`: OpenAPI contract for the mock API.
- `docs/`: technical notes for reviewers.
- `video/`: placeholder notes for the recorded demo.

## Notes

- The data source is in memory on purpose.
- Retrofit interfaces are present to make a backend swap straightforward later.
- Feature flags live in `core/FeatureFlags.kt`.
