package com.mathbrandino.e_commerce.di

import android.content.Context
import com.mathbrandino.e_commerce.data.local.EcommerceDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        EcommerceDatabase.getDatabase(context)

    @Provides
    fun provideProductDao(database: EcommerceDatabase) = database.getProductDao()

    @Provides
    fun provideOrderDao(database: EcommerceDatabase) = database.getOrderDao()

    @Provides
    fun provideOrderItemDao(database: EcommerceDatabase) = database.getOrderItemDao()
}