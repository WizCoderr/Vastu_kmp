# Vastu (Kotlin Multiplatform) — Application Context

## 1) What this repo is
This repository is a **Kotlin Multiplatform (KMP) + Compose Multiplatform** application called **`Vastu`**.

It targets:
- **Android** (Compose UI)
  - **iOS** (Compose UI embedded into SwiftUI)

The project is structured as:
- **`:composeApp`** — the shared KMP module that contains (most of) the app code and UI
  - **`/iosApp`** — the native iOS wrapper app (SwiftUI entry point that hosts Compose)


## 2) High-level architecture
At a high level, the app follows a feature-based structure with layers:

- **`core/`**
  - app-wide concerns: navigation, theme, shared UI primitives, network client setup, DI aggregation
  - **`features/`**
    - feature modules grouped by domain area (auth, home, splash, video)
    - each feature typically has **presentation** (Compose UI + ViewModel), and sometimes **domain/data** sublayers
  - **`data/`** (shared data layer)
    - remote data sources (Ktor), local data sources, DTO models, repository implementations
  - **`domain/`** (shared domain layer)
    - repository interfaces, domain models, and some cross-feature use cases
  - **`persistence/`**
    - DataStore-backed key/value storage (auth token, user info, video progress)

The app uses:
- **Koin** for dependency injection (including Compose ViewModels)
  - **Ktor** as the HTTP client
  - **AndroidX Navigation3** (`navigation3-runtime` / `navigation3-ui`) with **typed routes** (`NavKey`)
  - **AndroidX DataStore Preferences** for simple persistence


## 3) Build system & targets
### 3.1 Gradle modules
- **`settings.gradle.kts`** includes:
  - `:composeApp`

### 3.2 KMP targets
`composeApp/build.gradle.kts` declares:
- `androidTarget` (JVM 11)
  - `iosArm64`, `iosSimulatorArm64` — producing a static framework named **`ComposeApp`**

### 3.3 Key dependencies (from `gradle/libs.versions.toml`)
Not exhaustive, but the main ones:
- **Kotlin**: `2.3.0`
  - **Compose Multiplatform**: `1.9.3`
  - **Ktor**: `3.3.3`
  - **Koin**: `4.1.1` (+ KSP compiler)
  - **Navigation3**: `1.0.0-alpha06`
  - **AndroidX DataStore**: `1.2.0`
  - **Kotzilla**: `1.4.2-RC1` (analytics/instrumentation)
  - **Media3**: `1.9.0` (Android video)
  - **Coil3**: `3.3.0`

### 3.4 KSP / generated code
`composeApp/build.gradle.kts` configures KSP for Koin and adds:
- `kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")` into `commonMain`


## 4) Runtime entry points (how the app starts)
### 4.1 Android
- **Manifest**: `composeApp/src/androidMain/AndroidManifest.xml`
  - `android:name=".MainApplication"`
  - launches `.MainActivity`

  - **DI boot**: `MainApplication` (`composeApp/src/androidMain/kotlin/me/arun/vastu/MainApplication.kt`)
    - calls `initKoin { androidContext(...) }`

  - **UI boot**: `MainActivity` (`composeApp/src/androidMain/kotlin/me/arun/vastu/MainActivity.kt`)
    - `setContent { App() }`

### 4.2 iOS
- **SwiftUI App**: `iosApp/iosApp/iOSApp.swift`
  - shows `ContentView()`

  - **SwiftUI host**: `iosApp/iosApp/ContentView.swift`
    - creates a `UIViewController` from the KMP framework:
      - `MainViewControllerKt.MainViewController()`

  - **Compose entry controller**: `composeApp/src/iosMain/kotlin/me/arun/vastu/MainViewController.kt`
    - `ComposeUIViewController { App() }`

**Important note:** Android initializes Koin in `MainApplication`. iOS does not currently show an explicit call to `initKoin()` from Swift/Objective‑C in the checked files, so iOS may require additional initialization (or it may be done elsewhere in the Xcode project / build pipeline).


