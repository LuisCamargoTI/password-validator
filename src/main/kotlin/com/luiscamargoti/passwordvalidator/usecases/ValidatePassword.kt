package com.luiscamargoti.passwordvalidator.usecases

import com.luiscamargoti.passwordvalidator.domain.exceptions.ValidatePasswordException
import org.springframework.stereotype.Component

@Component
class ValidatePassword {

    fun execute(password: String) : Boolean {
        checkIfIsEmpty(password)
        return when {
            !haveAtLeastNineChars(password) -> false
            !haveAtLeastOneDigit(password) -> false
            !haveOneAtLeastUpperCase(password) -> false
            !haveOneAtLeastLowerCase(password) -> false
            !haveOneAtLeastSpecialChar(password) -> false
            !doNotHaveRepeatChar(password) -> false
            else -> true
        }
    }

    private fun checkIfIsEmpty(password: String) {
        if (password.trim() == "") throw ValidatePasswordException("Password not be empty")
    }

    private fun haveAtLeastNineChars(password: String) = password.length >= 9
    private fun haveAtLeastOneDigit(password: String) = password.toCharArray().partition { it.isDigit() }.first.isNotEmpty()
    private fun haveOneAtLeastUpperCase(password: String) = password.contains(Regex("(?=.*[A-Z])"))
    private fun haveOneAtLeastLowerCase(password: String) = password.contains(Regex("(?=.*[a-z])"))
    private fun haveOneAtLeastSpecialChar(password: String) = password.contains(Regex("[!@#$%^&*()\\-+]"))
    private fun doNotHaveRepeatChar(password: String) : Boolean {
        for(i in password.indices) {
            val char: Char = password[i]
            for (x in i+1 until password.length) {
                val nextChar = password[x]
                if (char == nextChar) {
                    return false
                }
            }
        }
        return true
    }
}
