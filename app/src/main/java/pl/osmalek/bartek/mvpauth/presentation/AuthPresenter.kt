package pl.osmalek.bartek.mvpauth.presentation

interface AuthPresenter {

    fun onViewCreated(view: AuthView)
    fun onViewDestroyed()
    fun signIn(login: String?, password: String?)
    fun validateCredentials(login: String, password: String)
}