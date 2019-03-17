package com.android.mackap.doittest.tasks_list_feature.di

import com.android.mackap.doittest.core_comp.ScreenState
import com.android.mackap.doittest.core_comp.pojo.Task
import com.android.mackap.doittest.core_comp.pojo.TaskListResp
import io.reactivex.Single

/**
 *Created by Makarov on 14.03.2019
 */
interface TaskListMVP {
    interface ITaskListModel{
        fun getToken(): String?
        fun loadTasks(): Single<TaskListResp>
        fun setTaskList(taskList: List<Task>?)
        fun getTaskList(): List<Task>?
        fun clearSelectedTask()
         fun setSelectedTaskId(itemId: Long?)
        fun clearUserData()
    }
    interface ITaskListView{
        fun showErrorMessage(string: String)
        fun showProgress(isShow: Boolean)
        fun showData(tasks: List<Task>?)
    }
    interface ITaskListPresenter{
        fun getCurrentState(): ScreenState
        fun getToken(): String?
        fun getCurrentErrorMessage(): String
        fun loadData()
        fun setView(taskListView: ITaskListView?)
        fun getData(): List<Task>?
        fun setCurrentState(firsT_OPEN: ScreenState)
        fun clearSeletedTask()
         fun setSelectedTaskId(itemId: Long?)
        fun clearUserData()
    }
}