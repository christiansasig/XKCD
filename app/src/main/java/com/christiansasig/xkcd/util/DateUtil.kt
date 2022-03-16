package com.christiansasig.xkcd.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun getMonthNameByNumber(monthNumber: Int): String {
        var monthName = ""
        if (monthNumber in 1..12) try {
            val calendar = Calendar.getInstance()
            calendar[Calendar.MONTH] = monthNumber - 1
            val simpleDateFormat = SimpleDateFormat("MMM", Locale.getDefault())
            monthName = simpleDateFormat.format(calendar.time)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return monthName
    }
}