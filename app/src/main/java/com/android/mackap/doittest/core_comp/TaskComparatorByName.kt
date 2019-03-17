package com.android.mackap.doittest.core_comp

import com.android.mackap.doittest.core_comp.pojo.Task

/**
 *Created by Makarov on 16.03.2019
 */
class TaskComparatorByName():Comparator<Task> {
    override fun compare(task1: Task?, task2: Task?): Int {
         if(task1?.title.toString()> task2?.title.toString()) return 1
        if(task1?.title.toString()< task2?.title.toString()) return -1
        return 0
    }
}