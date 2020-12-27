package com.luiscamargoti.passwordvalidator.usecases

import com.luiscamargoti.passwordvalidator.domain.exceptions.ValidatePasswordException
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ValidatePasswordTest {

    private val validatePassword: ValidatePassword = ValidatePassword()

    @Test
    fun validatePasswordWithSuccessfully(){
        val password = "AbTp9!fok"
        assert(validatePassword.execute(password))
    }

    @Test
    fun validatePasswordWithEmpty(){
        val password = ""
        assertThrows(ValidatePasswordException::class.java) { !validatePassword.execute(password) }
    }

    @Test
    fun validatePasswordWithTwoCharsRepeats(){
        val password = "aa"
        assert(!validatePassword.execute(password))
    }

    @Test
    fun validatePasswordWithTwoLetters(){
        val password = "ab"
        assert(!validatePassword.execute(password))
    }

    @Test
    fun validatePasswordWithThreeLettersRepeats(){
        val password = "AAAbbbCc"
        assert(!validatePassword.execute(password))
    }

    @Test
    fun validatePasswordWithTwoLettersLowerCaseRepeats(){
        val password = "AbTp9!foo"
        assert(!validatePassword.execute(password))
    }

    @Test
    fun validatePasswordWithTwoLettersUpperCaseRepeats(){
        val password = "AbTp9!foA"
        assert(!validatePassword.execute(password))
    }

    @Test
    fun validatePasswordWithSpace(){
        val password = "AbTp9 fok"
        assert(!validatePassword.execute(password))
    }
}
