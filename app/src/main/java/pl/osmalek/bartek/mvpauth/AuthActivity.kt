package pl.osmalek.bartek.mvpauth

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.content_auth.*
import pl.osmalek.bartek.mvpauth.presentation.AuthPresenter
import pl.osmalek.bartek.mvpauth.presentation.AuthPresenterImpl
import pl.osmalek.bartek.mvpauth.presentation.AuthView


class AuthActivity : AppCompatActivity(), AuthView {
    private var presenter: AuthPresenter = AuthPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        presenter.onViewCreated(this)
        setTextWatchers()
        setSignInListener()
    }

    private fun setSignInListener() {
        butSignIn.setOnClickListener {
            presenter.signIn(etLogin.text.toString(), etPassword.text.toString())
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(etLogin.windowToken,
                InputMethodManager.RESULT_UNCHANGED_SHOWN)
    }

    private fun setTextWatchers() {
        etLogin.addWatcher(presenter::validateLogin)
        etPassword.addWatcher(presenter::validatePassword)
    }

    private fun EditText.addWatcher(afterChange: (String?) -> Unit) {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                afterChange(s?.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        })
    }

    override fun showLoading() {
        runOnUiThread {
            flLoading.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        runOnUiThread {
            flLoading.visibility = View.GONE
        }
    }

    override fun showLoginSuccess() {
        runOnUiThread {
            Snackbar.make(flLoading, "Success", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun showLoginFailed() {
        runOnUiThread {
            Snackbar.make(flLoading, "Fail", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun showLoginError(e: String?) {
        tilLogin.error = e
    }

    override fun hideLoginError() {
        tilLogin.error = null
    }

    override fun showPasswordError(e: String?) {
        tilPassword.error = e
    }

    override fun hidePasswordError() {
        tilPassword.error = null
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}
