package com.android.mackap.doittest.edit_task_feature.di

import com.android.mackap.doittest.core_comp.ApiService
import com.android.mackap.doittest.core_comp.ILocalStorage
import com.android.mackap.doittest.edit_task_feature.EditTaskModel
import com.android.mackap.doittest.edit_task_feature.EditTaskPresenter
import com.android.mackap.doittest.edit_task_feature.IEditTaskMVP
import dagger.Module
import dagger.Provides

/**
 *Created by Makarov on 16.03.2019
 */
@Module
class EditTaskModule {
    @Provides
    @EditTaskScope
    fun provideEditTaskModel(apiService:ApiService, localStorage: ILocalStorage):IEditTaskMVP.IEditTaskModel{
        return EditTaskModel(apiService ,localStorage)
    }

    @Provides
    @EditTaskScope
    fun provideEditTaskPresenter(editTaskModel: IEditTaskMVP.IEditTaskModel):IEditTaskMVP.IEditTaskPresenter{
        return EditTaskPresenter(editTaskModel)
    }
}