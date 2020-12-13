package com.wholedetail.gramophone.ui.activity.ui.login

import androidx.annotation.StringRes

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    @StringRes val emailError: Int? = null,
    @StringRes val passwordError: Int? = null,
)