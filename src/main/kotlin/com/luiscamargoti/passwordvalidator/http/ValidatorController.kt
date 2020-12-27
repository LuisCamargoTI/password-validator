package com.luiscamargoti.passwordvalidator.http

import com.luiscamargoti.passwordvalidator.domain.exceptions.ValidatePasswordException
import com.luiscamargoti.passwordvalidator.ValidatePassword
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors


@RestController
@RequestMapping("/api/v1/password")
class ValidatorController {

    @Autowired
    lateinit var validatorPassword: ValidatePassword


    @GetMapping("/{password}")
    @Throws(ValidatePasswordException::class)
    fun passwordValidator(@PathVariable password: String) : Boolean {
        return validatorPassword.execute(password)
    }
}