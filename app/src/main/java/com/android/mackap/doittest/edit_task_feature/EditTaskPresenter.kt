package com.android.mackap.doittest.edit_task_feature

import android.annotation.SuppressLint
import com.android.mackap.doittest.core_comp.ScreenState
import com.android.mackap.doittest.core_comp.pojo.Task

/**
 *Created by Makarov on 16.03.2019
 */
class EditTaskPresenter(editTaskModel: IEditTaskMVP.IEditTaskModel) : IEditTaskMVP.IEditTaskPresenter {
    val mEditTaskModel = editTaskModel
    var mEditTaskView: IEditTaskMVP.IEditTaskView? = null
    override fun getEditableTask(): Task? {
        return mEditTaskModel.getCurrentTask()
    }

    @SuppressLint("CheckResult")
    override fun deleteCurrentTask() {
        mEditTaskModel.deleteTask()
            .subscribe({
                mEditTaskView?.showTaskList()
            }, {
                mEditTaskView?.showErrorMessage(it.localizedMessage)
            })
    }

    @SuppressLint("CheckResult")
    override fun addNewTask(task: Task) {
        mEditTaskModel.addNewTask(task)
            .subscribe({
                mEditTaskView?.showTaskList()
            }, {
                mEditTaskView?.showErrorMessage(it.localizedMessage)
            })
    }

    @SuppressLint("CheckResult")
    override fun updateTask(id: Int, task: Task) {
        mEditTaskModel.updateTask(id, task)
            .subscribe({
                mEditTaskModel.clearSelectionTask()
                mEditTaskView?.showTaskList()
            }, {
                mEditTaskView?.showErrorMessage(it.localizedMessage)
            })
    }

    override fun clearSelectedTask() {
        mEditTaskModel.clearSelectionTask()
    }

    override fun setView(editTaskActivity: IEditTaskMVP.IEditTaskView?) {
        mEditTaskView = editTaskActivity
    }
}