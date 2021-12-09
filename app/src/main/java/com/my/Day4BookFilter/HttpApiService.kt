package com.my.Day4BookFilter

import retrofit2.http.GET

interface HttpApiService {

    @GET("/books")
    suspend fun getBookDetails():List<BookFilterVaraiables>

}