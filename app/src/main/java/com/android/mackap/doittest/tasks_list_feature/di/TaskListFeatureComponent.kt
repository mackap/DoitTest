package com.android.mackap.doittest.tasks_list_feature.di

import com.android.mackap.doittest.core_comp.CoreComponent
import com.android.mackap.doittest.tasks_list_feature.TasksListActivity
import dagger.Component

/**
 *Created by Makarov on 14.03.2019
 */
@Component(dependencies = [CoreComponent::class],
           modules = [TaskListModelModule::class,
               TaskListPresenterModule::class]
)
@TaskListScope
interface TaskListFeatureComponent {
    fun injectIntoActivity(activity: TasksListActivity)
}