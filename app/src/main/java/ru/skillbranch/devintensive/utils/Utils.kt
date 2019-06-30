package ru.skillbranch.devintensive.utils

/**
 * Created by nkazakova on 2019-06-30.
 */
object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        return when (fullName?.trim()) {
            null, "" -> null to null
            else -> {
                val parts: List<String>? = fullName.split(delimiters = *arrayOf(" "))

                val firstName: String? = parts?.getOrNull(index = 0)
                val lastName: String? = parts?.getOrNull(index = 1)

                firstName to lastName
            }
        }
    }
}