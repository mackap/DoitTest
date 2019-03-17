package com.android.mackap.doittest.core_comp

import android.content.Context
import dagger.Component
import javax.inject.Singleton


/**
 *Created by Makarov on 14.03.2019
 */
@Singleton
@Component(modules = [AppModule::class, NetModule::class, LocalStorageModule::class])
interface CoreComponent {
    fun context(): Context
    fun localStorage():ILocalStorage
    fun apiService(): ApiService

}