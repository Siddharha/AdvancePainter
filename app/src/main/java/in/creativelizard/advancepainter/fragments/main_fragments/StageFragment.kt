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
    private var isSingleTouch:Boolean = false
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
        rootView.spMain.getChildAt(0).setOnTouchListener { v, event ->
            rootView.spMain.init(activity)
            return@setOnTouchListener false
        }
    }

    private fun initialize() {

    }

}
