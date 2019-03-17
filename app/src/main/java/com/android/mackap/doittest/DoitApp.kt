package com.android.mackap.doittest

import android.app.Application
import com.android.mackap.doittest.authorisation_feature.di.AuthComponent
import com.android.mackap.doittest.authorisation_feature.di.DaggerAuthComponent
import com.android.mackap.doittest.core_comp.AppModule
import com.android.mackap.doittest.core_comp.CoreComponent
import com.android.mackap.doittest.core_comp.DaggerCoreComponent
import com.android.mackap.doittest.edit_task_feature.di.DaggerEditTaskComponent
import com.android.mackap.doittest.edit_task_feature.di.EditTaskComponent
import com.android.mackap.doittest.task_details_feature.di.DaggerTaskDetailsComponent
import com.android.mackap.doittest.task_details_feature.di.TaskDetailsComponent
import com.android.mackap.doittest.tasks_list_feature.di.DaggerTaskListFeatureComponent
import com.android.mackap.doittest.tasks_list_feature.di.TaskListFeatureComponent

/**
 *Created by Makarov on 14.03.2019
 */
class DoitApp : Application() {
    lateinit var mCoreComponent: CoreComponent
    var mAuthComponent: AuthComponent? = null
    var mTaskListComponent: TaskListFeatureComponent? = null
    var mTaskDetailsComponent: TaskDetailsComponent? = null
    var mEditTaskComponent: EditTaskComponent? = null

    override fun onCreate() {
        super.onCreate()
        mCoreComponent = DaggerCoreComponent.builder().appModule(AppModule(this)).build()
    }

    fun getAuthComponent(): AuthComponent? {
        if (mAuthComponent == null) {
            mAuthComponent = DaggerAuthComponent.builder().coreComponent(mCoreComponent).build()
        }
        return mAuthComponent
    }

    fun getTaskListComponent(): TaskListFeatureComponent? {
        if (mTaskListComponent == null) {
            mTaskListComponent = DaggerTaskListFeatureComponent
                .builder()
                .coreComponent(mCoreComponent).build()
        }
        return mTaskListComponent
    }

    fun getTaskDetailsComponent(): TaskDetailsComponent? {
        if (mTaskDetailsComponent == null) {
            mTaskDetailsComponent = DaggerTaskDetailsComponent
                .builder()
                .coreComponent(mCoreComponent)
                .build()
        }
        return mTaskDetailsComponent
    }

    fun getEditTaskComponent(): EditTaskComponent? {
        if (mEditTaskComponent == null) {
            mEditTaskComponent = DaggerEditTaskComponent.builder()
                .coreComponent(mCoreComponent)
                .build()
        }
        return mEditTaskComponent
    }
}