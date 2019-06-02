package `in`.creativelizard.advancepainter.activities

import `in`.creativelizard.advancepainter.R
import `in`.creativelizard.advancepainter.fragments.main_fragments.StageFragment
import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuInflater
import kotlinx.android.synthetic.main.create_new_canvas.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
/*
    fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.stage_menu, menu)
        return true
    }*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.stage_menu, menu)
        return true
    }

    fun createNewCanvasDialog() {
        val d = Dialog(this)
        d.setContentView(R.layout.create_new_canvas)
        d.btnCreate.setOnClickListener {
            createNewCanvas(d.etW.text.toString(),d.etH.text.toString())
            d.dismiss()
        }
        d.show()
    }

    private fun createNewCanvas(w: String, h: String) {


    }


}
