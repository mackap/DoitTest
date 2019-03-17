package com.android.mackap.doittest.authorisation_feature.di

import com.android.mackap.doittest.authorisation_feature.AuthorisationMVP
import com.android.mackap.doittest.authorisation_feature.AuthorisationPresenter
import dagger.Module
import dagger.Provides

@Module
class AuthorisationPresenterModule {
    @Provides
    @AuthorisationScope
    fun provideAuthPresenter(authModel: AuthorisationMVP.IAuthorisationModel): AuthorisationMVP.IAuthorisationPresenter {
        return AuthorisationPresenter(authModel)
    }

}
