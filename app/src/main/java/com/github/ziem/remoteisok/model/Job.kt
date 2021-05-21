package com.github.ziem.remoteisok.model

import java.time.OffsetDateTime

data class Job(
    val id: Long,
    val position: String,
    val description: String,
    val company: Company,
    val url: String,
    val date: OffsetDateTime,
    val location: String,
    val tags: List<String>,
) {
    fun isWorldwide(): Boolean = location == "Worldwide"
    fun hasLocation(): Boolean = location.isNotBlank()

    companion object {
        fun empty(): Job = Job(
            -1,
            "",
            "",
            Company("", null),
            "",
            OffsetDateTime.now(),
            "",
            emptyList()
        )
    }
}