package com.luiscamargoti.passwordvalidator.gateways.http

import com.luiscamargoti.passwordvalidator.domain.exceptions.ValidatePasswordException
import com.luiscamargoti.passwordvalidator.usecases.ValidatePassword
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/password")
class ValidatorController {

    @Autowired
    lateinit var validatePassword: ValidatePassword

    @GetMapping("/{password}")
    @Throws(ValidatePasswordException::class)
    fun validatePassword(@PathVariable password: String) : Boolean {
        return validatePassword.execute(password)
    }
}
