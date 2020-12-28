package com.luiscamargoti.passwordvalidator.gateways.http

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.luiscamargoti.passwordvalidator.PasswordValidatorApiApplication
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@ContextConfiguration(classes = [PasswordValidatorApiApplication::class])
@WebAppConfiguration
@SpringBootTest
class ValidatorControllerTest {

    private val API_END_POINT = "/api/v1/password"

    @Autowired
    private var wac: WebApplicationContext? = null
    private var mockMvc: MockMvc? = null
    private var objectMapper: ObjectMapper? = null

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac!!).build()
        objectMapper = ObjectMapper()
        objectMapper!!.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    @Test
    fun validatePasswordWithSuccessfully() {
        this.mockMvc!!.perform(get("$API_END_POINT/AbTp9!fok"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").value(true))
    }

    @Test
    fun validatePasswordWrongPath() {
        this.mockMvc!!.perform(get("$API_END_POINT/"))
            .andDo(print())
            .andExpect(status().isNotFound)
    }

    @Test
    fun validatePasswordWithEmpty() {
        this.mockMvc!!.perform(get("$API_END_POINT/ "))
            .andDo(print())
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.errorMessage").value("Password not be empty"))
    }

    @Test
    fun validatePasswordWithTwoCharsRepeats() {
        this.mockMvc!!.perform(get("$API_END_POINT/aa"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").value(false))
    }

    @Test
    fun validatePasswordWithTwoLetters() {
        this.mockMvc!!.perform(get("$API_END_POINT/ab"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").value(false))
    }

    @Test
    fun validatePasswordWithThreeLettersRepeats() {
        this.mockMvc!!.perform(get("$API_END_POINT/AAAbbbCc"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").value(false))
    }

    @Test
    fun validatePasswordWithTwoLettersLowerCaseRepeats() {
        this.mockMvc!!.perform(get("$API_END_POINT/AbTp9!foo"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").value(false))
    }

    @Test
    fun validatePasswordWithTwoLettersUpperCaseRepeats() {
        this.mockMvc!!.perform(get("$API_END_POINT/AbTp9!foA"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").value(false))
    }

    @Test
    fun validatePasswordWithSpace() {
        this.mockMvc!!.perform(get("$API_END_POINT/AbTp9 fok"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").value(false))
    }
}
