package com.wholedetail.gramophone.ui.auth.sign_up

import com.wholedetail.gramophone.base.BaseViewModel

abstract class SignUpViewModel : BaseViewModel() {

    lateinit var onSuccess: () -> Unit
    lateinit var onError: (String) -> Unit

}