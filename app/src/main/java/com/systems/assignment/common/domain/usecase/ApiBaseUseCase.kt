package com.systems.assignment.common.domain.usecase


import com.systems.assignment.common.data.remote.ApiErrorHandle
import com.systems.assignment.common.data.remote.Outcome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class ApiBaseUseCase<Response, in Params>(
    private val apiErrorHandler: ApiErrorHandle?,
) where Response : Any {

    abstract suspend fun run(params: Params): Response

    operator fun invoke(params: Params): Flow<Outcome<Response>> =
        channelFlow {
            send(Outcome.Start())
            try {
                val response = run(params)
                send(Outcome.Success(response))
            } catch (io: IOException) {
                send(Outcome.NetworkError(io))
            } catch (ex: Exception) {
                if (ex is HttpException || ex is UnknownHostException || ex is SocketTimeoutException) {
                    send(Outcome.NetworkError(ex))
                } else {
                    send(Outcome.Failure(apiErrorHandler?.traceErrorException(ex)))
                }
            } finally {
                send(Outcome.End())
            }

        }.flowOn(Dispatchers.IO)

}