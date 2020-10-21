package com.bendrisstarek.rabobankassessment.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * this class represents the payment's model
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
class Payment {
    @PrimaryKey
    @JsonProperty("id")
    var id = 0

    @JsonProperty("amount")
    var amount: String? = null

    @JsonProperty("description")
    var description: String? = null

    @JsonProperty("counterparty")
    var counterparty: String? = null
    override fun toString(): String {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", counterparty=" + counterparty +
                '}'
    }

}