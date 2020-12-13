package com.example.loginactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import kotlinx.android.synthetic.main.activity_send_o_t_p.*
import java.util.concurrent.TimeUnit


class SendOTP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_o_t_p)
        val inputMobile = findViewById<EditText>(R.id.inputMobile)
        val buttonGetOTP = findViewById<Button>(R.id.buttonGetOTP)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        buttonGetOTP.setOnClickListener(View.OnClickListener {
            if (inputMobile.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(this@SendOTP, "Ingrese Móvil", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            progressBar.visibility = View.VISIBLE
            buttonGetOTP.visibility = View.INVISIBLE


            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+52" + inputMobile.text.toString(),
                20,
                TimeUnit.SECONDS,
                this@SendOTP,
                object : OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                        progressBar.visibility = View.GONE
                        buttonGetOTP.visibility = View.VISIBLE
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        progressBar.visibility = View.GONE
                        buttonGetOTP.visibility = View.VISIBLE
                        Toast.makeText(this@SendOTP, "Hubo un Error", Toast.LENGTH_SHORT).show()
                    }

                    override fun onCodeSent(
                        verificationId: String,
                        forceResendingToken: ForceResendingToken
                    ) {
                        progressBar.visibility = View.GONE
                        buttonGetOTP.visibility = View.VISIBLE
                        val bundle = intent.extras
                        val email = bundle?.getString("email")
                        val name = bundle?.getString("name")
                        val provider = bundle?.getString("provider")
                        val photo = bundle?.getString("photo")
                        val intent = Intent(applicationContext, VerifyOTP::class.java)
                        intent.putExtra("email", email)
                        intent.putExtra("name", name)
                        intent.putExtra("provider", provider)
                        intent.putExtra("photo", photo)
                        intent.putExtra("movil", inputMobile.text.toString())
                        intent.putExtra("verificationId", verificationId)
                        startActivity(intent)
                    }
                }
            )

            //val intent = Intent(applicationContext, VerifyOTP::class.java)
            //intent.putExtra("movil", inputMobile.text.toString())
            //startActivity(intent)
        })
    }
}