package com.android.mackap.doittest.core_comp

import com.android.mackap.doittest.authorisation_feature.pojo.SignResp
import com.android.mackap.doittest.core_comp.pojo.TaskDetailsResp
import com.android.mackap.doittest.core_comp.pojo.TaskListResp
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("users")
    fun signIn(
        @Field("email") email: String,
        @Field("password") password: String): Single<SignResp>

    @FormUrlEncoded
    @POST("auth")
    fun signUp(
        @Field("email") email: String,
        @Field("password") password: String): Single<SignResp>

    @GET("tasks")
    fun getTasks(
        @Header("Authorization:Bearer") token:String,
        @Query("page") page: Number): Single<TaskListResp>

    @FormUrlEncoded
    @POST("tasks")
    fun addTask(
        @Header("Authorization:Bearer") token:String,
        @Field("title") title: String,
        @Field("dueBy") dueBy: Long,
        @Field("priority") priority: String): Single<ResponseBody>

    @GET("tasks/{task}")
    fun getTaskDetails(
        @Header("Authorization:Bearer") token:String,
        @Path("task") taskId: Long): Single<TaskDetailsResp>

    @DELETE("tasks/{task}")
    fun deleteTask(
        @Header("Authorization:Bearer") token:String,
        @Path("task") taskId: Long): Single<ResponseBody>


    @PUT("tasks/{task}")
    fun updateTask(
        @Header("Authorization:Bearer") token:String,
        @Path("task") taskId: Int,
        @Query("title") title: String,
        @Query("dueBy") dueBy: Long,
        @Query("priority") priority: String): Single<ResponseBody>

}
