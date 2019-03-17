package com.android.mackap.doittest.authorisation_feature

import android.annotation.SuppressLint
import com.android.mackap.doittest.authorisation_feature.pojo.SignResp

/**
 *Created by Makarov on 14.03.2019
 */
class AuthorisationPresenter(authModel: AuthorisationMVP.IAuthorisationModel) :
    AuthorisationMVP.IAuthorisationPresenter {
    var mAuthorisationModel: AuthorisationMVP.IAuthorisationModel = authModel
    var mAuthorView: AuthorisationMVP.IAuthorisationView? = null
    var mIsSignUp = false

    override fun setView(authorisationActivity: AuthorisationMVP.IAuthorisationView?) {
        mAuthorView = authorisationActivity
    }

    override fun clicToLoginButton(email: String, password: String, isRegistering:Boolean) {
        if (isRegistering) {
            registration(email, password)
        } else {
            authorisation(email, password)
        }
    }

    @SuppressLint("CheckResult")
    override fun authorisation(email: String, password: String) {
        mAuthorisationModel.authorisation(email, password)
            .doOnSubscribe { mAuthorView?.showProgress(true) }
            .doFinally { mAuthorView?.showProgress(false) }
            .subscribe({ signResp ->
                run(processingSuccessAuthorisation(signResp))
            },
                { error -> mAuthorView?.showErrorMessage(error.message!!) })

    }

    @SuppressLint("CheckResult")
    override fun registration(email: String, password: String) {
        mAuthorisationModel.registration(email, password)
            .doOnSubscribe { mAuthorView?.showProgress(true) }
            .doFinally { mAuthorView?.showProgress(false) }
            .subscribe({ signResp ->
                run(processingSuccessAuthorisation(signResp))
            },
                { error -> mAuthorView?.showErrorMessage(error.message!!) })
    }

    private fun processingSuccessAuthorisation(signResp: SignResp): AuthorisationPresenter.() -> Unit {
        return fun AuthorisationPresenter.() {
            mAuthorisationModel.setToken(signResp.token)
            mAuthorView?.startTaskListActivity()

        }
    }

    override fun setIsSignInChecket(isChecked: Boolean) {
        mIsSignUp = isChecked
    }

    override fun checkAthorisation() {
        if( mAuthorisationModel.getToken()!=null){
            mAuthorView?.startTaskListActivity()
        }
    }


}