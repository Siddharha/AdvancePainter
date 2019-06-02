package `in`.creativelizard.advancepainter.fragments.main_fragments


import `in`.creativelizard.advancepainter.R
import `in`.creativelizard.advancepainter.Utils.PainterCanvas
import `in`.creativelizard.advancepainter.activities.MainActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
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

        mToolbar.setNavigationOnClickListener {
            rootView.dlMain.openDrawer(Gravity.LEFT)
        }

        try {
            rootView.spMain.getChildAt(0).setOnTouchListener { v, event ->
                rootView.spMain.init(activity)
                return@setOnTouchListener false
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        (context as MainActivity).stageViewModel.page.observe(activity!!, Observer { pageHashmap ->
            //s.text = s
                val w = pageHashmap?.get("width")
            val h = pageHashmap?.get("height")
                createNewCanvas(w!!,h!!)
        })
    }

    private fun initialize() {
        setHasOptionsMenu(true)
        mToolbar = rootView.toolbar as Toolbar
        mToolbar.setNavigationIcon(R.drawable.ic_menu)
        (context as MainActivity).setSupportActionBar(mToolbar)
    }


    fun createNewCanvas(w:Int,h:Int){
       // pcDrawing
        rootView.spMain.removeAllViews()
        pcDrawing = PainterCanvas(activity,null)
        val param = LinearLayout.LayoutParams(w,
            h)
        param.setMargins(10,10,10,10)
        pcDrawing.layoutParams = param
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
