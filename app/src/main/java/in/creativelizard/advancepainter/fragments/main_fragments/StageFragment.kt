package `in`.creativelizard.advancepainter.fragments.main_fragments


import `in`.creativelizard.advancepainter.R
import `in`.creativelizard.advancepainter.Utils.PainterCanvas
import `in`.creativelizard.advancepainter.activities.MainActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_stage.view.*
import java.lang.Exception


class StageFragment : Fragment() {


    lateinit var rootView:View
    lateinit var mToolbar: Toolbar
    private var isDrawingModeChecked = false
    lateinit var pcDrawing: PainterCanvas
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

        try {
            rootView.spMain.getChildAt(0).setOnTouchListener { v, event ->
                rootView.spMain.init(activity)
                return@setOnTouchListener false
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun initialize() {
        setHasOptionsMenu(true)
        mToolbar = rootView.toolbar as Toolbar
        (context as MainActivity).setSupportActionBar(mToolbar)

    }


    fun createNewCanvas(w:Int,h:Int){
       // pcDrawing

        pcDrawing = PainterCanvas(activity,null)
        pcDrawing.layoutParams = LinearLayout.LayoutParams(w,
            h)

        rootView.spMain.addView(pcDrawing)
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

                try{
                    pcDrawing.isCanvasInDrawMode = isDrawingModeChecked
                }catch (e:Exception){
                    Toast.makeText(activity!!,"Please Create a Canvas First!",Toast.LENGTH_SHORT).show()
                }
            }
        }
        return false
    }

}
