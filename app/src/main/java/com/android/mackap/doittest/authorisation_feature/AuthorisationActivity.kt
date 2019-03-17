package com.android.mackap.doittest.authorisation_feature

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.android.mackap.doittest.DoitApp
import com.android.mackap.doittest.R
import com.android.mackap.doittest.tasks_list_feature.TasksListActivity
import kotlinx.android.synthetic.main.activity_authorisation.*
import javax.inject.Inject

class AuthorisationActivity : AppCompatActivity(), AuthorisationMVP.IAuthorisationView {

    @Inject
    lateinit var mAuthPresenter: AuthorisationMVP.IAuthorisationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as DoitApp).getAuthComponent()?.injectIntoActivity(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorisation)
        switch_login_register.setOnCheckedChangeListener { buttonView, isChecked ->
            mAuthPresenter.setIsSignInChecket(isChecked)
            updateViewState(isChecked)
        }

        but_login.setOnClickListener(View.OnClickListener {
            authorisation()
        })

    }

    private fun updateViewState(isChecked: Boolean) {
        if (isChecked) {
            but_login.setText(R.string.sign_up)
            tv_sign_in.setText(R.string.sign_up)
        } else {
            but_login.setText(R.string.sign_in)
            tv_sign_in.setText(R.string.sign_in)
        }
    }

    override fun showErrorMessage(message: String) {
        progress_author_activ.visibility = View.INVISIBLE
        var dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(getString(R.string.error_title))
        dialogBuilder.setMessage(message)
        dialogBuilder.setNeutralButton("OK") { dialog, which -> dialog.cancel() }
        var dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun authorisation() {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(et_email_auth.text?.toString()).matches()) {
            mAuthPresenter.clicToLoginButton(et_email_auth.text.toString(),
                et_password.text!!.toString(),
                switch_login_register.isChecked)
        } else {
            showErrorMessage(getString(R.string.enter_valid_email))
        }
    }

    override fun showProgress(isShow: Boolean) {
        if (isShow) {
            progress_author_activ.visibility = View.VISIBLE
            but_login.isEnabled = false
        } else {
            progress_author_activ.visibility = View.INVISIBLE
            but_login.isEnabled = true
        }
    }

    override fun onResume() {
        super.onResume()
        mAuthPresenter.setView(this)
        mAuthPresenter.checkAthorisation()
    }

    override fun onPause() {
        super.onPause()
        mAuthPresenter.setView(null)
    }

    override fun startTaskListActivity() {
        startActivity(Intent(this, TasksListActivity::class.java))
        finish()
    }
}
