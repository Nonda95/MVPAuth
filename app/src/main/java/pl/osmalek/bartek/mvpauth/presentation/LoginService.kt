package pl.osmalek.bartek.mvpauth.presentation

import java.util.*

class LoginService(private val listener: SignInListener) {
    private val timer = Timer()

    fun signIn(login: String, password: String) {
        val timerTask: TimerTask = getSignInTask(login, password)
        timer.schedule(timerTask, 5000)
    }

    private fun getSignInTask(login: String, password: String): TimerTask {
        return object : TimerTask() {
            override fun run() {
                listener.onSignedIn(login == "Aqq" && password == "Passw0rd")
            }
        }
    }

    interface SignInListener {
        fun onSignedIn(success: Boolean)
    }
}