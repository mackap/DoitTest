package com.android.mackap.doittest.core_comp

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(context: Context) {
     var mContext: Context = context

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return mContext
    }
}
