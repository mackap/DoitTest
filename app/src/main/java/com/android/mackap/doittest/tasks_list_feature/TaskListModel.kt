package com.android.mackap.doittest.tasks_list_feature

import com.android.mackap.doittest.core_comp.ApiService
import com.android.mackap.doittest.core_comp.ILocalStorage
import com.android.mackap.doittest.core_comp.pojo.Task
import com.android.mackap.doittest.tasks_list_feature.di.TaskListMVP
import com.android.mackap.doittest.core_comp.pojo.TaskListResp
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 *Created by Makarov on 14.03.2019
 */
class TaskListModel(apiService: ApiService, localStorage: ILocalStorage) :
    TaskListMVP.ITaskListModel {
    var mApiService = apiService
    var mLocalStorage = localStorage
    lateinit var mTaskList: List<Task>

    override fun getToken(): String? {
        return mLocalStorage.getToken()
    }

    override fun loadTasks(): Single<TaskListResp> {
        return mApiService.getTasks(mLocalStorage.getToken().toString(), 0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    override fun setTaskList(taskList: List<Task>?) {
        mTaskList = taskList!!
    }
    override fun getTaskList():List<Task>?{
        return mTaskList
    }

    override fun clearSelectedTask() {
        mLocalStorage.setSelectedTask(null)
    }

    override fun setSelectedTaskId(itemId: Long?) {
        mLocalStorage.setSelectedTaskId(itemId)
    }

    override fun clearUserData() {
        mLocalStorage.setSelectedTaskId(0)
        mLocalStorage.setSelectedTask(null)
        mLocalStorage.saveToken(null)
        mTaskList = Collections.emptyList()
    }
}