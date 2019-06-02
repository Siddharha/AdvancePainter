package `in`.creativelizard.advancepainter.fragments.drawer_menu


import `in`.creativelizard.advancepainter.R
import `in`.creativelizard.advancepainter.activities.MainActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_drawer.view.*


class DrawerFragment : Fragment() {

    lateinit var rootView:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_drawer, container, false)
        onActionPerform()
        return rootView
    }

    private fun onActionPerform() {
        rootView.llNewCanvas.setOnClickListener {
            (context as MainActivity).createNewCanvasDialog()

        }


    }


}
