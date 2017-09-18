package pl.osmalek.bartek.mvpauth.presentation

interface AuthView {

    fun showLoginError(e: String?)
    fun showPasswordError(e: String?)
    fun hideLoginError()
    fun hidePasswordError()
    fun showLoading()
    fun hideLoading()
    fun showLoginSuccess()
    fun showLoginFailed()
}

