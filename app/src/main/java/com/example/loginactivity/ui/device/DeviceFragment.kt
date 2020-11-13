package com.example.loginactivity.ui.device

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.loginactivity.databinding.FragmentDeviceBinding

class DeviceFragment : Fragment() {

    private lateinit var deviceViewModel: DeviceViewModel
    private var _binding: FragmentDeviceBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        deviceViewModel =
                ViewModelProvider(this).get(DeviceViewModel::class.java)

        _binding = FragmentDeviceBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textDevice
        //galleryViewModel.text.observe(viewLifecycleOwner, Observer {
        //    textView.text = it
       // })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}