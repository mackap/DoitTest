package com.android.mackap.doittest.edit_task_feature

import com.android.mackap.doittest.core_comp.pojo.Task
import io.reactivex.Single
import okhttp3.ResponseBody

/**
 *Created by Makarov on 16.03.2019
 */
interface IEditTaskMVP {
    interface IEditTaskPresenter {
        fun getEditableTask(): Task?
        fun deleteCurrentTask()
        fun addNewTask(task: Task)
        fun clearSelectedTask()
        fun setView(editTaskActivity: IEditTaskView?)
        fun updateTask(id: Int, task: Task)
    }

    interface IEditTaskModel {
        fun getCurrentTask(): Task?
        fun addNewTask(task: Task): Single<ResponseBody>
        fun clearSelectionTask()
        fun updateTask(id: Int, task: Task): Single<ResponseBody>
        fun deleteTask(): Single<ResponseBody>
    }

    interface IEditTaskView {
        fun showTaskList()
        fun showErrorMessage(message: String)
    }
}