package me.arun.vastu.features.auth.landing.presentation

data class LandingState(
    val currentPage: Int = 0,
    val totalPages: Int = 3,
    val isAutoScrollEnabled: Boolean = true,
    val autoScrollDelayMs: Long = 3000L
)
