package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Created by nkazakova on 2019-06-30.
 */
data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = Date(),
    var isOnline: Boolean = false
) {
    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(
        id = id,
        firstName = "John",
        lastName = "Doe $id"
    )

    private constructor(builder: Builder) : this(
        id = builder.id,
        firstName = builder.firstName,
        lastName = builder.lastName,
        avatar = builder.avatar,
        rating = builder.rating,
        respect = builder.respect,
        lastVisit = builder.lastVisit,
        isOnline = builder.isOnline
    )

    companion object Factory {
        private var lastId: Int = -1

        fun makeUser(fullName: String?): User {
            lastId++

            val (firstName: String?, lastName: String?) = Utils.parseFullName(fullName)

            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }

    class Builder {
        internal var id: String = UUID.randomUUID().toString()
            private set
        internal var firstName: String? = null
            private set
        internal var lastName: String? = null
            private set
        internal var avatar: String? = null
            private set
        internal var rating: Int = 0
            private set
        internal var respect: Int = 0
            private set
        internal var lastVisit: Date? = Date()
            private set
        internal var isOnline: Boolean = false
            private set

        fun id(id: String) = apply { this.id = id }
        fun firstName(firstName: String?) = apply { this.firstName = firstName }
        fun lastName(lastName: String?) = apply { this.lastName = lastName }
        fun avatar(avatar: String?) = apply { this.avatar = avatar }
        fun rating(rating: Int) = apply { this.rating = rating }
        fun respect(respect: Int) = apply { this.respect = respect }
        fun lastVisit(lastVisit: Date?) = apply { this.lastVisit = lastVisit }
        fun isOnline(isOnline: Boolean) = apply { this.isOnline = isOnline }
        fun build() = User(this)
    }
}