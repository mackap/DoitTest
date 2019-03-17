package com.android.mackap.doittest.core_comp

import com.android.mackap.doittest.core_comp.pojo.Task

/**
 *Created by Makarov on 15.03.2019
 */
interface ILocalStorage {
    fun saveToken(token:String?)
    fun getToken(): String?
    fun getSelectedTask(): Task?
    fun setSelectedTask(task: Task?)
    fun setSelectedTaskId(itemId: Long?)
    fun getSelectedTaskId():Long?
}