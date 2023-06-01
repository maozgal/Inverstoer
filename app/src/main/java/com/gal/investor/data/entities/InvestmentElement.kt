package com.gal.investor.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "investment_element", foreignKeys = [
        ForeignKey(
        entity = InvestmentItemType::class,
        parentColumns = arrayOf("name"),
        childColumns = arrayOf("investment_type_name"),
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = Apartment::class,
            parentColumns = arrayOf("uid"),
            childColumns = arrayOf("apartment_uid"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class InvestmentElement(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "is_income") val isIncome: Boolean?,
    @ColumnInfo(name = "investment_type_name") val investmentTypeName: String?,
    @ColumnInfo(name = "apartment_uid") val apartmentUid: Int?,
    @ColumnInfo(name = "amount") val amount: Double?,
    @ColumnInfo(name = "date_in_millis") val dateInMillis: Long?
)