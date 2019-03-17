package com.android.mackap.doittest.authorisation_feature.di

import com.android.mackap.doittest.authorisation_feature.AuthorisationActivity
import com.android.mackap.doittest.core_comp.CoreComponent
import dagger.Component

/**
 *Created by Makarov on 14.03.2019
 */
@Component(dependencies = [CoreComponent::class],
           modules = [AuthorisationModelModule::class,
                     AuthorisationPresenterModule::class]
)
@AuthorisationScope
interface AuthComponent {
    fun injectIntoActivity(activity: AuthorisationActivity)
}