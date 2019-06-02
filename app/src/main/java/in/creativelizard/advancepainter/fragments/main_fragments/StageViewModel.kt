package `in`.creativelizard.advancepainter.fragments.main_fragments

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class StageViewModel : ViewModel() {
    var page: MutableLiveData<HashMap<String,Int>> = MutableLiveData()
}