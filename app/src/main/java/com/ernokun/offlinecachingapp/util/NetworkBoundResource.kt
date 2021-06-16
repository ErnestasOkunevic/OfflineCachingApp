package com.ernokun.offlinecachingapp.util

import kotlinx.coroutines.flow.*

/**
 * RequestType -> What I want to get from the API
 * (in this case: List<Cannabis>)
 *
 * ResultType -> What I receive from the database
 * (in this case: Flow<List<Cannabis>>)
 */
inline fun <ResultType, RequestType> networkBoundResource(
    // Gets data from the database.
    crossinline query: () -> Flow<ResultType>,

    // Gets data from the API.
    crossinline fetch: suspend () -> RequestType,

    // Saves the API request result to the database.
    crossinline saveFetchResult: suspend (RequestType) -> Unit,

    // Makes the decision whether the database data is stale
    // and the API needs to be called. (True by default)
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) { // Whether to fetch new data from the API
        emit(Resource.Loading(data))

        try {
            // Try to fetch API data and save it to the database.
            saveFetchResult(fetch())

            // If all went well, then get the new database data
            // and return it.
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            // If the API request failed -> return the
            // cached data and error message.
            query().map { Resource.Error(throwable.localizedMessage, it) }
        }
    } else { // If the currently cached data is sufficient.
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}