package com.example.loginactivity

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.loginactivity.databinding.ActivityNavigationBinding
import com.facebook.login.LoginManager
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.nav_header_navigation.*
import java.util.*


enum class ProviderType {
    BASIC,
    GOOGLE,
    FACEBOOK
}

class HomeActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavigationBinding
    private var email : String? = null
    private var name : String? = null
    private var provider : String? = null
    private var Image : String? = null


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val permissionCheck =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                Toast.makeText(
                    this,
                    "Los permisos para encontrar el bluetooth son requeridos",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ), 1
                )
            }
        } else {
            Toast.makeText(this, "Permisos ya concedidos", Toast.LENGTH_SHORT).show()
        }


        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (email == document.id){
                        val prefs = getSharedPreferences(
                            getString(R.string.prefs_file),
                            Context.MODE_PRIVATE
                        ).edit()
                        for (data in document.data){
                            Log.d("DESGLOSE F", "${data.key} => ${data.value}")
                            if (data.key == "batt"){
                                prefs.putString("batt", data.value as String)
                                BatteryText.text = data.value as String
                            }
                            if (data.key == "freq"){
                                prefs.putString("freq", data.value as String)
                                FrequencyText.text = data.value as String
                            }
                            if (data.key == "height"){
                                prefs.putString("height", data.value as String)
                                HeightText.text = data.value as String
                            }
                            if (data.key == "step"){
                                prefs.putString("step", data.value as String)
                                FootstepText.text = data.value as String
                            }
                            if (data.key == "weight"){
                                prefs.putString("weight", data.value as String)
                                WeightText.text = data.value as String
                            }
                        }
                        prefs.apply()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents.", exception)
            }
        val bundle = intent.extras
        email = bundle?.getString("email")
        name = bundle?.getString("name")
        provider = bundle?.getString("provider")
        Image = bundle?.getString("photo")
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()

        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.putString("name", name)
        prefs.putString("photo", Image)


        prefs.apply()

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarNavigation.toolbar)

        //binding.appBarNavigation.fab.setOnClickListener { view ->
        //    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //            .setAction("Action", null).show()
        //}
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_navigation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_symptoms, R.id.nav_device, R.id.nav_history, R.id.nav_about
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        // etc.
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation, menu)
        //Setup

        setup(email ?: "", provider ?: "", name ?: "", Image ?: "")
        // Guardado de datos

        //MiBand.stopScan(scanCallback);


        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_navigation)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onBackPressed() {
        val lang = resources.getString(R.string.langua)
        if(lang == "en"){
            AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Logging Out")
                .setMessage("Are you sure you want to close this session?")
                .setPositiveButton("Yes"
                ) { dialog, which -> finish()
                    val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                    prefs.clear()
                    prefs.apply()

                    if (provider == ProviderType.FACEBOOK.name) {
                        LoginManager.getInstance().logOut()
                    }

                    FirebaseAuth.getInstance().signOut()
                }
                .setNegativeButton("No", null)
                .show()
        }
        if(lang == "es"){
            AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Cerrando Sesion")
                .setMessage("Estas seguro que quieres cerrar sesion?")
                .setPositiveButton("Yes"
                ) { dialog, which -> finish()
                    val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                    prefs.clear()
                    prefs.apply()

                    if (provider == ProviderType.FACEBOOK.name) {
                        LoginManager.getInstance().logOut()
                    }

                    FirebaseAuth.getInstance().signOut()
                }
                .setNegativeButton("No", null)
                .show()
        }
    }

    private fun setup(email: String, provider: String, name: String, photourl: String){
        title = "Inicio"
        var newname : String? = null

        if(name==""){
            newname = email
        }else{
            newname = name
        }

        emailPersona.text = email
        namePersona.text = newname
        logOutUpButton.text = newname

        try {
            Picasso.get()
                .load(photourl)
                .resize(200, 200)
                .centerCrop()
                .into(imagePersona)
            Picasso.get()
                .load(photourl)
                .resize(125, 125)
                .centerCrop()
                .into(UserImageView)
        }catch (e: Exception){}



        //providerTextView.text = provider

        logOutUpButton.setOnClickListener{

            val lang = resources.getString(R.string.langua)
            if(lang == "en"){
                AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Logging Out")
                    .setMessage("Did you want to close this session?")
                    .setPositiveButton("Yes"
                    ) { dialog, which -> finish()
                        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                        prefs.clear()
                        prefs.apply()

                        if (provider == ProviderType.FACEBOOK.name) {
                            LoginManager.getInstance().logOut()
                        }

                        FirebaseAuth.getInstance().signOut()
                        onBackPressed()
                    }
                    .setNegativeButton("No", null)
                    .show()

            }
            if(lang == "es"){
                AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Cerrando Sesion")
                    .setMessage("Quieres cerrar sesion?")
                    .setPositiveButton("Yes"
                    ) { dialog, which -> finish()
                        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                        prefs.clear()
                        prefs.apply()

                        if (provider == ProviderType.FACEBOOK.name) {
                            LoginManager.getInstance().logOut()
                        }

                        FirebaseAuth.getInstance().signOut()
                        onBackPressed()
                    }
                    .setNegativeButton("No", null)
                    .show()
            }
        }

        logoutImg.setOnClickListener{

            val lang = resources.getString(R.string.langua)
            if(lang == "en"){
                AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Logging Out")
                    .setMessage("Did you want to close this session?")
                    .setPositiveButton("Yes"
                    ) { dialog, which -> finish()
                        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                        prefs.clear()
                        prefs.apply()

                        if (provider == ProviderType.FACEBOOK.name) {
                            LoginManager.getInstance().logOut()
                        }

                        FirebaseAuth.getInstance().signOut()
                        onBackPressed()
                    }
                    .setNegativeButton("No", null)
                    .show()

            }
            if(lang == "es"){
                AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Cerrando Sesion")
                    .setMessage("Quieres cerrar sesion?")
                    .setPositiveButton("Yes"
                    ) { dialog, which -> finish()
                        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                        prefs.clear()
                        prefs.apply()

                        if (provider == ProviderType.FACEBOOK.name) {
                            LoginManager.getInstance().logOut()
                        }

                        FirebaseAuth.getInstance().signOut()
                        onBackPressed()
                    }
                    .setNegativeButton("No", null)
                    .show()
            }
        }
    }

}