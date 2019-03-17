package com.android.mackap.doittest.core_comp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *Created by Makarov on 15.03.2019
 */
class Meta {

    @SerializedName("current")
    @Expose
    var current: Int? = null
    @SerializedName("limit")
    @Expose
    var limit: Int? = null
    @SerializedName("count")
    @Expose
    var count: Int? = null

}