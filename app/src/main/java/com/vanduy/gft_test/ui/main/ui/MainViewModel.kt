package com.vanduy.gft_test.ui.main.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vanduy.gft_test.ui.main.data.PhotoItem
import com.vanduy.gft_test.ui.main.domain.DataProvider
import com.vanduy.gft_test.ui.main.domain.ScreenState
import com.vanduy.gft_test.ui.main.util.SchedulerFacade
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataProvider: DataProvider<PhotoItem>,
    private val scheduler: SchedulerFacade
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private var _screenState: MutableLiveData<ScreenState<List<PhotoItem>>> = MutableLiveData()
    val screenState: LiveData<ScreenState<List<PhotoItem>>> = _screenState

    fun fetchItems() {
        _screenState.value = ScreenState.loading(true)
        compositeDisposable.add(dataProvider.fetchItems()
            .subscribeOn(scheduler.background)
            .observeOn(scheduler.main)
            .subscribe({
                _screenState.value = ScreenState.loading(false)
                _screenState.value = ScreenState.success(it)
            }, {
                _screenState.value = ScreenState.loading(false)
                _screenState.value = ScreenState.error(it)
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}