package com.android.mackap.doittest.task_details_feature

import android.annotation.SuppressLint
import android.util.Log
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
class TaskDetailsModel(apiService: ApiService, localStorage: ILocalStorage) : ITaskDetailsMVP.ITaskDetailsModel {

    val mApiService = apiService
    val mLocalStorage = localStorage
    var mCurrentTask: Task? = null
  //  var mTaskId: Long = 0

    override fun setCurrentTask(task: Task?) {
        mCurrentTask = task
        mLocalStorage.setSelectedTask(task)
  }

    @SuppressLint("CheckResult")
    override fun getTaskDetails(): Single<Task?> {
        /// todo be able to create some mini cash
        /**
    if(mCurrentTask!=null && mLocalStorage.getSelectedTaskId()!!.toInt()==mCurrentTask?.id){
        return Single.just(mCurrentTask)
    }
    */
        return mApiService.getTaskDetails(mLocalStorage.getToken().toString(),
            mLocalStorage.getSelectedTaskId()!!.toLong())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { mCurrentTask = it.task }
            .map { it.task }
    }

    override fun deteteTask(): Single<ResponseBody> {
        return mApiService.deleteTask(mLocalStorage.getToken().toString(),
            mLocalStorage.getSelectedTaskId()!!.toLong())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                mLocalStorage.setSelectedTaskId(0)
                mLocalStorage.setSelectedTask(null)
                mCurrentTask = null
            }
    }


}