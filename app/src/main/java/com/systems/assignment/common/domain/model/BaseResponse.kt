package com.systems.assignment.common.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("responseCode")
    @Expose
    var responseCode: String? = null

    //@SerializedName("errorMsg")
    @SerializedName("responseMsg")
    @Expose
    var responseMsg: String? = null

    @SerializedName("errorCode")
    @Expose
    var errorCode: String? = null

    @SerializedName("errorMsg")
    @Expose
    var errorMsg: String? = null
}