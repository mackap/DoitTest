package com.android.mackap.doittest.tasks_list_feature

import android.annotation.SuppressLint
import com.android.mackap.doittest.core_comp.ScreenState
import com.android.mackap.doittest.core_comp.pojo.Task
import com.android.mackap.doittest.tasks_list_feature.di.TaskListMVP

class TaskListPresenter(taskListModel: TaskListMVP.ITaskListModel) : TaskListMVP.ITaskListPresenter {

    private var mTaskListModel = taskListModel
    private var mTaskListView: TaskListMVP.ITaskListView? = null
    var mCurrentScreenState: ScreenState = ScreenState.FIRST_OPEN
    var mCurrentErrorMessage = ""


    override fun getCurrentState(): ScreenState {
        return mCurrentScreenState
    }

    override fun getToken(): String? {
        return mTaskListModel.getToken()
    }

    override fun getCurrentErrorMessage(): String {

        return mCurrentErrorMessage
    }

    @SuppressLint("CheckResult")
    override fun loadData() {
        mTaskListModel.loadTasks()
            .doOnSubscribe {
                mTaskListView?.showProgress(true)
            }
            .subscribe({
                mCurrentScreenState = ScreenState.SHOW_DATA
                var taskList = it.tasks
                mTaskListModel.setTaskList(taskList)
                mTaskListView?.showData(taskList)
            }, {
                mCurrentScreenState = ScreenState.SHOW_ERROR
                var errorMessage = it.message.toString()
                mCurrentErrorMessage = errorMessage
                mTaskListView?.showErrorMessage(errorMessage)
            })
    }


    override fun getData(): List<Task>? {
        return mTaskListModel.getTaskList()
    }

    override fun setView(taskListView: TaskListMVP.ITaskListView?) {
        mTaskListView = taskListView
    }

    override fun setCurrentState(screenState: ScreenState) {
        mCurrentScreenState = screenState
    }

    override fun clearSeletedTask() {
        mTaskListModel.clearSelectedTask()
    }

    override fun setSelectedTaskId(itemId: Long?) {
        mTaskListModel.setSelectedTaskId(itemId)
    }

    override fun clearUserData() {
        mTaskListModel.clearUserData()
        mCurrentScreenState = ScreenState.FIRST_OPEN
    }
}