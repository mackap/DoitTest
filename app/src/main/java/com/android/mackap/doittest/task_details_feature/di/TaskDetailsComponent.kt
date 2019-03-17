package com.android.mackap.doittest.task_details_feature.di

import com.android.mackap.doittest.core_comp.CoreComponent
import com.android.mackap.doittest.task_details_feature.TaskDetailsActivity
import dagger.Component

/**
 *Created by Makarov on 16.03.2019
 */
@TaskDetailsScope
@Component(dependencies = [CoreComponent::class],
              modules = [TaskDetailModule::class])
interface TaskDetailsComponent {
    fun injectIntoActivity(taskDetailsActivity: TaskDetailsActivity)
}