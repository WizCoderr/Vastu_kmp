package me.arun.vastu.features.home.courses.data.repository

import me.arun.vastu.features.home.courses.data.mappers.toDomain
import me.arun.vastu.features.home.courses.domain.model.Course
import me.arun.vastu.features.home.courses.domain.model.CourseAccessType
import me.arun.vastu.features.home.courses.domain.model.Courses
import me.arun.vastu.features.home.courses.domain.repository.CoursesRepository


/**
 * Concrete implementation of the repository for the Courses feature.
 */
class DefaultCoursesRepository  constructor() : CoursesRepository {

    override suspend fun getCoursesData(): Result<Courses> {
        return try {
            val courses = listOf(
                Course("1", "Vastu Foundation Course", "Batch 2023", null, CourseAccessType.BATCH),
                Course("2", "Professional Vastu Course (Recorded)", "Lifetime Access", null, CourseAccessType.LIFETIME),
                Course("3", "Numerology Course (Recorded)", "Advanced Module", null, CourseAccessType.RECORDED),
                Course("4", "Vastu & Numerology Combined Course", "Combined Pack", null, CourseAccessType.COMBINED),
                Course("5", "Advance Vastu Course", "Recorded", null, CourseAccessType.RECORDED),

                Course("6", "Basic Numerology for Beginners", "Batch 2022", null, CourseAccessType.BATCH),
                Course("7", "Vastu for Home Planning", "Lifetime Access", null, CourseAccessType.LIFETIME),
                Course("8", "Numerology for Career Growth", "Recorded", null, CourseAccessType.RECORDED),
                Course("9", "Vastu Remedies & Corrections", "Advanced", null, CourseAccessType.RECORDED),
                Course("10", "Business Vastu Mastery", "Professional", null, CourseAccessType.LIFETIME),

                Course("11", "Residential Vastu Design", "Recorded", null, CourseAccessType.RECORDED),
                Course("12", "Commercial Vastu Course", "Lifetime Access", null, CourseAccessType.LIFETIME),
                Course("13", "Numerology Name Correction", "Advanced", null, CourseAccessType.RECORDED),
                Course("14", "Vastu for Apartments", "Batch 2021", null, CourseAccessType.BATCH),
                Course("15", "Vastu for Offices", "Professional", null, CourseAccessType.RECORDED),

                Course("16", "Advanced Numerology Predictions", "Recorded", null, CourseAccessType.RECORDED),
                Course("17", "Vastu for Wealth & Prosperity", "Lifetime Access", null, CourseAccessType.LIFETIME),
                Course("18", "Astro-Numerology Basics", "Introductory", null, CourseAccessType.RECORDED),
                Course("19", "Vastu for Factories", "Industrial", null, CourseAccessType.RECORDED),
                Course("20", "Professional Numerology Course", "Lifetime Access", null, CourseAccessType.LIFETIME),

                Course("21", "Vastu for Plot Selection", "Recorded", null, CourseAccessType.RECORDED),
                Course("22", "Numerology for Relationships", "Advanced", null, CourseAccessType.RECORDED),
                Course("23", "Vastu Energy Balancing", "Professional", null, CourseAccessType.RECORDED),
                Course("24", "Numerology Lo Shu Grid Mastery", "Recorded", null, CourseAccessType.RECORDED),
                Course("25", "Vastu for Spiritual Growth", "Lifetime Access", null, CourseAccessType.LIFETIME),

                Course("26", "Vastu for Interior Design", "Recorded", null, CourseAccessType.RECORDED),
                Course("27", "Numerology for Business Naming", "Professional", null, CourseAccessType.RECORDED),
                Course("28", "Vastu for Land Development", "Advanced", null, CourseAccessType.RECORDED),
                Course("29", "Numerology Remedies & Solutions", "Recorded", null, CourseAccessType.RECORDED),
                Course("30", "Advanced Vastu Practitioner Course", "Lifetime Access", null, CourseAccessType.LIFETIME),

                Course("31", "Vastu for Educational Institutes", "Recorded", null, CourseAccessType.RECORDED),
                Course("32", "Numerology for Health Analysis", "Advanced", null, CourseAccessType.RECORDED),
                Course("33", "Vastu for Hospitality Industry", "Professional", null, CourseAccessType.RECORDED),
                Course("34", "Chaldean Numerology Complete Course", "Lifetime Access", null, CourseAccessType.LIFETIME),
                Course("35", "Vastu for Retail Shops", "Recorded", null, CourseAccessType.RECORDED),

                Course("36", "Numerology for Financial Stability", "Advanced", null, CourseAccessType.RECORDED),
                Course("37", "Vastu for High-Rise Buildings", "Professional", null, CourseAccessType.RECORDED),
                Course("38", "Numerology Destiny Number Analysis", "Recorded", null, CourseAccessType.RECORDED),
                Course("39", "Vastu for Farmhouses", "Recorded", null, CourseAccessType.RECORDED),
                Course("40", "Advanced Numerology Consultant Course", "Lifetime Access", null, CourseAccessType.LIFETIME),

                Course("41", "Vastu for Marriage Harmony", "Recorded", null, CourseAccessType.RECORDED),
                Course("42", "Numerology for Students", "Beginner", null, CourseAccessType.RECORDED),
                Course("43", "Vastu for Hospitals & Clinics", "Professional", null, CourseAccessType.RECORDED),
                Course("44", "Numerology Karmic Debt Analysis", "Advanced", null, CourseAccessType.RECORDED),
                Course("45", "Vastu & Numerology Expert Combo", "Combined Pack", null, CourseAccessType.COMBINED),

                Course("46", "Vastu for Political Success", "Advanced", null, CourseAccessType.RECORDED),
                Course("47", "Numerology for Life Path Mastery", "Lifetime Access", null, CourseAccessType.LIFETIME),
                Course("48", "Vastu for Temple Architecture", "Recorded", null, CourseAccessType.RECORDED),
                Course("49", "Numerology Personal Year Forecasting", "Recorded", null, CourseAccessType.RECORDED),
                Course("50", "Ultimate Vastu & Numerology Master Program", "Lifetime Access", null, CourseAccessType.LIFETIME)
            )

            val domainModel = Courses(allCourses = courses)
            Result.success(domainModel)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}