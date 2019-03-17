package com.android.mackap.doittest.core_comp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *Created by Makarov on 15.03.2019
 */
class TaskListResp {
    @SerializedName("tasks")
    @Expose
    var tasks: List<Task>? = null
    @SerializedName("meta")
    @Expose
    var meta: Meta? = null
}