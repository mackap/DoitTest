package com.android.mackap.doittest.authorisation_feature

import com.android.mackap.doittest.authorisation_feature.pojo.SignResp
import io.reactivex.Single

/**
 *Created by Makarov on 14.03.2019
 */
interface AuthorisationMVP {
    interface IAuthorisationModel{
        fun authorisation(email: String, password: String):Single<SignResp>
        fun registration(email: String, password: String):Single<SignResp>
        fun setToken(token: String?)
        fun getToken(): String?
    }
    interface IAuthorisationView{
        fun showErrorMessage(string: String)
        fun showProgress(isShow: Boolean)
        fun startTaskListActivity()
    }
    interface IAuthorisationPresenter{
         fun authorisation(email: String, password: String)
         fun setView(authorisationActivity: IAuthorisationView?)
         fun registration(email: String, password: String)
         fun setIsSignInChecket(isChecked: Boolean)
        fun clicToLoginButton(toString: String, toString1: String, isRegistering:Boolean)
        fun checkAthorisation()
    }
}