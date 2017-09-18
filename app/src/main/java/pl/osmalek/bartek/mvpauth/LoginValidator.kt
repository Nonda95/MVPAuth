package pl.osmalek.bartek.mvpauth

private const val MIN_LENGTH = 3

class LoginValidator : Validator<String> {
    override fun validate(element: String?): Boolean {
        return element?.let {
            it.length >= MIN_LENGTH && it.matches("^[^\\s\\d]*$".toRegex())
        } ?: false
    }
}