package com.systems.assignment.common.state

data  class UIState<T>(
    val isDefault: Boolean = false,
    val isLoading: Boolean = false,
    val networkError: Boolean = false,
    val actionTaken: Boolean = false,
    var error: String = "",
    var response: T? = null,
    val responseCode: String = "",
)