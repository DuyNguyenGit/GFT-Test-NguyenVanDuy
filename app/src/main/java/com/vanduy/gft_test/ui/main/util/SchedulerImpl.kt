package com.vanduy.gft_test.ui.main.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SchedulerImpl @Inject constructor() : SchedulerFacade {
    override val main: Scheduler
        get() = AndroidSchedulers.mainThread()
    override val background: Scheduler
        get() = Schedulers.io()
}