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
                val words = parts?.filter { it.isNotEmpty() }
                val firstName: String? = words?.getOrNull(index = 0)
                val lastName: String? = words?.getOrNull(index = 1)

                firstName to lastName
            }
        }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val transMap = mapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya"
        )

        val result = StringBuilder()
        for (char in payload) {
            if (char.toString() == " ") {
                result.append(divider)
                continue
            }

            val sToLowerCase = char.toString().toLowerCase()

            if (sToLowerCase !in transMap) {
                result.append(char.toString()) // различные символы и английские буквы переносятся как есть
                continue
            }

            if (transMap[sToLowerCase] != null && transMap[char.toString()] == null) { // сохранять заглавные буквы
                result.append(transMap[sToLowerCase]?.get(0)?.toUpperCase())

                if (transMap[sToLowerCase]?.length == 2) { // второй символ в транслитерации делать прописным
                    result.append(transMap[sToLowerCase]?.get(1))
                }
            } else {
                result.append(transMap[sToLowerCase])
            }
        }

        return result.toString()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val result = StringBuilder()

        if (firstName == null && lastName == null) {
            return null
        }

        if (firstName != null && firstName.trim() != "") {
            result.append(firstName.get(0).toUpperCase())
        }

        if (lastName != null && lastName.trim() != "") {
            result.append(lastName.get(0).toUpperCase())
        }

        return when {
            result.toString() == "" -> null
            else -> result.toString()
        }
    }
}