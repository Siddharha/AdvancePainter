package `in`.creativelizard.advancepainter.Utils

import android.content.Context
import android.util.DisplayMetrics



class AppUtil {

companion object{
    fun convertPixelsToDp(px : Int, context : Context):Float{
        return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}
}