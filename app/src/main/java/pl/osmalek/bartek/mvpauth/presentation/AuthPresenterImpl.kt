package pl.osmalek.bartek.mvpauth.presentation

import pl.osmalek.bartek.mvpauth.validators.LoginValidator
import pl.osmalek.bartek.mvpauth.validators.PasswordValidator

class AuthPresenterImpl : AuthPresenter, LoginService.SignInListener {
    private var view: AuthView? = null

    private val loginValidator = LoginValidator()

    private val passwordValidator = PasswordValidator()

    private val loginService = LoginService(this)

    override fun onViewCreated(view: AuthView) {
        this.view = view
    }

    override fun onViewDestroyed() {
        this.view = null
    }

    override fun signIn(login: String?, password: String?) {
        if (login != null && password != null) {
            view?.showLoading()
            loginService.signIn(login, password)
        }
    }

    override fun onSignedIn(success: Boolean) {
        view?.hideLoading()
        if (success) {
            view?.showSignInSuccess()
        } else {
            view?.showSignInFailed()
        }
    }

    override fun validateCredentials(login: String, password: String) {
        val isValidLogin = loginValidator.validate(login)
        val isValidPassword = passwordValidator.validate(password)
        showLoginError(isValidLogin)
        showPasswordError(isValidPassword)
        enableSignIn(isValidLogin && isValidPassword)
    }

    private fun enableSignIn(isSignInEnabled: Boolean) {
        if (isSignInEnabled) {
            view?.enableSignIn()
        } else {
            view?.disableSignIn()
        }
    }

    private fun showPasswordError(isValidPassword: Boolean) {
        if (isValidPassword) {
            view?.hidePasswordError()
        } else {
            view?.showPasswordError("Invalid password")
        }
    }

    private fun showLoginError(isValidLogin: Boolean) {
        if (isValidLogin) {
            view?.hideLoginError()
        } else {
            view?.showLoginError("Invalid login")
        }
    }

}

