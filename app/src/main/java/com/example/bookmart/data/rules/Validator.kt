package com.example.bookmart

import android.util.Log

object Validator {


    fun validateFirstName(fName: String): ValidationResult {
        return ValidationResult(
            (!fName.isNullOrEmpty() && fName.length >= 2)
        )

    }

    fun validateLastName(lName: String): ValidationResult {
        return ValidationResult(
            (!lName.isNullOrEmpty() && lName.length >= 2)
        )
    }

    fun validateEmail(email: String): ValidationResult {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

        return ValidationResult(
            (!email.isNullOrEmpty()&& email.matches(emailRegex.toRegex()))
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length >= 4)
        )
    }

    fun validatePrivacyPolicyAcceptance(statusValue:Boolean):ValidationResult{
        return ValidationResult(
            statusValue
        )
    }

}

data class ValidationResult(
    val status: Boolean = false
)








