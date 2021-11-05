package com.lucianoluzzi.navigation_impl.di

import com.lucianoluzzi.navigation.Navigator
import com.lucianoluzzi.navigation_impl.NavigatorImpl
import org.koin.dsl.module

object NavigationImplModule {
    val module = module {
        single<Navigator> {
            NavigatorImpl()
        }
    }
}