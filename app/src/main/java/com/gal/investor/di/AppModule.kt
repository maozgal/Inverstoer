package com.gal.investor.di

import android.content.Context
import androidx.room.Room
import com.gal.investor.data.InvestmentDB
import com.gal.investor.data.InvestmentItemDAO
import com.gal.investor.repo.ContextApartmentRepo
import com.gal.investor.repo.InvestRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

//    @Singleton
//    @Provides
//    fun provideOkHttpClient(): OkHttpClient {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//         return OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .build()
//    }
//

    @Provides
    @Singleton
    fun provideRepo(investmentItemDAO: InvestmentItemDAO) = InvestRepo(investmentItemDAO)

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext applicationContext: Context) = Room.databaseBuilder(
        applicationContext,
        InvestmentDB::class.java, "investment-db"
    ).build()


    @Provides
    @Singleton
    fun provideDAO(db: InvestmentDB) = db.investmentItemDAO()

    @Provides
    @Singleton
    fun provideContextApartment() = ContextApartmentRepo()
}