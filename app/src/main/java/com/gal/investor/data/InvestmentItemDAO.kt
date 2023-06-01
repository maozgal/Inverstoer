package com.gal.investor.data

import androidx.room.*
import com.gal.investor.data.entities.Apartment
import com.gal.investor.data.entities.InvestmentElement
import com.gal.investor.data.entities.InvestmentItemType

@Dao
interface InvestmentItemDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItemType(vararg investmentItemType: InvestmentItemType)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertElement(investmentElement: InvestmentElement)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertApartment(apartment: Apartment)

    @Query("SELECT * FROM investment_element WHERE is_income = :income")
    suspend fun getAllInvestmentElements(income: Boolean): List<InvestmentElement>

    @Transaction
    @Query("SELECT * FROM investment_item_type WHERE is_income = :income")
    suspend fun getInvestmentTypes(income: Boolean): List<InvestmentItemType>

    @Query("SELECT * FROM investment_item_type")
    suspend fun getAll(): List<InvestmentItemType>

    @Query("SELECT * FROM apartments")
    suspend fun getAllApartments(): List<Apartment>

}