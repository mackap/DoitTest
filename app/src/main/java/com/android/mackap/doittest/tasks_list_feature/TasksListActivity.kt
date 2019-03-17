package com.android.mackap.doittest.tasks_list_feature

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.mackap.doittest.DoitApp
import com.android.mackap.doittest.R
import com.android.mackap.doittest.authorisation_feature.AuthorisationActivity
import com.android.mackap.doittest.core_comp.IRecItemClickListener
import com.android.mackap.doittest.core_comp.ScreenState
import com.android.mackap.doittest.core_comp.pojo.Task
import com.android.mackap.doittest.edit_task_feature.EditTaskActivity
import com.android.mackap.doittest.task_details_feature.TaskDetailsActivity
import com.android.mackap.doittest.tasks_list_feature.di.TaskListMVP
import kotlinx.android.synthetic.main.activity_tasks_list.*
import javax.inject.Inject

class TasksListActivity : AppCompatActivity(), TaskListMVP.ITaskListView, IRecItemClickListener {

    @Inject
    lateinit var mTaskListPresenter: TaskListMVP.ITaskListPresenter
    var mRecAdapter: TaskListRecAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as DoitApp).getTaskListComponent()?.injectIntoActivity(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks_list)
        swipe_activity_tasks_list.setOnRefreshListener {
            mTaskListPresenter.loadData()
        }

        fab.setOnClickListener { view ->
            mTaskListPresenter.clearSeletedTask()
            startNewTaskActivity()
        }
        setTitle(R.string.my_tasks)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.sorted_by_name -> mRecAdapter?.sortedByName(false)
            R.id.sorted_by_name_reverse -> mRecAdapter?.sortedByName(true)
            R.id.sorted_by_priority -> mRecAdapter?.sortedByPriority(false)
            R.id.sorted_by_priority_reverse -> mRecAdapter?.sortedByPriority(true)
            R.id.action_logout -> logout()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        mTaskListPresenter.clearUserData()
        startActivity(Intent(this, AuthorisationActivity::class.java))
        finish()
    }

    private fun startNewTaskActivity() {
        startActivity(Intent(this, EditTaskActivity::class.java))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        mTaskListPresenter.setCurrentState(ScreenState.FIRST_OPEN)
    }

    override fun onResume() {
        super.onResume()
        mTaskListPresenter.setView(this)
        showCurrentState(mTaskListPresenter.getCurrentState())
    }


    private fun showCurrentState(currenScreenState: ScreenState) {
        when (currenScreenState) {
            ScreenState.FIRST_OPEN -> mTaskListPresenter.loadData()
            ScreenState.SHOW_DATA -> showData(mTaskListPresenter.getData())
            ScreenState.SHOW_PROGRESS -> showProgress(true)
            ScreenState.SHOW_ERROR -> showErrorMessage(mTaskListPresenter.getCurrentErrorMessage())
        }
    }

    override fun showData(tasks: List<Task>?) {
        showProgress(false)
        if (mRecAdapter == null) {
            mRecAdapter = TaskListRecAdapter(tasks!!, this)
        } else {
            mRecAdapter?.updateData(tasks)
        }
        rec_activity_tasks_list.layoutManager = LinearLayoutManager(this)
        rec_activity_tasks_list.adapter = mRecAdapter
    }


    override fun showErrorMessage(message: String) {
        showProgress(false)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress(isShow: Boolean) {
        swipe_activity_tasks_list.isRefreshing = isShow
    }

    override fun onPause() {
        super.onPause()
        mTaskListPresenter.setView(null)
    }

    override fun onItemClick(itemId: Long?) {
        //  Log.d("TaskListActiv", "----------- itemId="+itemId)
        mTaskListPresenter.setSelectedTaskId(itemId)
        var intent = Intent(this, TaskDetailsActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
       finish()
    }
}
