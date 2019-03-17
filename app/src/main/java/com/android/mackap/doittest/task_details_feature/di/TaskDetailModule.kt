package com.android.mackap.doittest.task_details_feature.di

import com.android.mackap.doittest.core_comp.ApiService
import com.android.mackap.doittest.core_comp.ILocalStorage
import com.android.mackap.doittest.core_comp.LocalStorage
import com.android.mackap.doittest.task_details_feature.ITaskDetailsMVP
import com.android.mackap.doittest.task_details_feature.TaskDetailsModel
import com.android.mackap.doittest.task_details_feature.TaskDetailsPresenter
import dagger.Module
import dagger.Provides

/**
 *Created by Makarov on 16.03.2019
 */
@Module
class TaskDetailModule {
    @Provides
    @TaskDetailsScope
    fun  provideModel(apiService: ApiService, localStorage: ILocalStorage):ITaskDetailsMVP.ITaskDetailsModel{
        return TaskDetailsModel(apiService, localStorage)
    }
    @Provides
    @TaskDetailsScope
    fun providePresenter(taskDetailModel: ITaskDetailsMVP.ITaskDetailsModel):ITaskDetailsMVP.ITaskDetailsPresenter{
        return TaskDetailsPresenter(taskDetailModel)
    }
}