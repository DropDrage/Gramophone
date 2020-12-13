package com.wholedetail.gramophone.view

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.annotation.InspectableProperty
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.wholedetail.gramophone.R
import com.wholedetail.gramophone.data.model.InstrumentType
import com.wholedetail.gramophone.data.model.MusicGenre
import com.wholedetail.gramophone.data.model.Named
import com.wholedetail.gramophone.utils.extensions.makeGone
import kotlinx.android.synthetic.main.view_labeled_dropdown.view.*

class LabeledDropdown(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var spinnerAdapter: ArrayAdapter<String>? = null

    private var selectedValue: Named? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_labeled_dropdown, this, true)

        val attrsArray = context.obtainStyledAttributes(attrs, R.styleable.LabeledDropdown)
        try {
            label.text = attrsArray.getString(R.styleable.LabeledDropdown_label)
        } finally {
            attrsArray.recycle()
        }

        if (TextUtils.isEmpty(label.text)) {
            label.makeGone()
        }

        spinnerAdapter = spinner.adapter as ArrayAdapter<String>
    }


    @InspectableProperty(name = "spinnerOptions")
    fun setSpinnerOptions(options: List<String>) {
        spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, options)
        spinner.adapter = spinnerAdapter
    }


    companion object {
        @JvmStatic
        @BindingAdapter("valueAttrChanged")
        fun setListeners(view: LabeledDropdown, attrChange: InverseBindingListener) {
            view.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    attrChange.onChange()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }


        @JvmStatic
        @BindingAdapter("value")
        fun setValue(view: LabeledDropdown, value: Named?) {
            view.selectedValue = value
            if (value != null && view.spinnerAdapter != null) {
                view.spinner.setSelection(view.spinnerAdapter!!.getPosition(view.context.getString(value.entityNameResId)))
            }
        }


        @JvmStatic
        @InverseBindingAdapter(attribute = "value", event = "valueAttrChanged")
        fun getInstrumentValue(view: LabeledDropdown): InstrumentType? = view.selectedValue as? InstrumentType

        @JvmStatic
        @InverseBindingAdapter(attribute = "value", event = "valueAttrChanged")
        fun getMusicGenreValue(view: LabeledDropdown): MusicGenre? = view.selectedValue as? MusicGenre


        @JvmStatic
        @BindingAdapter("value")
        fun setValueString(view: LabeledDropdown, value: String?) {
            if (value != null && view.spinnerAdapter != null) {
                view.spinner.setSelection(view.spinnerAdapter!!.getPosition(value))
            }
        }

        @JvmStatic
        @InverseBindingAdapter(attribute = "value")
        fun getValueString(view: LabeledDropdown): String? = view.spinner.selectedItem as String?
    }
}