package com.systems.assignment.common

import okhttp3.logging.HttpLoggingInterceptor

object AppEnvironment {

    fun getServerUrl(): String {
        return "https://s3-eu-west-1.amazonaws.com/developer-application-test/"
    }

    fun getLogsLevel(): HttpLoggingInterceptor.Level {
        return HttpLoggingInterceptor.Level.BODY
    }
}