package com.android.mackap.doittest.core_comp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 *Created by Makarov on 16.03.2019
 */
class TaskDetailsResp {
    @SerializedName("task")
    @Expose
    var task: Task? = null
}