## 5) Dependency Injection (Koin)
### 5.1 Koin startup
- `me.arun.vastu.core.di.initKoin(config: KoinAppDeclaration? = null)`
  - `startKoin { modules(appModule); analytics() }`

### 5.2 App module wiring
`appModule` (`core/di/AppModule.kt`) aggregates modules:
- `homeModule`
  - `splashModule`
  - `dataModule`
  - `useCaseModule`
  - `courseScreensModule`
  - `persistenceModule` (expect/actual)
  - `networkModule`
  - `loginPresentationModule`
  - `registerPresentationModule`

### 5.3 Data + domain wiring
- `dataModule` (`di/DataModule.kt`):
  - data sources: `AuthLocalDataSource`, `AuthRemoteDataSource`, `StudentRemoteDataSource`, `PaymentRemoteDataSource`
  - repositories:
    - `AuthRepository` → `AuthRepositoryImpl`
    - `PaymentRepository` → `PaymentRepositoryImpl`

  - `useCaseModule` (`di/UseCaseModule.kt`):
    - auth: `SaveAuthTokenUseCase`, `GetAuthTokenUseCase`, `LoginUseCase`, `RegisterUseCase`, `LogoutUseCase`
    - payments: `CreatePaymentIntentUseCase`
    - progress: `UpdateProgressUseCase`

### 5.4 Feature modules
Examples:
- `homeModule` includes:
  - `dashboardModule`, `courseModule`, `profileModule`
  - `courseScreensModule` includes:
    - course details modules (see `CourseDetailsModule.kt`)


## 6) Navigation model
The app uses **AndroidX Navigation3** but wraps it in a custom multi-stack state model.

### 6.1 Routes
`AppScreen` (`core/navigation/AppScreen.kt`) defines typed routes:
- Public:
  - `Dashboard`, `Courses`, `CourseDetails(courseId)`, `Stats`, `Profile`
  - Auth:
    - `Login(redirectTo: AppScreen?)`, `Register`
  - Protected (via `ProtectedRoute`):
    - `Enroll(courseId)`, `Lecture(courseId, lectureId)`, `MyCourses`

### 6.2 Multi-backstack tabs
`NavigationState` maintains **one back stack per top-level tab** (Dashboard/Courses/Stats/Profile).
- `rememberNavigationState()` builds `backStacks` keyed by each top-level route.
  - `Navigator` pushes within the active stack or switches the top-level route.

### 6.3 Protected-route gating
`Navigator.navigate(route, isLoggedIn)` enforces:
- if route is `ProtectedRoute` and `isLoggedIn == false`:
  - redirect to `AppScreen.Login(redirectTo = route)`

This implements an **“action-level auth gate”**: users can browse some areas freely, but certain routes/actions require auth.

### 6.4 Root navigation UI
`NavigationRoot` (`core/navigation/NavigationRoot.kt`):
- Shows a Material3 `Scaffold` with a bottom bar (from `bottomBarItems`)
  - Hides the bottom bar on `ProtectedRoute.Lecture`
  - Uses `NavDisplay` entries for each route.

Notable route handlers:
- `Dashboard` emits events like:
  - navigate to course details
  - navigate to lecture/video player (protected)
  - `Login` supports redirecting to a previously requested protected route
  - `Stats` entry currently has no screen implementation
  - Some protected entries are placeholders (e.g., `MyCourses`, `Enroll`)

There is also a separate `HomeNavigationRoot` in `features/home/navigation/` that demonstrates another navigation approach for home, but the main app currently uses `core/navigation/NavigationRoot`.


## 7) App startup/auth state
`App()` (`composeApp/src/commonMain/kotlin/me/arun/vastu/App.kt`):
- obtains `SplashViewModel` via `koinViewModel()`
  - collects `isUserAuthenticated` (nullable boolean)
  - once non-null:
    - shows `NavigationRoot(isLoggedIn = isUserAuthenticated)` inside `VastuMobileTheme`

