package com.luiscamargoti.passwordvalidator.gateways.http.customException

import com.luiscamargoti.passwordvalidator.domain.exceptions.ValidatePasswordException
import com.luiscamargoti.passwordvalidator.gateways.http.json.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler(ValidatePasswordException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleNotFoundException(ex: ValidatePasswordException): ErrorResponse? {
        return ErrorResponse(ex.message)
    }
}
