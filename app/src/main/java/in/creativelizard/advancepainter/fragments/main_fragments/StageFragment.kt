package `in`.creativelizard.advancepainter.fragments.main_fragments


import `in`.creativelizard.advancepainter.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_stage.view.*
import android.view.MotionEvent
import android.widget.RelativeLayout
















class StageFragment : Fragment() {

    lateinit var rootView:View
    private var mXDelta: Int = 0
    private var mYDelta: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_stage, container, false)
        initialize()
        onActionPerform()
        return rootView
    }

    private fun onActionPerform() {
        rootView.flWorkSpace.getChildAt(0)
            .setOnTouchListener { v, event ->
                val x = event.rawX.toInt()
                val y = event.rawY.toInt()
           when (event.action){
                MotionEvent.ACTION_DOWN -> {
                    val lParams = v.layoutParams as RelativeLayout.LayoutParams
                    mXDelta = x - lParams.leftMargin
                    mYDelta = y - lParams.topMargin

                    return@setOnTouchListener true
                }

               MotionEvent.ACTION_MOVE -> {
                   val layoutParams = v.layoutParams as RelativeLayout.LayoutParams
                   layoutParams.leftMargin = x - mXDelta
                   layoutParams.topMargin = y - mYDelta
                   layoutParams.rightMargin = -250
                   layoutParams.bottomMargin = -250
                   v.layoutParams = layoutParams


                   return@setOnTouchListener true
               }


           }

            return@setOnTouchListener false
        }
    }

    private fun initialize() {

    }

}
