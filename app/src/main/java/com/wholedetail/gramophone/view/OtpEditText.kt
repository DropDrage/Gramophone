package com.wholedetail.gramophone.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ActionMode
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.wholedetail.gramophone.R
import com.wholedetail.gramophone.utils.extensions.afterTextChanged
import kotlin.math.max

typealias OnEnterCompleteListener = (String) -> Unit

class OtpEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {

    private val mNumChars: Float
    private val textWidths: FloatArray

    /**
     * Space between lines.
     */
    private val mLineSpace: Float
    private val mLineWidth: Float
    private val mLineThickness: Float
    private val mLineThicknessSelected: Float
    private val mTextMarginFromLine: Float //8dp by default, height of the text from our lines
    private val mLinesPaint: Paint

    private var bottom: Float = 0f
    private var mCharSize: Float = 0f
    private var startX: Float = 0f
    private var startXStep: Int = 0

    private var mClickListener: OnClickListener? = null
    private var onEnterCompleteListener: OnEnterCompleteListener? = null


    private val mColorStates: ColorStateList


    init {
        val densityMultiplier = context.resources.displayMetrics.density

        val attrArray = context.obtainStyledAttributes(attrs, R.styleable.OtpEditText, 0, 0)
        mNumChars = attrArray.getInt(R.styleable.OtpEditText_length, 4).toFloat()
        mLineSpace = attrArray.getDimension(R.styleable.OtpEditText_line_space, 24 * densityMultiplier)
        mLineWidth = attrArray.getDimension(R.styleable.OtpEditText_line_width, 0f)
        mLineThickness = attrArray.getDimension(R.styleable.OtpEditText_line_thickness, 1 * densityMultiplier)
        attrArray.recycle()

        filters = arrayOf<InputFilter>(*filters, LengthFilter(mNumChars.toInt()))

        mLineThicknessSelected = 2 * densityMultiplier
        mTextMarginFromLine = 8 * densityMultiplier

        textWidths = FloatArray(mNumChars.toInt())

        mLinesPaint = Paint(paint)
        mLinesPaint.strokeWidth = mLineThickness

        val value = TypedValue()
        context.theme.resolveAttribute(R.attr.colorAccent, value, true)
        mLinesPaint.color = value.data

        setBackgroundResource(0)

        val mStates = arrayOf(
            intArrayOf(android.R.attr.state_selected),
            intArrayOf(android.R.attr.state_focused),
            intArrayOf(-android.R.attr.state_focused)
        )
        val mColors = intArrayOf(Color.GREEN, Color.BLACK, Color.BLACK)
        mColorStates = ColorStateList(mStates, mColors)


        super.setOnClickListener {
            // When tapped, move cursor to end of text.
            setSelection(text?.length ?: 0)

            mClickListener?.onClick(it)
        }

        if (!isInEditMode) {
            val outValue = TypedValue()

            //Activated
            context.theme.resolveAttribute(R.attr.colorControlActivated, outValue, true)
            mColors[0] = outValue.data

            //Dark
            context.theme.resolveAttribute(R.attr.colorPrimary, outValue, true)
            mColors[1] = outValue.data

            //Highlight
            context.theme.resolveAttribute(R.attr.colorControlHighlight, outValue, true)
            mColors[2] = outValue.data
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minWidth = paddingLeft + paddingRight + suggestedMinimumWidth
        val availableWidth = View.resolveSizeAndState(minWidth, widthMeasureSpec, 1)
        var width = availableWidth

        startX = paddingLeft.toFloat()
        if (mLineWidth > 0) {
            val maxWidth = minWidth + (mLineWidth + mLineSpace) * mNumChars - mLineSpace
            startX += max(availableWidth - maxWidth, 0f) / 2
            width = View.resolveSizeAndState(maxWidth.toInt(), widthMeasureSpec, 1)
        }
        mCharSize =
            if (mLineWidth > 0) mLineWidth
            else (availableWidth - mLineSpace * (mNumChars - 1) - paddingLeft - paddingRight) / mNumChars
        startXStep = (mCharSize + mLineSpace).toInt()

        val minHeight =
            (textSize + mLineThickness + mTextMarginFromLine).toInt() + paddingBottom + paddingTop + suggestedMinimumHeight
        val height = View.resolveSizeAndState(minHeight, heightMeasureSpec, 0)
        bottom = (height - paddingBottom).toFloat()

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        val text = text!!
        val textLength = text.length
        paint.getTextWidths(text, 0, textLength, textWidths)

        var startXTemp = startX
        for (i in 0 until mNumChars.toInt()) {
            updateColorForLines(i == textLength || i + 1 == mNumChars.toInt() && textLength == mNumChars.toInt())
            canvas.drawLine(startXTemp, bottom, startXTemp + mCharSize, bottom, mLinesPaint)

            if (textLength > i) {
                val middle = startXTemp + mCharSize / 2
                canvas.drawText(text, i, i + 1, middle - textWidths[i] / 2, bottom - mTextMarginFromLine, paint)
            }

            startXTemp += startXStep
        }
    }

    private fun getColorForState(vararg states: Int) = mColorStates.getColorForState(states, Color.BLACK)

    /**
     * @param next Is the current char the next character to be input?
     */
    private fun updateColorForLines(next: Boolean) {
        if (isFocused) {
            mLinesPaint.strokeWidth = mLineThicknessSelected
            mLinesPaint.color =
                getColorForState(if (next) android.R.attr.state_selected else android.R.attr.state_focused)
        } else {
            mLinesPaint.strokeWidth = mLineThickness
            mLinesPaint.color = getColorForState(-android.R.attr.state_focused)
        }
    }


    override fun setOnClickListener(l: OnClickListener?) {
        mClickListener = l
    }

    override fun setCustomSelectionActionModeCallback(actionModeCallback: ActionMode.Callback?) {
        throw RuntimeException("setCustomSelectionActionModeCallback() not supported.")
    }

    fun setOnEnterCompleteListener(enterCompleteListener: OnEnterCompleteListener) {
        onEnterCompleteListener = enterCompleteListener
        afterTextChanged { if (text!!.length == mNumChars.toInt()) enterCompleteListener(it) }
    }
}