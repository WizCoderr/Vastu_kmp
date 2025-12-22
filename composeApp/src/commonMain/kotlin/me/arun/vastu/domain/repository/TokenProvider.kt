package me.arun.vastu.domain.repository

interface TokenProvider {
    fun getToken(): String?
}
