package com.example.loginactivity.ui

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

//#HACER SERVICIO DE CONEXION DE BLUETOOTH

class MyService : Service() {

    override fun onCreate() {
        Toast.makeText(this, "The new Service was Created", Toast.LENGTH_LONG).show()
    }

    override fun onStart(intent: Intent, startId: Int) {
        // For time consuming an long tasks you can launch a new thread here...
        // Do your Bluetooth Work Here
        Toast.makeText(this, " Service Started", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}

// startService(new Intent(this, MyService.class));
// stopService(new Intent(this, MyService.class));