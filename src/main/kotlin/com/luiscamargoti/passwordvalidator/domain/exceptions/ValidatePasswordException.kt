package com.luiscamargoti.passwordvalidator.domain.exceptions

class ValidatePasswordException : RuntimeException {
    constructor(message: String): super(message)
}
