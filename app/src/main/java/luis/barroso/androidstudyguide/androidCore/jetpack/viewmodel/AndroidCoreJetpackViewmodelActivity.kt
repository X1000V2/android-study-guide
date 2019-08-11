package luis.barroso.androidstudyguide.androidCore.jetpack.viewmodel

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_android_core_jetpack_viewmodel.*
import luis.barroso.androidstudyguide.R

class AndroidCoreJetpackViewmodelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_core_jetpack_viewmodel)

        val model = ViewModelProviders.of(this)[TextChangeViewmodel::class.java]
        model.textChanged.observe(this, Observer<String> {text ->
            text_view_change_text.text = text
        })

        button_save.setOnClickListener {
            model.textChanged.value = edit_text_change_text.text.toString()
        }
    }
}
