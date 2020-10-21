package com.bendrisstarek.rabobankassessment.datasource.remote.response

import com.bendrisstarek.rabobankassessment.datasource.local.entity.Payment
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * this class represents the login response
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class MakePaymentResponse {
    @JsonProperty("response")
    var response: List<Payment>? = null
    override fun toString(): String {
        return "MakePaymentResponse{" +
                "response=" + response +
                '}'
    }

}