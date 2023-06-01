package com.gal.investor.add_data.add.element

import com.gal.investor.utils.Enums
import java.util.Date

data class AddInvestmentElementScreenData(
    val isIncome: String?,
    val investmentType: String?,
    val amount: Double,
    val date: Date
) {
    fun getIsIncome(): Boolean? {
        if (isIncome == null) return null
        return Enums.InvestmentKind.getByValue(isIncome) == Enums.InvestmentKind.INCONE
    }
}
