package `in`.creativelizard.advancepainter.fragments.main_fragments


import `in`.creativelizard.advancepainter.R
import `in`.creativelizard.advancepainter.Utils.AppUtil
import `in`.creativelizard.advancepainter.Utils.PainterCanvas
import `in`.creativelizard.advancepainter.activities.MainActivity
import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_stage.view.*
import kotlinx.android.synthetic.main.save_image.*
import java.lang.Exception
import android.graphics.Bitmap
import android.graphics.Canvas


class StageFragment : Fragment() {


    lateinit var rootView:View
    lateinit var mToolbar: Toolbar
    private var isDrawingModeChecked = false
    lateinit var pcDrawing: PainterCanvas
     var pageW:Int = 0
     var pageH:Int = 0
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
            rootView.dlMain.openDrawer(Gravity.START)
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
                 pageW = pageHashmap?.get("width")!!
             pageH = pageHashmap?.get("height")!!
                createNewCanvas(pageW,pageH)
        })
    }

    private fun initialize() {
        setHasOptionsMenu(true)
        mToolbar = rootView.toolbar as Toolbar
        mToolbar.setNavigationIcon(R.drawable.ic_menu)
        (context as MainActivity).setSupportActionBar(mToolbar)
    }


    val createNewCanvas:(Int,Int)->Unit = {w,h ->

        val pxW = AppUtil.convertPixelsToDp(w,activity!!).toInt()
        val pxH = AppUtil.convertPixelsToDp(h,activity!!).toInt()
       // pcDrawing
        rootView.spMain.removeAllViews()
        pcDrawing = PainterCanvas(activity,null)
        val param = LinearLayout.LayoutParams(pxW,
            pxH)
        param.setMargins(10,10,10,10)
        pcDrawing.layoutParams = param
        rootView.spMain.addView(pcDrawing)
        rootView.dlMain.closeDrawer(Gravity.START)
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

            R.id.mnuSave ->{saveImagePopup()}
            R.id.mnuUndo ->{pcDrawing.pathArrayList.removeAt(pcDrawing.pathArrayList.size-1)
                pcDrawing.path.reset()
            pcDrawing.invalidate()
            }
        }
        return false
    }

    private fun saveImagePopup() {
        val d = Dialog(activity!!)
        d.setContentView(R.layout.save_image)
        d.btnSave.setOnClickListener {
            val fileName = d.etFileName.text.toString()

           /* val bitmap = Bitmap.createBitmap(pageW, pageH, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)*/


        }
        d.show()
    }

}
