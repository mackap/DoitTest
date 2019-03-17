package com.android.mackap.doittest.core_comp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 *Created by Makarov on 16.03.2019
 */
public class Task {
    @SerializedName("id")
    @Expose
    open var id: Int? = null
    @SerializedName("title")
    @Expose
    open  var title: String? = null
    @SerializedName("dueBy")
    @Expose
    open var dueBy: Long? = null
    @SerializedName("priority")
    @Expose
    open var priority: String? = null

}