`SplashViewModel` checks auth by reading stored token via `GetAuthTokenUseCase()`.

There is also a `SplashScreen` composable in `features/splash/SplashScreen.kt`, but the current `App()` does not render it; instead it just waits until auth state is known.


## 8) Networking
### 8.1 HTTP client
`createHttpClient()` (`core/network/ApiClient.kt`) configures Ktor:
- `ContentNegotiation` with kotlinx.serialization JSON
  - `ignoreUnknownKeys = true`
  - `HttpTimeout` (`requestTimeoutMillis = 30000`)
  - `defaultRequest`:
    - sets base URL and JSON content-type header

DI provides:
- `HttpEngineFactory()` (expect/actual per platform)
  - `HttpClient` created using that engine

### 8.2 API endpoints
`ApiEndpoints` (`core/network/ApiEndpoints.kt`):
- Base URL:
  - `https://vastu-backend-latest.onrender.com/`
  - Auth:
    - `auth/register`, `auth/login`, `auth/logout`
  - Courses:
    - public: `/api/public/courses`
    - student: `/api/student/courses`, details, curriculum
  - Progress:
    - `/api/student/progress/update`
  - Payments:
    - `/api/payments/create-intent`

Additional backend/API documentation exists in:
- `student.md`


## 9) Data layer
### 9.1 Remote data sources
- `AuthRemoteDataSource`:
  - `POST` login/register/logout

  - `StudentRemoteDataSource`:
    - `GET` public courses, course details, curriculum
    - `POST` update progress
    - includes manual mapping from a `RawCourse` DTO to the app’s `Course` model

  - `PaymentRemoteDataSource`:
    - creates a Stripe payment intent (via backend)

### 9.2 Repositories
- `AuthRepositoryImpl`:
  - wraps remote calls into an `ApiResult`
  - persists auth token via `PreferencesRepository`

  - `PaymentRepositoryImpl`:
    - maps request/response between data ↔ domain models


## 10) Domain layer
### 10.1 Repository interfaces
- `domain/repository/AuthRepository`
  - `domain/repository/PaymentRepository`

### 10.2 Use cases
The shared domain use cases are thin wrappers around repositories.
Examples:
- `LoginUseCase(loginRequest)` → `AuthRepository.login(loginRequest)`
  - `GetAuthTokenUseCase()` → `AuthRepository.getAuthToken()`
  - `CreatePaymentIntentUseCase(request)` → `PaymentRepository.createPaymentIntent(request)`

**Important note:** The codebase contains both:
- shared-domain use cases under `me.arun.vastu.domain.usecase.*`
  - feature-scoped use cases under `me.arun.vastu.features.auth.*.domain.usecase.*`

So you effectively have **two parallel “domain” layers** (global and per-feature). This is workable, but it’s a key piece of context when tracing dependencies.


## 11) Persistence (DataStore)
Persistence is implemented as a KMP expect/actual module:

- `persistence/di/PersistenceModule.kt`:
  - `expect val persistenceModule: Module`

  - Android actual (`PersistenceModule.android.kt`):
    - `provideDataStore(androidContext())`
    - binds `PreferencesRepository` → `DefaultPreferencesRepository`

  - iOS actual (`PersistenceModule.ios.kt`):
    - uses `createDataStore()` (platform-specific path creation happens in iOS source)
    - binds `PreferencesRepository` → `DefaultPreferencesRepository`

Stored keys (`PreferenceKeys.kt`):
- `auth_token`, `user_name`, `user_email`
  - per-course video:
    - `video_position_<courseId>`, `video_progress_<courseId>`

`DefaultPreferencesRepository` supports:
- save/get auth token
  - save/get user name
  - save/get per-course video progress


