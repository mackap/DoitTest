package com.android.mackap.doittest.authorisation_feature.di

import com.android.mackap.doittest.authorisation_feature.AuthorisationMVP
import com.android.mackap.doittest.authorisation_feature.AuthorisationModel
import com.android.mackap.doittest.core_comp.ApiService
import com.android.mackap.doittest.core_comp.ILocalStorage
import dagger.Module
import dagger.Provides

@Module
class AuthorisationModelModule {

    @Provides
    @AuthorisationScope
    fun provideAuthModel(
        apiService: ApiService,
        localStorage: ILocalStorage
    ): AuthorisationMVP.IAuthorisationModel {
        return AuthorisationModel(apiService, localStorage)
    }
}
