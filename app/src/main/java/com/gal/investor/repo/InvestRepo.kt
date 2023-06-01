package com.gal.investor.repo

import com.gal.investor.data.InvestmentItemDAO
import com.gal.investor.data.entities.Apartment
import com.gal.investor.data.entities.InvestmentElement
import com.gal.investor.data.entities.InvestmentItemType
import java.util.*
import javax.inject.Inject

class InvestRepo @Inject constructor(
    private val investmentDAO: InvestmentItemDAO
) {
    suspend fun addNewApartment(name: String, location: String) {
        val apartment = Apartment(name = name, location = location)
        investmentDAO.insertApartment(apartment)
    }

    suspend fun addNewInvestmentItem(isIncome: Boolean, name: String) {
        val investmentItemType = InvestmentItemType(isIncome = isIncome, name = name)
        investmentDAO.insertItemType(investmentItemType)
    }

    suspend fun getAll() = investmentDAO.getAll()

    suspend fun getAllApartments(): List<Apartment> =
        investmentDAO.getAllApartments()

    suspend fun getAllInvestmentElements(isIncome: Boolean): List<InvestmentElement> =
        investmentDAO.getAllInvestmentElements(isIncome)

    suspend fun getInvestmentItemType(isIncome: Boolean): List<InvestmentItemType> =
        investmentDAO.getInvestmentTypes(isIncome)

    suspend fun addInvestmentElement(
        isIncome: Boolean,
        investmentTypeName: String,
        amount: Double,
        data: Date,
        apartmentUid: Int?
    ) {
        investmentDAO.insertElement(
            InvestmentElement(
                isIncome = isIncome,
                investmentTypeName =
                investmentTypeName,
                amount = amount,
                dateInMillis = data.time,
                apartmentUid = apartmentUid
            )
        )
    }
}