package com.example.fhict_companion_app_group1.models

class Course(
    val subject: String,
    private val start: String,
    private val end: String,
    private val room: String,
    private val teacherAbbreviation: String,
) {

    fun getTimeSlot(): String {
        return start.substring(11, 16).plus(" - ")
            .plus(end.substring(11, 16))
    }

    fun getDate(): String {
        return start.substring(0, 10)
    }

    fun getDetails(): String {
        return room.plus(" ").plus(teacherAbbreviation).plus("\r\n")
    }

}

