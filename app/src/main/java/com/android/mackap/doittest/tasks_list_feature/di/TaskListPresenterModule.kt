package com.android.mackap.doittest.tasks_list_feature.di

import com.android.mackap.doittest.tasks_list_feature.TaskListPresenter
import dagger.Module
import dagger.Provides

@Module
class TaskListPresenterModule {
    @Provides
    @TaskListScope
    fun provideTaskListPresenter(authModel: TaskListMVP.ITaskListModel): TaskListMVP.ITaskListPresenter {
        return TaskListPresenter(authModel)
    }

}
