package ru.skillbranch.devintensive.models

/**
 * Created by nkazakova on 2019-07-01.
 */
class Chat(
    val id: String,
    val members: MutableList<User> = mutableListOf(),
    val messages: MutableList<BaseMessage> = mutableListOf()
) {
}
