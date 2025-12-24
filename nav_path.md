# Navigation Paths and Rules

This document outlines the navigation structure, authentication model, and rules implemented in the application using the `navigation3-runtime` library.

## Routes

### Public Routes
These routes are accessible without any authentication.
-   **Dashboard**: `AppScreen.Dashboard`
-   **Courses**: `AppScreen.Courses`
-   **Course Details**: `AppScreen.CourseDetails(courseId: String)`

### Authentication Routes
These routes are used for user authentication processes.
-   **Login**: `AppScreen.Login(redirectTo: AppScreen? = null)`
-   **Register**: `AppScreen.Register`

### Protected Routes
These routes require the user to be authenticated to access their content or perform actions.
-   **Enroll in Course**: `AppScreen.Enroll(courseId: String)`
-   **Lecture Player**: `AppScreen.Lecture(courseId: String, lectureId: String)`
-   **My Courses**: `AppScreen.MyCourses`
-   **Stats**: `AppScreen.Stats` (This is a bottom bar item but also a protected route in terms of actions it might lead to)

## Authentication Model

The application allows users to browse content freely, with authentication required only for specific protected actions.

-   The application launches directly into the `Dashboard` (Home) screen.
-   There is no splash screen acting as an authentication gate.
-   Login is not enforced on launch.

Authentication is triggered *only* when a user attempts actions such as:
-   Course enrollment
-   Watching lectures
-   Accessing "My Courses"
-   Tracking progress

## Navigation Rules

1.  **Dashboard and Course Details are Always Accessible**: Users can always navigate to the Dashboard and view Course Details pages.
2.  **Authentication Check on Protected Actions**: When a user clicks on an action that leads to a protected route (e.g., "Enroll", "Continue Learning", "Play Lecture"), the application first checks the user's authentication state.
3.  **Redirect to Login/Register**: If the user is *not* authenticated for a protected action, they are automatically navigated to the `Login` screen. The original intended destination (the protected route) is preserved.
4.  **Automatic Action Resume After Login**: After a successful login, the user is automatically redirected to their originally intended protected destination, allowing them to resume their action seamlessly.

## Bottom Navigation

The application features a bottom navigation bar with the following tabs:
-   **Dashboard**
-   **Courses**
-   **Stats**

### Bottom Navigation Rules:
-   The bottom navigation bar is **visible** for public routes.
-   The bottom navigation bar is **hidden** for the `Lecture Player` screen to provide an immersive viewing experience.
-   Each tab in the bottom navigation maintains its own independent backstack.

## Backstack Behavior

-   **Back from Login**: Pressing back from the `Login` screen returns the user to the public screen they were on before attempting the protected action.
-   **Back from Lecture**: Pressing back from the `Lecture` player screen returns the user to the `Course Details` page of the lecture.
-   **Logout**: Upon user logout, all navigation stacks are cleared, and the user is navigated back to the `Dashboard` screen, ensuring a clean and secure state.

## Implementation Details

-   Uses `navigation3-runtime` for navigation.
-   All navigation destinations are defined as sealed/typed serializable objects (`AppScreen`).
-   Authentication checks are implemented at the *action level* (within the `Navigator`), not by blocking navigation globally.
-   The `Navigator` class handles the redirect logic for unauthenticated protected actions.
