package luis.barroso.androidstudyguide.androidCore.jetpack.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_navigation_main.*

import luis.barroso.androidstudyguide.R

class NavigationMainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_navigation_main, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_navigation_main_go_to_screen_1.setOnClickListener { goToScreen1() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun goToScreen1() {
        view?.findNavController()?.navigate(R.id.action_navigationMainFragment_to_navigationScreen1Fragment)
    }
}
