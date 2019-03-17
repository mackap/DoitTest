package com.android.mackap.doittest.core_comp

import com.android.mackap.doittest.core_comp.pojo.Task
import java.util.*

/**
 *Created by Makarov on 16.03.2019
 */
class TaskComparatorByPrior() : Comparator<Task> {
    val priorList: MutableList<String>? = Arrays.asList("High", "Normal", "Low")

    override fun compare(task1: Task?, task2: Task?): Int {
        if (priorList?.indexOf(task1?.priority.toString())!! > priorList.indexOf(task2?.priority.toString())) return 1
        if (priorList?.indexOf(task1?.priority.toString())!! < priorList.indexOf(task2?.priority.toString())) return -1
        return 0
    }
}