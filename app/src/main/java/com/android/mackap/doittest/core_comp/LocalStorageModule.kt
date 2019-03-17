package com.android.mackap.doittest.core_comp

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalStorageModule {
    private val sharedPrefName:String = "shared_pref_key"

    @Provides
    @Singleton
    fun provideSharedPref(context: Context): SharedPreferences {
        return context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideLocalData(sharedPreferences: SharedPreferences):ILocalStorage{
        return LocalStorage(sharedPreferences)
    }
}
