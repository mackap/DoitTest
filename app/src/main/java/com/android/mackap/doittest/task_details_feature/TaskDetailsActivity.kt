package com.android.mackap.doittest.task_details_feature

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.android.mackap.doittest.DoitApp
import com.android.mackap.doittest.R
import com.android.mackap.doittest.core_comp.ScreenState
import com.android.mackap.doittest.core_comp.pojo.Task
import com.android.mackap.doittest.edit_task_feature.EditTaskActivity
import com.android.mackap.doittest.tasks_list_feature.TasksListActivity
import kotlinx.android.synthetic.main.activity_task_details.*
import javax.inject.Inject


class TaskDetailsActivity : AppCompatActivity(), ITaskDetailsMVP.ITaskDetailsView {

    @Inject
    lateinit var mTaskDetailsPresenter: ITaskDetailsMVP.ITaskDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as DoitApp).getTaskDetailsComponent()?.injectIntoActivity(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_task_details)
        but_delete_task.setOnClickListener {
            mTaskDetailsPresenter.deleteTask()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.edit_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
          R.id.action_edit-> {
              startActivity(Intent(this, EditTaskActivity::class.java))
              finish()
          }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        mTaskDetailsPresenter.setView(this)
        showCurrentState(mTaskDetailsPresenter.getCurrentViewState())
    }

    override fun onPause() {
        super.onPause()
        mTaskDetailsPresenter.setView(null)

    }

    private fun showCurrentState(currentViewState: ScreenState) {
        when (currentViewState) {
            ScreenState.FIRST_OPEN -> getData()
            ScreenState.SHOW_PROGRESS -> showProgress(true)
            ScreenState.SHOW_DATA -> showData()
            ScreenState.SHOW_ERROR -> showError()
        }
    }

    override fun updateData(taskDetails: Task?) {
        showProgress(false)
        tv_title.text = taskDetails?.title
        tv_priority_value.text = taskDetails?.priority
        tv_date.text = taskDetails?.dueBy.toString()
    }

    private fun getData() {
        showProgress(true)
        mTaskDetailsPresenter.getTaskDetails()
    }

    override fun showProgress(isShow: Boolean) {
        if (isShow) {
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE
        }
    }

    override fun showError() {
        showProgress(false)
        Toast.makeText(this, mTaskDetailsPresenter.getCurrentError(), Toast.LENGTH_SHORT).show()
    }

    override fun showData() {
        mTaskDetailsPresenter.getTaskDetails()
    }

    override fun taskDeleted() {
      startActivity(Intent(this, TasksListActivity::class.java))
      finish()
    }
}
