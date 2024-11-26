package com.example.studentmanager

import java.io.Serializable

data class StudentModel(
    var name: String,
    var studentId: String
) : Serializable

