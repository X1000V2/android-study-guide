package luis.barroso.androidstudyguide.androidCore.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextChangeViewmodel: ViewModel() {

    //var textChanged: String? = null

    // Create a LiveData with a String
    val textChanged: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}