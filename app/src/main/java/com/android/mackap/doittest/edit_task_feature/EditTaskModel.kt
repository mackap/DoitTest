package com.android.mackap.doittest.edit_task_feature

import android.annotation.SuppressLint
import com.android.mackap.doittest.core_comp.ApiService
import com.android.mackap.doittest.core_comp.ILocalStorage
import com.android.mackap.doittest.core_comp.pojo.Task
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

/**
 *Created by Makarov on 16.03.2019
 */
class EditTaskModel(apiService: ApiService, localStorage: ILocalStorage):IEditTaskMVP.IEditTaskModel {


    val mApiService = apiService
    val mLocalStorage = localStorage

    override fun getCurrentTask(): Task? {
        return mLocalStorage.getSelectedTask()
    }

    @SuppressLint("CheckResult")
    override fun addNewTask(task: Task):Single<ResponseBody> {
      return mApiService.addTask(mLocalStorage.getToken().toString(),
           task.title.toString(), task.dueBy!!.toLong(), task.priority.toString())
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateTask(id: Int, task: Task): Single<ResponseBody> {
        return mApiService.updateTask(mLocalStorage.getToken().toString(), id,
            task.title.toString(), task.dueBy!!.toLong(), task.priority.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteTask(): Single<ResponseBody> {
        return mApiService.deleteTask(
            mLocalStorage.getToken().toString(),
            mLocalStorage.getSelectedTaskId()!!.toLong()
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                mLocalStorage.setSelectedTaskId(0)
                mLocalStorage.setSelectedTask(null)
            }
    }

    override fun clearSelectionTask() {
        mLocalStorage.setSelectedTaskId(null)
        mLocalStorage.setSelectedTask(null)
    }
}