package com.luiscamargoti.passwordvalidator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PasswordValidatorApiApplication

fun main(args: Array<String>) {
	runApplication<PasswordValidatorApiApplication>(*args)
}
