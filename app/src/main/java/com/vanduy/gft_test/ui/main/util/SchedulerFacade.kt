package com.vanduy.gft_test.ui.main.util

import io.reactivex.Scheduler

interface SchedulerFacade {
    val main: Scheduler
    val background: Scheduler
}