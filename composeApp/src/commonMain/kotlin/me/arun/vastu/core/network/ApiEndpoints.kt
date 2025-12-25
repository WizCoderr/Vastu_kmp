package me.arun.vastu.core.network

object ApiEndpoints {
    const val BASE_URL = "https://vastu-backend-latest.onrender.com/"
    const val REGISTER = "${BASE_URL}auth/register"
    const val LOGIN = "${BASE_URL}auth/login"
    const val LOGOUT = "${BASE_URL}auth/logout"

    // Student Operations
    private const val STUDENT_API = "${BASE_URL}api/student"
    const val COURSES = "$STUDENT_API/courses"
    fun courseDetails(id: String) = "$COURSES/$id"
    fun courseCurriculum(id: String) = "$COURSES/$id/curriculum"
    const val UPDATE_PROGRESS = "$STUDENT_API/progress/update"

    // Payments
    private const val PAYMENTS_API = "${BASE_URL}api/payments"
    const val CREATE_PAYMENT_INTENT = "$PAYMENTS_API/create-intent"
}