package `in`.creativelizard.advancepainter.fragments.main_fragments


import `in`.creativelizard.advancepainter.R
import `in`.creativelizard.advancepainter.activities.MainActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.*
import kotlinx.android.synthetic.main.fragment_stage.view.*


class StageFragment : Fragment() {

    lateinit var rootView:View
    lateinit var mToolbar: Toolbar
    private var isDrawingModeChecked = false
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
        setHasOptionsMenu(true)
        mToolbar = rootView.toolbar as Toolbar
        (context as MainActivity).setSupportActionBar(mToolbar)

    }

    override fun onPrepareOptionsMenu(menu: Menu?) {

        val checkable = menu?.findItem(R.id.mnuDrawSwitch)
        checkable?.isChecked = isDrawingModeChecked
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.mnuDrawSwitch ->{
                isDrawingModeChecked = !item.isChecked
                item.isChecked = isDrawingModeChecked

                rootView.pcDrawing.isCanvasInDrawMode = isDrawingModeChecked
            }
        }
        return false
    }

}
