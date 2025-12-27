package me.arun.vastu.di

import me.arun.vastu.data.local.AuthLocalDataSource
import me.arun.vastu.data.remote.AuthRemoteDataSource
import me.arun.vastu.data.remote.PaymentRemoteDataSource
import me.arun.vastu.data.remote.StudentRemoteDataSource
import me.arun.vastu.data.repository.AuthRepositoryImpl
import me.arun.vastu.data.repository.EnrolmentRepositoryImpl
import me.arun.vastu.data.repository.PaymentRepositoryImpl
import me.arun.vastu.domain.repository.AuthRepository
import me.arun.vastu.domain.repository.EnrolmentRepository
import me.arun.vastu.domain.repository.PaymentRepository
import org.koin.dsl.module

val dataModule = module {
    single { AuthLocalDataSource(get()) }
    single { AuthRemoteDataSource(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(),get()) }
    single<EnrolmentRepository> { EnrolmentRepositoryImpl() }
    single { StudentRemoteDataSource(get()) }
    single { PaymentRemoteDataSource(get()) }
    single<PaymentRepository> { PaymentRepositoryImpl(get()) }
}
