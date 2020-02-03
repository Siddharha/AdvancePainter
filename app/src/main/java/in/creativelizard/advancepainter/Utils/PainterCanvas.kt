package `in`.creativelizard.advancepainter.Utils

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.MotionEvent
import android.graphics.RectF
import android.util.Log
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class PainterCanvas(context: Activity?, attrs: AttributeSet?) : View(context, attrs){
    private lateinit var drawPaint:Paint
    private val TOUCH_TOLERANCE = 4f
    lateinit var path:Path
    private var oldX = 0f
    private var oldY = 0f
    private var lastX = 0f
    private var lastY = 0f
    lateinit var scaleMatrix:Matrix
    lateinit var rectF:RectF
    var isCanvasInDrawMode = false
    public var DRAWING_MODE = 0 //for Free Drawing.

    lateinit var pathArrayList: ArrayList<Path>
    lateinit var pathRedoList:ArrayList<Path>
    lateinit var mCanvas: Canvas

    init {

        setupPaint()
    }

     fun setupScaleMatrix(f: Float): (Float) ->Unit = {
        scaleMatrix.setScale(it, it,0.5f,0.5f)
        path.transform(scaleMatrix)

         mCanvas.restore()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val pointX = event?.x
        val pointY = event?.y

        when(event?.action){
            MotionEvent.ACTION_DOWN ->{
               when(DRAWING_MODE){
                   0 ->{
                       //path.reset()
                       path.moveTo(pointX!!, pointY!!)
                       //path.lineTo(pointX, pointY)
                       oldX = pointX
                       oldY = pointY
                   }

                   1 ->{
                       //path.reset()
                       //path.lineTo(pointX, pointY)
                       oldX = pointX!!
                       oldY = pointY!!
                       path.moveTo(oldX, oldY)
                   }
               }

            }

            MotionEvent.ACTION_MOVE ->{
                when(DRAWING_MODE) {

                    0->{
                        val dx = Math.abs(pointX!! - oldX)
                        val dy = Math.abs(pointY!! - oldY)
                        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                            path.quadTo(oldX, oldY, (pointX + oldX) / 2, (pointY + oldY) / 2)
                            oldX = pointX
                            oldY = pointY

                        }
                    }

                    1->{
                       // val dx = Math.abs(pointX!! - oldX)
                       // val dy = Math.abs(pointY!! - oldY)
                        lastX = pointX!!
                        lastY = pointY!!
                       // if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                        path.reset()
                        path.moveTo(oldX,oldY)
                            path.lineTo(lastX,lastY)
                          //  oldX = pointX
                           // oldY = pointY

                       // }
                    }
                }
                invalidate()
            }
            MotionEvent.ACTION_UP ->{
                val _localPath = Path()
                _localPath.addPath(path)
                pathArrayList.add(_localPath)
                path.reset()
            }
        }
        return isCanvasInDrawMode


    }


    private fun setupPaint() {
       scaleMatrix = Matrix()
        rectF = RectF()
        path = Path()
        path.computeBounds(rectF, true)
        drawPaint = Paint()
        drawPaint.color = Color.BLACK
        drawPaint.strokeWidth = 5f

        drawPaint.isAntiAlias = false
        drawPaint.style = Paint.Style.STROKE
        drawPaint.strokeJoin = Paint.Join.ROUND
        drawPaint.strokeCap = Paint.Cap.SQUARE
        pathArrayList = ArrayList()
        pathRedoList = ArrayList()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mCanvas = canvas!!
        mCanvas.drawColor(Color.WHITE)
        mCanvas.drawPath(path,drawPaint)
        for(pth in pathArrayList){
        mCanvas.drawPath(pth, drawPaint)
        }




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