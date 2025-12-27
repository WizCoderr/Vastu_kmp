package me.arun.vastu.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import me.arun.vastu.core.network.ApiEndpoints
import me.arun.vastu.data.model.Course
import me.arun.vastu.data.model.CoursesResponse
import me.arun.vastu.data.model.CurriculumResponse
import me.arun.vastu.data.model.ProgressUpdateRequest
import me.arun.vastu.data.model.ProgressUpdateResponse

class StudentRemoteDataSource(private val httpClient: HttpClient) {

    @Serializable
    data class RawCourse(
        val id: String,
        val title: String,
        val description: String,
        val thumbnail: String?, // Directly use thumbnail
        val price: Double,
        val published: Boolean,
        val instructorId: String,
        val s3Key: String? = null,
        val s3Bucket: String? = null,
        val mediaType: String? = null
    )

    @Serializable
    data class RawCoursesResponse(
        val success: Boolean,
        val data: List<RawCourse>
    )

    suspend fun getCourses(): CoursesResponse {
        val rawResponse = httpClient.get(ApiEndpoints.PUBLIC_COURSES).body<RawCoursesResponse>()
        println("Raw thumbnail from API: ${rawResponse.data.firstOrNull()?.thumbnail}")

        // Manually map RawCourse to data.model.Course to ensure all fields are handled
        val mappedCourses = rawResponse.data.map { rawCourse ->
            Course(
                id = rawCourse.id,
                title = rawCourse.title,
                description = rawCourse.description,
                instructor = rawCourse.instructorId, // Map instructorId to instructor
                price = rawCourse.price,
                published = rawCourse.published,
                imageUrl = rawCourse.thumbnail, // Map thumbnail to imageUrl
                tag = null, // Not present in raw response
                accessType = null // Not present in raw response
            )
        }
        return CoursesResponse(success = rawResponse.success, data = mappedCourses)
    }

    suspend fun getCourseDetails(id: String): Course {
        return httpClient.get(ApiEndpoints.courseDetails(id)).body()
    }

    suspend fun getCourseCurriculum(id: String): CurriculumResponse {
        return httpClient.get(ApiEndpoints.courseCurriculum(id)).body()
    }

    suspend fun updateProgress(request: ProgressUpdateRequest): ProgressUpdateResponse {
        return httpClient.post(ApiEndpoints.UPDATE_PROGRESS) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}
