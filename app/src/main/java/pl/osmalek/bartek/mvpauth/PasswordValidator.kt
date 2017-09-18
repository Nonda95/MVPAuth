package pl.osmalek.bartek.mvpauth

private const val MIN_LENGTH = 6

class PasswordValidator : Validator<String> {
    override fun validate(element: String?): Boolean {
        return element?.run {
            length >= MIN_LENGTH && any { it.isDigit() } && any { it.isUpperCase() }
        } ?: false
    }
}