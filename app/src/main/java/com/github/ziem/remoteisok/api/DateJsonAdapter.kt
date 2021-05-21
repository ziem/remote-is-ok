package com.github.ziem.remoteisok.api

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.time.OffsetDateTime

class DateJsonAdapter: JsonAdapter<OffsetDateTime>() {
    override fun fromJson(reader: JsonReader): OffsetDateTime {
        return OffsetDateTime.parse(reader.nextString())
    }

    override fun toJson(writer: JsonWriter, value: OffsetDateTime?) {
        val string = value?.toString()
        writer.value(string)
    }
}