package com.wholedetail.gramophone.data

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

/**
 * Automatically validates form data on change and returns `true`, otherwise `false`.
 *
 * @param formFieldsWithValidators function returns true if data valid
 */
class ObservableFormValid(
    lifecycleOwner: LifecycleOwner,
    private vararg val formFieldsWithValidators: Pair<MutableLiveData<*>, (Any) -> Boolean>
) : ObservableBoolean() {

    init {
        formFieldsWithValidators.forEach {
            it.first.observe(lifecycleOwner, { notifyChange() })
        }
    }

    override fun get() = formFieldsWithValidators.all { it.second(it.first.value!!) }

}