package me.arun.vastu.features.home.dashboard.domain.repository

import me.arun.vastu.features.home.dashboard.domain.model.Dashboard

interface DashboardRepository {
    suspend fun getDashboard(): Result<Dashboard>
}
