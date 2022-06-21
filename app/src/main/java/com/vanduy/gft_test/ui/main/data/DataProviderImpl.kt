package com.vanduy.gft_test.ui.main.data

import com.vanduy.gft_test.ui.main.data.api.ApiService
import com.vanduy.gft_test.ui.main.domain.DataProvider
import io.reactivex.Single
import javax.inject.Inject

class DataProviderImpl @Inject constructor(private val apiService: ApiService) : DataProvider<PhotoItem> {
    override fun fetchItems(): Single<List<PhotoItem>> {
        return apiService.getItems()
    }
}