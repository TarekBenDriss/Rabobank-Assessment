package com.bendrisstarek.rabobankassessment.datasource.local.converter

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.io.IOException
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * this class represents a custom json date deserializer
 */
class CustomJsonDateDeserializer : JsonDeserializer<Date>() {
    /**
     * this function deserialize a date json to a date
     * @param jsonparser
     * @param deserializationcontext
     * @return
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jsonparser: JsonParser,
                             deserializationcontext: DeserializationContext): Date {
        val formatter: DateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US)
        val formatter2: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US)
        val dateStr = jsonparser.text
        return try {
            formatter.parse(dateStr) as Date
        } catch (e: ParseException) {
            try {
                formatter2.parse(dateStr) as Date
            } catch (e1: ParseException) {
                e1.printStackTrace()
                throw RuntimeException(e)
            }
        }
    }
}