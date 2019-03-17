package com.android.mackap.doittest.task_details_feature

import com.android.mackap.doittest.core_comp.ScreenState
import com.android.mackap.doittest.core_comp.pojo.Task
import io.reactivex.Single
import okhttp3.ResponseBody

/**
 *Created by Makarov on 16.03.2019
 */
interface ITaskDetailsMVP {
    interface ITaskDetailsPresenter{
        fun setView(taskDetailsActivity: ITaskDetailsView?)
        fun getCurrentViewState(): ScreenState
        fun getTaskDetails()
        fun getCurrentError(): String?
        fun deleteTask()
       }
    interface ITaskDetailsModel{
        fun getTaskDetails(): Single<Task?>
        fun setCurrentTask(it: Task?)
        fun deteteTask(): Single<ResponseBody>

    }
    interface ITaskDetailsView{
        fun showData()
        fun showError()
        fun showProgress(isShow: Boolean)
        fun updateData(taskDetails: Task?)
        fun taskDeleted()
    }
}