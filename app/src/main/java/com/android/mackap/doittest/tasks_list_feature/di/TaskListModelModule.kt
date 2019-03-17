package com.android.mackap.doittest.tasks_list_feature.di

import com.android.mackap.doittest.core_comp.ApiService
import com.android.mackap.doittest.core_comp.ILocalStorage
import com.android.mackap.doittest.tasks_list_feature.TaskListModel
import dagger.Module
import dagger.Provides

@Module
class TaskListModelModule {

    @Provides
    @TaskListScope
    fun provideAuthModel(
        apiService: ApiService,
        localStorage: ILocalStorage
    ): TaskListMVP.ITaskListModel {
        return TaskListModel(apiService, localStorage)
    }
}
