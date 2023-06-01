package com.gal.investor.utils

class Enums {
    enum class InvestmentKind(var kind: String) {
        INCONE("Income"),
        EXPENSE("Expense");

        override fun toString(): String {
            return kind
        }
        companion object {
            private val VALUES = values()
            fun getByValue(kind: String) = VALUES.firstOrNull { it.kind == kind }
        }
    }
}