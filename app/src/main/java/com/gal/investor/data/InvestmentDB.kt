package com.gal.investor.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.gal.investor.data.entities.Apartment
import com.gal.investor.data.entities.InvestmentElement
import com.gal.investor.data.entities.InvestmentItemType

@Database(
    entities = [InvestmentItemType::class, InvestmentElement::class, Apartment::class],
    version = 1,
    exportSchema = true
)
abstract class InvestmentDB : RoomDatabase() {
    abstract fun investmentItemDAO(): InvestmentItemDAO
}