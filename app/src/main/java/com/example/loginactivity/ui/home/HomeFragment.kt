package com.example.loginactivity.ui.home

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.loginactivity.ProviderType
import com.example.loginactivity.R
import com.example.loginactivity.databinding.FragmentHomeBinding
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import com.zhaoxiaodan.miband.ActionCallback
import com.zhaoxiaodan.miband.MiBand
import kotlinx.android.synthetic.main.fragment_home.*
import net.steamcrafted.loadtoast.LoadToast
import kotlin.concurrent.thread
import kotlin.properties.Delegates.observable


class HomeFragment : Fragment() {
    lateinit var lt: LoadToast
    var miband = MiBand(this.activity)
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var udevicename: String? = null
    private var uname: String? = null
    private var uprovider: String? = null
    private var photourl: String? = null
    private var isconnected: Boolean = false


    private val scanCallback: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            var devicecont = 0
            val device: BluetoothDevice = result.device
            //Log.d(
            //    TAG,
            //    "Buscar un dispositivo Bluetooth cercano: " +
            //            "name:" + device.name +
            //            ",uuid:" + device.uuids + ",add:"
            //            + device.address + ",type:"
            //            + device.type + ",bondState:"
            //            + device.bondState + ",rssi:" + result.rssi
            //)
            if (device.name == "Mi Smart Band 4") {
                while (!listenToConnect(device)) {
                }
                lt.success()
                lt.hide()
                isconnected = true
                udevicename = "Mi Smart Band 4"
                val prefs = activity?.getSharedPreferences(
                    getString(R.string.prefs_file),
                    Context.MODE_PRIVATE
                )?.edit()
                prefs?.putString("devicename", device.name)
                prefs?.putString("deviceuuids", device.uuids.toString())
                prefs?.putString("deviceaddress", device.address)
                prefs?.putString("devicetype", device.type.toString())
                prefs?.putString("devicebondstate", device.bondState.toString())
                prefs?.putString("devicerssi", result.rssi.toString())
                prefs?.apply()
            }
            if (device.name == "Mi Smart Band 5") {
                while (!listenToConnect(device)) {
                }
                lt.success()
                lt.hide()
                isconnected = true
                udevicename = "Mi Smart Band 5"
                val prefs = activity?.getSharedPreferences(
                    getString(R.string.prefs_file),
                    Context.MODE_PRIVATE
                )?.edit()
                prefs?.putString("devicename", device.name)
                prefs?.putString("deviceuuids", device.uuids.toString())
                prefs?.putString("deviceaddress", device.address)
                prefs?.putString("devicetype", device.type.toString())
                prefs?.putString("devicebondstate", device.bondState.toString())
                prefs?.putString("devicerssi", result.rssi.toString())
                prefs?.apply()
            }
        }
    }
    private val REQUEST_ENABLE_BT = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val prefs = this.activity?.getSharedPreferences(
            getString(R.string.prefs_file),
            Context.MODE_PRIVATE
        )
        prefs?.getString("name", uname)
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
        val prefs = this.activity?.getSharedPreferences(
            getString(R.string.prefs_file),
            Context.MODE_PRIVATE
        )
        uname = prefs?.getString("name", null)
        uprovider = prefs?.getString("provider", null)
        photourl = prefs?.getString("photo", null)
        val logOutButton: AppCompatButton = binding.logOutUpButton
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            logOutButton.text = uname
            Picasso.get()
                .load(photourl)
                .resize(125, 125)
                .centerCrop()
                .into(UserImageView)
            logOutUpButton.setOnClickListener {

                val prefsedit = this.activity?.getSharedPreferences(
                    getString(R.string.prefs_file),
                    Context.MODE_PRIVATE
                )?.edit()
                prefsedit?.clear()
                prefsedit?.apply()

                if (uprovider == ProviderType.FACEBOOK.name) {
                    LoginManager.getInstance().logOut()
                }

                FirebaseAuth.getInstance().signOut()
                this.activity?.onBackPressed()
            }
            ConnectButton.setOnClickListener {
                if (isconnected) {
                    Toast.makeText(this.activity, "Usted ya esta conectado a: $udevicename", Toast.LENGTH_SHORT).show()
                } else {
                    val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
                    if (bluetoothAdapter?.isEnabled == false) {
                        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
                    }

                    lt = LoadToast(this.activity)
                    lt.setText("Buscando SmartBand")
                    lt.setTranslationY(1468)
                    lt.show()
                    MiBand.startScan(scanCallback)
                }
            }
        })
    }

    fun listenToConnect(device: BluetoothDevice): Boolean {
        var promise = false
        miband.connect(device, object : ActionCallback {
            override fun onSuccess(data: Any?) {
                Log.d(TAG, "connect success")
                MiBand.stopScan(scanCallback)
                promise = true
            }

            override fun onFail(errorCode: Int, msg: String) {
                Log.d(TAG, "connect fail, code:$errorCode,mgs:$msg");
                MiBand.stopScan(scanCallback)
                promise = false
            }
        })
        Thread.sleep(3000)
        return promise
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