package com.main.marriage_list.helper.component

import android.content.Context
import android.util.AttributeSet
import android.util.SparseArray
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.main.marriage_list.R

class CustomButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var typeArray: SparseArray<Type>? = null
    private var type: Type? = null
    private var isClickableWhenDisable = false

    init {
        initTypeArray()
        val ta = context.obtainStyledAttributes(attrs, R.styleable.CustomButton)
        val status = ta.getInt(R.styleable.CustomButton_btnType, 0)
        type = typeArray?.get(status)
        gravity = Gravity.CENTER
        setBackgroundByType()
        setTextColorByType()
        ta.recycle()
    }

    private fun initTypeArray() {
        typeArray = SparseArray(5)
        typeArray?.put(0, Type.PRIMARY)
        typeArray?.put(1, Type.SECONDARY)
        typeArray?.put(2, Type.FAIL)
        typeArray?.put(3, Type.GREEN_BORDER)
        typeArray?.put(4, Type.ORANGE_BORDER)
    }

    private fun setBackgroundByType() {
        when (type) {
            Type.PRIMARY -> {
                background = ContextCompat.getDrawable(context, R.drawable.custom_button_primary)
            }
            Type.SECONDARY -> {
                background = ContextCompat.getDrawable(context, R.drawable.custom_button_secondary)
            }
            Type.FAIL -> {
                background = ContextCompat.getDrawable(context, R.drawable.custom_button_fail)
            }
            Type.GREEN_BORDER -> {
                background = ContextCompat.getDrawable(context, R.drawable.custom_button_bordered_green)
            }
            Type.ORANGE_BORDER -> {
                background = ContextCompat.getDrawable(context, R.drawable.custom_button_bordered_orange)
            }
        }
    }

    private fun setTextColorByType() {
        when(type) {
            Type.GREEN_BORDER -> {
                setTextColor(ContextCompat.getColor(context, R.color.main_color))
            }
            Type.ORANGE_BORDER -> {
                setTextColor(ContextCompat.getColor(context, R.color.main_orange))
            }
            else -> {
                setTextColor(ContextCompat.getColor(context, R.color.white))
            }
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        isEnabled = isEnabled || isClickableWhenDisable
    }

    override fun setAllowClickWhenDisabled(clickableWhenDisabled: Boolean) {
        super.setAllowClickWhenDisabled(clickableWhenDisabled)
        isClickableWhenDisable = clickableWhenDisabled
    }

    enum class Type {
        PRIMARY, SECONDARY, FAIL, GREEN_BORDER, ORANGE_BORDER
    }
}