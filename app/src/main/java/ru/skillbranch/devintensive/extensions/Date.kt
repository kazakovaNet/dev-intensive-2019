package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by nkazakova on 2019-06-30.
 */

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))

    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    var time: Long = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time

    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val timeDiff = this.time - date.time

    val timeDiffInSeconds = abs(timeDiff / SECOND)
    val timeDiffInMinutes = abs(timeDiff / MINUTE)
    val timeDiffInHours = abs(timeDiff / HOUR)
    val timeDiffInDays = abs(timeDiff / DAY)

    val isFuture = timeDiff > 0

    return when {
        timeDiffInSeconds in 0..1 -> "только что"
        timeDiffInSeconds in 0..45 && !isFuture -> "несколько секунд назад"
        timeDiffInSeconds in 0..45 -> "через несколько секунд"
        timeDiffInSeconds in 45..75 && !isFuture -> "минуту назад"
        timeDiffInSeconds in 45..75 -> "через минуту"
        timeDiffInSeconds > 75 && timeDiffInMinutes < 45 -> getTimeStr(timeDiffInMinutes, TimeUnits.MINUTE, isFuture)
        timeDiffInMinutes in 45..75 && !isFuture -> "час назад"
        timeDiffInMinutes in 45..75 -> "через час"
        timeDiffInMinutes > 75 && timeDiffInHours < 22 -> getTimeStr(timeDiffInHours, TimeUnits.HOUR, isFuture)
        timeDiffInHours in 22..26 && !isFuture -> "день назад"
        timeDiffInHours in 22..26 -> "через день"
        timeDiffInHours > 26 && timeDiffInDays < 360 -> getTimeStr(timeDiffInDays, TimeUnits.DAY, isFuture)
        timeDiffInDays > 360 && !isFuture -> "более года назад"
        timeDiffInDays > 360 -> "более чем через год"
        else -> "неизвестно"
    }
}

fun getAddition(num: Long, units: TimeUnits): String {
    val toInt = (num % 10).toInt()

    fun minuteAddition(): String = when (toInt) {
        1 -> "минуту"
        in 2..4 -> "минуты"
        else -> "минут"
    }

    fun hourAddition(): String = when (toInt) {
        1 -> "час"
        in 2..4 -> "часа"
        else -> "часов"
    }

    fun dayAddition(): String = when (toInt) {
        1 -> "день"
        in 2..4 -> "дня"
        else -> "дней"
    }

    return when (units) {
        TimeUnits.MINUTE -> minuteAddition()
        TimeUnits.HOUR -> hourAddition()
        TimeUnits.DAY -> dayAddition()
        else -> ""
    }.toString()
}

fun getTimeStr(num: Long, units: TimeUnits, isFuture: Boolean): String {
    val addition = getAddition(num, units)

    return when {
        isFuture -> "через $num $addition"
        else -> "$num $addition назад"
    }
}

enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY
}