package com.android.mackap.doittest.edit_task_feature.di

import com.android.mackap.doittest.core_comp.CoreComponent
import com.android.mackap.doittest.edit_task_feature.EditTaskActivity
import dagger.Component

/**
 *Created by Makarov on 16.03.2019
 */
@EditTaskScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [EditTaskModule::class]
)
interface EditTaskComponent {
    fun injectIntoActivity(activity: EditTaskActivity)
}