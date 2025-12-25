package me.arun.vastu.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import me.arun.vastu.core.network.ApiEndpoints
import me.arun.vastu.data.model.Course
import me.arun.vastu.data.model.CurriculumResponse
import me.arun.vastu.data.model.PagedResponse
import me.arun.vastu.data.model.ProgressUpdateRequest
import me.arun.vastu.data.model.ProgressUpdateResponse

class StudentRemoteDataSource(private val httpClient: HttpClient) {

    suspend fun getCourses(): PagedResponse<Course> {
        return httpClient.get(ApiEndpoints.COURSES).body()
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
