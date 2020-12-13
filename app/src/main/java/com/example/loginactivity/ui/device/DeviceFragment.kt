package com.example.loginactivity.ui.device

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.loginactivity.R
import com.example.loginactivity.databinding.FragmentDeviceBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_device.*


class DeviceFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()
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


        return root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val prefs = this.activity?.getSharedPreferences(
            getString(R.string.prefs_file),
            Context.MODE_PRIVATE
        )

        deviceViewModel.text.observe(viewLifecycleOwner, Observer {

            val email = prefs?.getString("email", null)
            db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        if (email == document.id) {
                            val prefset = this.activity?.getSharedPreferences(
                                getString(R.string.prefs_file),
                                Context.MODE_PRIVATE
                            )
                                ?.edit()
                            for (data in document.data) {
                                Log.d("DESGLOSE F", "${data.key} => ${data.value}")
                                if (data.key == "smartband") {
                                    val map: Map<String, Any> = data.value as Map<String, Any>
                                    for (newData in map) {
                                        if (newData.key == "devicename") {
                                            Log.d(
                                                "devicename",
                                                "---------------->\n ${newData.key} => ${newData.value}"
                                            )
                                            prefset?.putString(
                                                "devicename",
                                                newData.value as String
                                            )
                                            VersionText.text = newData.value as String
                                        }
                                        if (newData.key == "deviceaddress") {
                                            Log.d(
                                                "deviceaddress",
                                                "---------------->\n ${newData.key} => ${newData.value}"
                                            )
                                            prefset?.putString(
                                                "deviceaddress",
                                                newData.value as String
                                            )
                                            DeviceText.text = newData.value as String
                                        }
                                    }
                                }
                            }
                            prefset?.apply()
                        }
                    }
                }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}