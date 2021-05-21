package com.github.ziem.remoteisok.mapper

import com.github.ziem.remoteisok.model.Company
import com.github.ziem.remoteisok.model.Job
import com.github.ziem.remoteisok.model.JobResponse

fun JobResponse.asJob(): Job {
    val tags = tags.map { tag -> tag.replace("_", " ") }
    val companyLogo = if (company_logo.isNullOrBlank() || company_logo == "https://cdn.sstatic.net/careers/Img/ico-no-company-logo.svg") {
        null
    } else {
        company_logo
    }

    return Job(
        id,
        position!!,
        description ?: "No description",
        Company(company!!, companyLogo),
        url,
        date,
        location,
        tags
    )
}