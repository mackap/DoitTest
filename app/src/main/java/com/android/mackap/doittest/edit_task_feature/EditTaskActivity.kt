package com.android.mackap.doittest.edit_task_feature

import android.app.DatePickerDialog
import android.content.Intent
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import com.android.mackap.doittest.DoitApp
import com.android.mackap.doittest.R
import com.android.mackap.doittest.core_comp.pojo.Task
import com.android.mackap.doittest.tasks_list_feature.TasksListActivity
import kotlinx.android.synthetic.main.activity_edit_task.*
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

class EditTaskActivity : AppCompatActivity(), IEditTaskMVP.IEditTaskView, View.OnClickListener,
    DatePickerDialog.OnDateSetListener {

    @Inject
    lateinit var mEditTaskPresenter: IEditTaskMVP.IEditTaskPresenter
    var mEditableTask: Task? = null
    lateinit var mPriorityButList: List<Button>
    val butLowText = "Low"
    val butNormalText = "Normal"
    val butHighText = "High"
    var mPriority: String = butLowText
    var mTaskDate: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as DoitApp).getEditTaskComponent()?.injectIntoActivity(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)

        mPriorityButList = listOf(but_low, but_normal, but_high)

        mEditableTask = mEditTaskPresenter.getEditableTask()

        if (mEditableTask != null) {
            setTitle(mEditableTask?.title)
            but_delete_task.setOnClickListener({ deleteTask() })
            et_title.setText(mEditableTask?.title)
            setPrioritBut(mEditableTask?.priority)
            mTaskDate = mEditableTask?.dueBy!!.toLong()
            setTimeValue(Date(mEditableTask!!.dueBy!!*1000))
        } else {
            setTitle("New Task")
            but_delete_task.visibility = View.GONE
        }
        mPriorityButList.map { button -> button.setOnClickListener(this) }
        iv_calendar_icon.setOnClickListener(View.OnClickListener { showTimeDialog() })
    }

    private fun setTimeValue(date: Date) {
        tv_calendare_value.setText("Date: " + DateFormat.getDateInstance().format(date))
    }

    private fun showTimeDialog() {
        var calendar = Calendar.getInstance()

        DatePickerDialog(
            this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        var calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        mTaskDate = calendar.timeInMillis/1000
        setTimeValue(calendar.time)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.appruve_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_appruve -> addTask()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setPrioritBut(priority: String?) {
        mPriority = priority.toString()
        mPriorityButList.map { button -> button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrey)) }
        when (priority) {
            butLowText -> but_low.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
            butNormalText -> but_normal.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
            butHighText -> but_high.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            but_low.id -> setPrioritBut(butLowText)
            but_normal.id -> setPrioritBut(butNormalText)
            but_high.id -> setPrioritBut(butHighText)

        }
    }

    private fun addTask() {
        if (et_title.text == null || et_title.text.toString() == "") {
            showErrorMessage("Title cant be empty")
            return
        }
        if (mTaskDate == 0L) {
            showErrorMessage("Date cant be empty")
            return
        }
        var task = Task()
        task.title = et_title.text.toString()
        task.priority = mPriority

        task.dueBy = mTaskDate
        if (mEditableTask != null && mEditableTask?.id != null) {
            mEditTaskPresenter.updateTask(mEditableTask?.id!!, task)
        } else {
            mEditTaskPresenter.addNewTask(task)
        }
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun deleteTask() {
        mEditTaskPresenter.deleteCurrentTask()
    }

    override fun onResume() {
        super.onResume()
        mEditTaskPresenter.setView(this)
    }

    override fun onPause() {
        super.onPause()
        mEditTaskPresenter.setView(null)
    }

    override fun showTaskList() {
        startActivity(Intent(this, TasksListActivity::class.java))
        finish()
    }

}
