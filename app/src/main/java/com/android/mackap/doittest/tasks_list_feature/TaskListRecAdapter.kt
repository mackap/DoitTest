package com.android.mackap.doittest.tasks_list_feature

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.mackap.doittest.R
import com.android.mackap.doittest.core_comp.IRecItemClickListener
import com.android.mackap.doittest.core_comp.TaskComparatorByName
import com.android.mackap.doittest.core_comp.TaskComparatorByPrior
import com.android.mackap.doittest.core_comp.pojo.Task
import kotlinx.android.synthetic.main.activity_edit_task.*
import kotlinx.android.synthetic.main.item_task.view.*
import java.text.DateFormat
import java.util.*

/**
 *Created by Makarov on 15.03.2019
 */
class TaskListRecAdapter(taskList: List<Task>, var itemClicListener: IRecItemClickListener) :
    RecyclerView.Adapter<TaskListRecAdapter.TaskHolder>() {
    var mTaskList: MutableList<Task>? = taskList.toMutableList()
    lateinit var context: Context
    val mTaskComparatorByName = TaskComparatorByName()
    val mTaskComparatorByPrior = TaskComparatorByPrior()
    override fun getItemCount(): Int {
        return mTaskList!!.size
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskHolder {
        context = viewGroup.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_task, null, false)
        return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val task = mTaskList?.get(position)
        holder.tvTitle.setText(task?.title)
        holder.tvDueTo.setText("Due to: " + DateFormat.getDateInstance().format(Date(task!!.dueBy!!*1000)))
        holder.tvPriority.setText(task?.priority)
        holder.relLayoutContainer.setOnClickListener {
            itemClicListener.onItemClick(task?.id?.toLong())
        }
    }



    fun updateData(tasks: List<Task>?) {
        mTaskList?.clear()
        mTaskList?.addAll(tasks!!.toMutableList())
        notifyDataSetChanged()
    }

    fun sortedByName(isReverse:Boolean) {
        Collections.sort(mTaskList, mTaskComparatorByName)
        if(isReverse) Collections.reverse(mTaskList)
        notifyDataSetChanged()
    }

    fun sortedByPriority(isReverse: Boolean) {
        Collections.sort(mTaskList, mTaskComparatorByPrior)
        if(isReverse) Collections.reverse(mTaskList)
        notifyDataSetChanged()
    }

    class TaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var relLayoutContainer = itemView.rl_container
        var tvTitle = itemView.tv_title
        var tvDueTo = itemView.tv_due_to_value
        var ivRightArrov = itemView.iv_right_arrov
        var tvPriority = itemView.tv_priority_value
    }
}


