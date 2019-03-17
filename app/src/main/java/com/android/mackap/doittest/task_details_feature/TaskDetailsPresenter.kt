package com.android.mackap.doittest.task_details_feature

import android.annotation.SuppressLint
import com.android.mackap.doittest.core_comp.ScreenState

/**
 *Created by Makarov on 16.03.2019
 */
class TaskDetailsPresenter(taskDetailModel: ITaskDetailsMVP.ITaskDetailsModel)
    : ITaskDetailsMVP.ITaskDetailsPresenter {
    var mTaskDetailsModel = taskDetailModel
    var mTaskDetailsView: ITaskDetailsMVP.ITaskDetailsView? = null
    var mCurrenViewState = ScreenState.FIRST_OPEN
    private var mCurrentErrorStr: String? = null

    override fun getCurrentViewState(): ScreenState {
        return mCurrenViewState
    }



    override fun setView(taskDetailsActivity: ITaskDetailsMVP.ITaskDetailsView?) {
       mTaskDetailsView = taskDetailsActivity
    }


    @SuppressLint("CheckResult")
    override fun getTaskDetails() {
        mTaskDetailsModel.getTaskDetails()
            .doOnSubscribe {
                mCurrenViewState = ScreenState.SHOW_PROGRESS
                mTaskDetailsView?.showProgress(true)
              }
            .subscribe({
                mCurrenViewState = ScreenState.SHOW_DATA
                mTaskDetailsModel.setCurrentTask(it)
                mTaskDetailsView?.updateData(it)
            },
                {
                    mCurrenViewState = ScreenState.SHOW_ERROR
                    mCurrentErrorStr = it.localizedMessage
                    mTaskDetailsView?.showError()
                })
    }

    @SuppressLint("CheckResult")
    override fun deleteTask() {
        mTaskDetailsModel.deteteTask()
            .subscribe({
                mTaskDetailsView?.taskDeleted()
                mTaskDetailsModel.setCurrentTask(null)
            }, {
                mCurrentErrorStr = it.localizedMessage
                mCurrenViewState = ScreenState.SHOW_ERROR
                mTaskDetailsView?.showError()
            })
    }

    override fun getCurrentError(): String? {
        return mCurrentErrorStr
    }

}