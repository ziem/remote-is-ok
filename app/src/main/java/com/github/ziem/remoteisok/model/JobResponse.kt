package com.github.ziem.remoteisok.model

data class JobResponse(
    val id: Long,
    val position: String?,
    val description: String?,
    val url: String,
    val date: String,
    val company: String?,
    val company_logo: String?,
    val location: String,
    val tags: List<String>? = emptyList(),
    val original: Boolean? = false,
) {
    fun isValid(): Boolean {
        return position?.isNotBlank() == true && company?.isNotBlank() == true
    }
}