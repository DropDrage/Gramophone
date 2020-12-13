package com.wholedetail.gramophone.utils.extensions

import android.util.Patterns

fun String.isEmailValid(): Boolean =
    isNotBlank() && contains('@') && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isPasswordValid() = length > 5