package `in`.creativelizard.advancepainter.Utils

import android.content.Context
import android.graphics.Path
import android.util.DisplayMetrics



class AppUtil {

companion object{
    val convertPixelsToDp : ( Int, Context) -> Float = {px,c ->
        px / (c.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT) }


}

}