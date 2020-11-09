package com.example.loginactivity.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.loginactivity.R
import com.example.loginactivity.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var Uname : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val prefs = this.activity?.getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        prefs?.getString("name",Uname)
        //val logOutButton: AppCompatButton = binding.logOutUpButton
        //homeViewModel.text.observe(viewLifecycleOwner, Observer {
        //    logOutButton.text = it
        //})

        return root
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        // etc.
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        val prefs = this.activity?.getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        Uname = prefs?.getString("name",null)
        val logOutButton: AppCompatButton = binding.logOutUpButton
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            logOutButton.text = Uname
        })
    }

    //override fun onViewStateRestored(savedInstanceState: Bundle?) {
    //    super.onViewStateRestored(savedInstanceState)
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
    //    val logOutButton: AppCompatButton = binding.logOutUpButton
    //    homeViewModel.text.observe(viewLifecycleOwner, Observer {
    //        logOutButton.text = savedInstanceState?.getString("MyName")
    //    })
    //}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}