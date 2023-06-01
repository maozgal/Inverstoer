package com.gal.investor.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "investment_item_type")
data class InvestmentItemType (
    @PrimaryKey(autoGenerate = false) val name: String,
    @ColumnInfo(name = "is_income") val isIncome: Boolean?,
    )