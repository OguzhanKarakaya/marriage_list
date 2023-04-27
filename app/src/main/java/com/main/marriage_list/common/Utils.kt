package com.main.marriage_list.common

import android.graphics.Color
import kotlin.math.roundToInt
import kotlin.reflect.KClass

object Utils {

    @JvmStatic
    fun getColorWithAlpha(color: Int, ratio: Float): Int {
        val newColor: Int
        val alpha = (Color.alpha(color) * ratio).roundToInt()
        val r = Color.red(color)
        val g = Color.green(color)
        val b = Color.blue(color)
        newColor = Color.argb(alpha, r, g, b)
        return newColor
    }

    @JvmStatic
    fun <T : Any> mapToObject(map: Map<String, Any>, clazz: KClass<T>) : T {
        //Get default constructor
        val constructor = clazz.constructors.first()

        //Map constructor parameters to map values
        val args = constructor
            .parameters
            .map { it to map.get(it.name) }
            .toMap()

        //return object from constructor call
        return constructor.callBy(args)
    }
}

