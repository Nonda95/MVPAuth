package pl.osmalek.bartek.mvpauth.presentation

import pl.osmalek.bartek.mvpauth.LoginValidator
import pl.osmalek.bartek.mvpauth.PasswordValidator

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

    override fun validateLogin(login: String?) {
        if (loginValidator.validate(login)) {
            view?.hideLoginError()
        } else {
            view?.showLoginError("Invalid login")
        }
    }

    override fun validatePassword(password: String?) {
        if (passwordValidator.validate(password)) {
            view?.hidePasswordError()
        } else {
            view?.showPasswordError("Invalid password")
        }
    }

    override fun onSignedIn(success: Boolean) {
        view?.hideLoading()
        if(success) {
            view?.showLoginSuccess()
        } else {
            view?.showLoginFailed()
        }
    }

}

