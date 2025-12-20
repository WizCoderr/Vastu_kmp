package me.arun.vastu.features.home.dashboard.domain.usecase

import me.arun.vastu.features.home.dashboard.domain.model.Dashboard
import me.arun.vastu.features.home.dashboard.domain.repository.DashboardRepository

class GetDashboardDataUseCase(
    private val repository: DashboardRepository
) {
    suspend operator fun invoke(): Result<Dashboard> {
        return repository.getDashboard()
    }
}
