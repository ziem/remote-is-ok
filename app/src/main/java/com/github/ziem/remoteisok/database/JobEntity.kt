package com.github.ziem.remoteisok.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity(tableName = "job")
data class JobEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "date") val date: OffsetDateTime,
    @ColumnInfo(name = "company") val company: String,
    @ColumnInfo(name = "company_logo_url") val companyLogoUrl: String?,
    @ColumnInfo(name = "position") val position: String,
    @ColumnInfo(name = "tags") val tags: List<String> = emptyList(),
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "original") val original: Boolean? = false,
    @ColumnInfo(name = "url") val url: String,
)
