package com.example.loginactivity.ui.diagnose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.loginactivity.databinding.FragmentDiagnoseBinding

class DiagnoseFragment : Fragment() {

    private lateinit var diagnoseViewModel: DiagnoseViewModel
    private var _binding: FragmentDiagnoseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        diagnoseViewModel =
                ViewModelProvider(this).get(DiagnoseViewModel::class.java)

        _binding = FragmentDiagnoseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textSlideshow
        //slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
        //    textView.text = it
        //})
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}