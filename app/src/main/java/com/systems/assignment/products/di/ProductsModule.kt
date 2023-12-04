package com.systems.assignment.products.di

import com.systems.assignment.products.data.remote.ProductsApi
import com.systems.assignment.products.data.repository.ProductsRepositoryImpl
import com.systems.assignment.products.domain.remote.ProductsRepository
import com.systems.assignment.products.navigation.ProductsNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductsModule {

    @Provides
    @Singleton
    fun provideProductsApi(retrofit: Retrofit): ProductsApi = retrofit.create(ProductsApi::class.java)

    @Provides
    @Singleton
    fun provideDbtRepository(api: ProductsApi): ProductsRepository {
        return ProductsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideProductsNavigation(): ProductsNavigation {
        return ProductsNavigation()
    }

}