package com.vanduy.gft_test.ui.main.data.api

import com.vanduy.gft_test.ui.main.data.PhotoItem
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val BASE_URL = "https://609f8ee5c512c20017dcd55b.mockapi.io/"
    }

    @GET("MockItems")
    fun getItems(): Single<List<PhotoItem>>
}