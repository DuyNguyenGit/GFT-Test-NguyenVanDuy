package com.vanduy.gft_test.ui.main.domain

import io.reactivex.Single

interface DataProvider<T> {
    fun fetchItems(): Single<List<T>>
}