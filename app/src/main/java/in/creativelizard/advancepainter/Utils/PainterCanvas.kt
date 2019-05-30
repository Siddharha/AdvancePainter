package `in`.creativelizard.advancepainter.Utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.MotionEvent






class PainterCanvas(context: Context?, attrs: AttributeSet?) : View(context, attrs){
    private lateinit var drawPaint:Paint


    init {

        setupPaint()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return false


    }


    private fun setupPaint() {
        drawPaint = Paint()
        drawPaint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.WHITE)
        canvas?.drawCircle(10f,10f,10f,drawPaint)
        canvas?.save()

        canvas?.restore()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
       // super.onMeasure(widthMeasureSpec, heightMeasureSpec)


        val desiredWidth = 100
        val desiredHeight = 100

        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)

        val width: Int
        val height: Int

        //Measure Width
        if (widthMode == View.MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize
        } else if (widthMode == View.MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize)
        } else {
            //Be whatever you want
            width = desiredWidth
        }

        //Measure Height
        if (heightMode == View.MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize
        } else if (heightMode == View.MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize)
        } else {
            //Be whatever you want
            height = desiredHeight
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height)
    }
}