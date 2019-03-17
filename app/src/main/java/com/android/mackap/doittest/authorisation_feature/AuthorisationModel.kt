package com.android.mackap.doittest.authorisation_feature

import com.android.mackap.doittest.authorisation_feature.pojo.SignResp
import com.android.mackap.doittest.core_comp.ApiService
import com.android.mackap.doittest.core_comp.ILocalStorage
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *Created by Makarov on 14.03.2019
 */
class AuthorisationModel(apiService: ApiService, localStorage: ILocalStorage) :
    AuthorisationMVP.IAuthorisationModel {
    override fun setToken(token: String?) {
        mLocalStorage.saveToken(token)
    }

    var mApiService = apiService
    var mLocalStorage = localStorage

    override fun authorisation(email: String, password: String): Single<SignResp> {
        return mApiService.signUp(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    override fun registration(email: String, password: String): Single<SignResp> {
       return mApiService.signIn(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getToken():String? {
       return mLocalStorage.getToken()
    }
}