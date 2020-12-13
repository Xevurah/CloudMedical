package com.example.loginactivity.ui.history

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.loginactivity.R
import com.example.loginactivity.databinding.FragmentHistoryBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_device.*
import kotlinx.android.synthetic.main.fragment_history.*


class HistoryFragment : Fragment() {
    private val db = FirebaseFirestore.getInstance()

    private lateinit var historyViewModel: HistoryViewModel
    private var _binding: FragmentHistoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyViewModel =
                ViewModelProvider(this).get(HistoryViewModel::class.java)

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textSlideshow


        return root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        historyViewModel.text.observe(viewLifecycleOwner, Observer {
            //<TextView
            //android:id="@+id/StatDataView"
            //android:layout_width="match_parent"
            //android:layout_height="50dp"
            //android:fontFamily="sans-serif-black"
            //android:gravity="center"
            //android:text="@string/history_text"
            //android:textColor="@color/white"
            //android:textSize="18sp" />
            val prefs = this.activity?.getSharedPreferences(
                getString(R.string.prefs_file),
                Context.MODE_PRIVATE
            )
            var title:String = ""
            var titletext:String = ""
            var subtitle:String = ""
            var subtitletext:String = ""
            val email = prefs?.getString("email", null)
            db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        if (email == document.id) {
                            for (data in document.data) {
                                Log.d("DESGLOSE F", "${data.key} => ${data.value}")
                                if (data.key == "history") {
                                    val map: Map<String, Any> = data.value as Map<String, Any>
                                    for (newData in map) {
                                        val newmap: Map<String, Any> = newData.value as Map<String, Any>
                                        for (newNewData in newmap) {
                                            if (newNewData.key == "AUTOCUIDADO") {
                                                Log.d(
                                                    "AUTOCUIDADO",
                                                    "---------------->\n ${newNewData.key} => ${newNewData.value}"
                                                )
                                                title = newNewData.key
                                                titletext = newNewData.value as String
                                                val textView = TextView(this.activity)
                                                textView.textSize = 18F
                                                textView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                                                textView.gravity = Gravity.CENTER
                                                textView.text = ""+title+"\n"+titletext
                                                linearhistory.addView(textView)
                                            }
                                            if (newNewData.key == "DIAGNOSTICO") {
                                                Log.d(
                                                    "DIAGNOSTICO",
                                                    "---------------->\n ${newNewData.key} => ${newNewData.value}"
                                                )
                                                subtitle = newNewData.key
                                                subtitletext = newNewData.value as String
                                                val textView = TextView(this.activity)
                                                textView.textSize = 18F
                                                textView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                                                textView.gravity = Gravity.CENTER
                                                textView.text = ""+subtitle+"\n"+subtitletext
                                                linearhistory.addView(textView)
                                            }
                                        }
                                    }
                                }
                            }
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