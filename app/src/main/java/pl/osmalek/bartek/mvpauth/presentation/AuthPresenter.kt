package pl.osmalek.bartek.mvpauth.presentation

interface AuthPresenter {

    fun onViewCreated(view: AuthView)
    fun onViewDestroyed()
    fun validateLogin(login: String?)
    fun validatePassword(password: String?)
    fun signIn(login: String?, password: String?)
}