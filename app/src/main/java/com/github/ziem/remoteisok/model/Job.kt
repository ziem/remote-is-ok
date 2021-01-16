package com.github.ziem.remoteisok.model

data class Job(
    val position: String,
    val description: String,
    val company: Company,
    val url: String,
    val date: String, // convert to 310 date
    val location: String,
    val tags: List<String>,
) {
    fun isWorldwide(): Boolean = location == "Worldwide"
    fun hasLocation(): Boolean = location.isNotBlank()

    companion object {
        fun empty(): Job = Job(
            "",
            "",
            Company("", null),
            "",
            "",
            "",
            emptyList()
        )
    }
}