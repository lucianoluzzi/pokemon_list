package com.lucianoluzzi.tests.extensions

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import java.util.concurrent.Callable

class OverrideSchedulersExtension : AfterEachCallback, BeforeEachCallback {

    private val mSchedulerInstance = Schedulers.trampoline()
    private val schedulerFunc: (Scheduler) -> Scheduler = { mSchedulerInstance }
    private val schedulerLazyFunc: (Callable<Scheduler>) -> Scheduler = { mSchedulerInstance }

    @Throws(Exception::class)
    override fun beforeEach(context: ExtensionContext) {
        RxAndroidPlugins.reset()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerLazyFunc)

        RxJavaPlugins.reset()
        RxJavaPlugins.setIoSchedulerHandler(schedulerFunc)
        RxJavaPlugins.setNewThreadSchedulerHandler(schedulerFunc)
        RxJavaPlugins.setComputationSchedulerHandler(schedulerFunc)
    }

    @Throws(Exception::class)
    override fun afterEach(context: ExtensionContext) {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }
}