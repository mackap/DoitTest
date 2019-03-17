package com.android.mackap.doittest.authorisation_feature.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 *Created by Makarov on 14.03.2019
 */
open class SignResp {
    @SerializedName("token")
    @Expose
    var token: String? = null

}