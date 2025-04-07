package hnu.multimedia.androiddh.week5

import java.io.Serializable

data class ExamModel(
    val title: String,
    val date: String,
    val memo: String,
    val classroom: String,
    val range: String
) : Serializable
