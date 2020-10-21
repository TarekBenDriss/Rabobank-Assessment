package com.bendrisstarek.rabobankassessment.datasource.remote.params

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * this class represents the salary param
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class PaymentParam {
    @JsonProperty("amount")
    var amount: String? = null

    @JsonProperty("description")
    var description: String? = null
    override fun toString(): String {
        return "PaymentParam(amount=$amount, description=$description)"
    }


}