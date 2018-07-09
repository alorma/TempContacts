package com.alorma.tempcontacts.ui.configuration

sealed class TimeSelection {
    object HOUR : TimeSelection()
    object DAY : TimeSelection()
    object WEEK : TimeSelection()
    object MONTH : TimeSelection()
    object OTHER : TimeSelection()
    object NONE : TimeSelection()
    data class Custom(val number: Int, val type: TimeSelection): TimeSelection()
}