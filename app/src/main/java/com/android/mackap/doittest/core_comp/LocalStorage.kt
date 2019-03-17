package com.android.mackap.doittest.core_comp

import android.content.SharedPreferences
import com.android.mackap.doittest.core_comp.pojo.Task

/**
 *Created by Makarov on 15.03.2019

 */
class LocalStorage(sharedPreferences: SharedPreferences) : ILocalStorage {
    private val mSharedPreferences = sharedPreferences
    private val TOKEN_KEY: String = "token_key"
    private var mToken: String? = null
    private var mSelectedTask: Task? = null
    private var mSelectedTaskId:Long?=null
    override fun getToken(): String? {
        if (mToken != null) {
            return mToken;
        }
        return mSharedPreferences.getString(TOKEN_KEY, null)
    }

    override fun saveToken(token: String?) {
        mToken = token
        mSharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    override fun getSelectedTask(): Task? {
        return mSelectedTask
    }

    override fun setSelectedTask(task: Task?) {
        mSelectedTask = task
    }

    override fun setSelectedTaskId(itemId: Long?) {
        mSelectedTaskId = itemId
    }

    override fun getSelectedTaskId(): Long? {
        return mSelectedTaskId
    }
}