package luis.barroso.androidstudyguide

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_navigation_screen1.*

class NavigationScreen1Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_screen1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_navigation_main_go_to_screen_2.setOnClickListener { goToScreen2() }
    }

    private fun goToScreen2(){

        val action = NavigationScreen1FragmentDirections.actionNavigationScreen1FragmentToNavigationScreen2Fragment(textReceived = text_input_edit_text_name.text.toString())
        view?.findNavController()?.navigate(action)
    }
}