## 12) UI/Theming/Resources
### 12.1 Theme
`VastuMobileTheme` (`core/theme/VastuMobileTheme.kt`):
- defines light/dark palettes but currently applies the **LightColorScheme** unconditionally

### 12.2 Icons
`VastuIcons` centralizes icon access (Material + Fluent UI icons).
Bottom bar uses:
- `VastuIcons.dashboard`
  - `VastuIcons.course`
  - `VastuIcons.Profile`

### 12.3 String resources
The app uses Compose Multiplatform resources:
- `composeApp/src/commonMain/composeResources/values/strings.xml`
  - generated accessors under `vastu.composeapp.generated.resources.Res.string.*`

Example usage in `RegisterScreen`:
- `stringResource(Res.string.student_register)`


## 13) Features overview
### 13.1 Auth
Location: `features/auth/`
- **Login**
  - `LoginRoot` + `LoginViewModel`
  - emits `LoginEvent.NavigateToHome` or `LoginEvent.NavigateToRegister`
  - **Register**
    - `RegisterRoot` + `RegisterViewModel`
    - emits `RegisterEvent.NavigateToHome` or `RegisterEvent.NavigateToLogin`

Token persistence:
- ViewModels call `SaveAuthTokenUseCase(...)` on successful auth.

### 13.2 Home
Location: `features/home/`
- **Dashboard**
  - list-like view of enrolled/active courses
  - emits navigation events (course click, continue learning)

  - **Courses**
    - loads all courses and exposes events to navigate to details

  - **Course details**
    - loads details + curriculum for a given `courseId`
    - has an `Enroll` action (currently only triggers a UI action/event)

  - **Profile**
    - shows a sign-in button when unauthenticated
    - shows profile details + settings when authenticated
    - emits events like logout / navigate to login

### 13.3 Video
Location: `features/vedio/` (note the folder name)
- `VideoPlayerRoot` renders a placeholder text plus a `VideoPlayer(...)` call
  - includes a hardcoded MP4 URL in the current file


## 14) How to run
### 14.1 Android
From IDE: run the Android configuration.

From terminal (Windows):
```powershell
.\gradlew.bat :composeApp:assembleDebug
```

### 14.2 iOS
- Open `iosApp` in Xcode
  - Build/run the `iosApp` target

If Koin is not initialized on iOS at runtime, you may need to explicitly call `initKoin()` from Swift before creating `MainViewController()`.


## 15) “Where do I change X?” cheat sheet
- **App start (shared)**: `composeApp/src/commonMain/kotlin/me/arun/vastu/App.kt`
  - **Android entry**: `MainApplication.kt`, `MainActivity.kt`
  - **iOS entry**: `iosApp/iosApp/ContentView.swift`, `composeApp/src/iosMain/.../MainViewController.kt`
  - **Navigation routes**: `core/navigation/AppScreen.kt`
  - **Navigation host**: `core/navigation/NavigationRoot.kt`
  - **Auth gating**: `core/navigation/Navigator.kt`
  - **DI graph**: `core/di/AppModule.kt` + feature modules
  - **API base URL / endpoints**: `core/network/ApiEndpoints.kt`
  - **HTTP config**: `core/network/ApiClient.kt`
  - **Persistence keys**: `persistence/keys/PreferenceKeys.kt`
  - **Resources (strings)**: `composeResources/values/strings.xml`


## 16) Known gaps / important caveats (context for future work)
- **iOS DI init**: Android explicitly initializes Koin; iOS init isn’t visible in the Swift entry files.
  - **Stats screen**: `AppScreen.Stats` exists but has no UI entry implemented in `NavigationRoot`.
  - **Some protected routes are placeholders**: `MyCourses`, `Enroll`.
  - **Two different navigation roots exist** (`NavigationRoot` and `HomeNavigationRoot`), but only one may be in active use.
  - **Mixed domain layering**: both global `domain/usecase` and feature-local `features/*/domain/usecase` exist; be careful when wiring/injecting use cases.
