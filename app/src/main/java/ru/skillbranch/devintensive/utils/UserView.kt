package ru.skillbranch.devintensive.utils

/**
 * Created by nkazakova on 2019-06-30.
 */
class UserView(
    val id: String,
    val fullName: String,
    val nickName: String,
    val avatar: String? = null,
    val status: String = "offline",
    val initials: String?
) {
    fun printMe() = println(
        """
        id: $id
        fullName: $fullName
        nickName: $nickName
        avatar: $avatar
        status: $status
        initials: $initials
    """.trimIndent()
    )
}