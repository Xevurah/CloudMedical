package com.example.loginactivity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.TimeUnit


class VerifyOTP : AppCompatActivity()  {
    private val db = FirebaseFirestore.getInstance()
    private lateinit var inputCode1:EditText
    private lateinit var inputCode2:EditText
    private lateinit var inputCode3:EditText
    private lateinit var inputCode4:EditText
    private lateinit var inputCode5:EditText
    private lateinit var inputCode6:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_o_t_p)
        val textMobile = findViewById<TextView>(R.id.textMobile)
        textMobile.text = String.format(
            "+52-%s", intent.getStringExtra("movil")
        )
        inputCode1 = findViewById<EditText>(R.id.inputCode1)
        inputCode2 = findViewById<EditText>(R.id.inputCode2)
        inputCode3 = findViewById<EditText>(R.id.inputCode3)
        inputCode4 = findViewById<EditText>(R.id.inputCode4)
        inputCode5 = findViewById<EditText>(R.id.inputCode5)
        inputCode6 = findViewById<EditText>(R.id.inputCode6)

        setupOTPInputs()

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val buttonVerify = findViewById<Button>(R.id.buttonVerify)

        var verificationId = intent.getStringExtra("verificationId")

        buttonVerify.setOnClickListener {
            if (inputCode1.text.toString().trim().isEmpty()
                    ||inputCode2.text.toString().trim().isEmpty()
                    ||inputCode3.text.toString().trim().isEmpty()
                    ||inputCode4.text.toString().trim().isEmpty()
                    ||inputCode5.text.toString().trim().isEmpty()
                    ||inputCode6.text.toString().trim().isEmpty()
                ){
                    Toast.makeText(this, "Por favor ingrese un codigo valido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val code = inputCode1.text.toString() + inputCode2.text.toString() + inputCode3.text.toString() + inputCode4.text.toString() + inputCode5.text.toString() + inputCode6.text.toString()

            if(verificationId != null){
                progressBar.visibility = View.VISIBLE
                buttonVerify.visibility = View.INVISIBLE
                val phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId!!, code)
                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                    .addOnCompleteListener(this){ task ->
                        if (task.isSuccessful) {
                            val bundle = intent.extras
                            val email = bundle?.getString("email")
                            val name = bundle?.getString("name")
                            val provider = bundle?.getString("provider")
                            val photo = bundle?.getString("photo")
                            if (email != null) {
                                db.collection("users").document(email).update(
                                    hashMapOf(
                                        "celphone" to "" + String.format(
                                            "+52-%s", intent.getStringExtra("movil")
                                        ) + ""
                                    ) as Map<String, Any>
                                )
                            }
                            val homeIntent = Intent(applicationContext, HomeActivity::class.java).apply {
                                putExtra("email", email)
                                putExtra("name", name)
                                putExtra("provider",provider)
                                putExtra("photo",photo)
                            }
                            homeIntent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                            startActivity(homeIntent)
                        } else {
                            Toast.makeText(this,"El código de verificacion era invalido", Toast.LENGTH_SHORT).show();
                            progressBar.visibility = View.GONE
                            buttonVerify.visibility = View.VISIBLE
                        }
                }
            }
        }

        findViewById<TextView>(R.id.textResendOTP).setOnClickListener {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+52" + (intent.extras?.getString("movil") ?: ""),
                60,
                TimeUnit.SECONDS,
                this@VerifyOTP,
                object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        Toast.makeText(this@VerifyOTP, "Hubo un Error", Toast.LENGTH_SHORT).show()
                    }

                    override fun onCodeSent(
                        newVerificationId: String,
                        forceResendingToken: PhoneAuthProvider.ForceResendingToken
                    ) {
                        verificationId = newVerificationId
                        Toast.makeText(this@VerifyOTP, "OTP enviado", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }

    }

    private fun setupOTPInputs() {
        inputCode1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().trim { it <= ' ' }.isNotEmpty()) {
                    inputCode2.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        inputCode2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().trim { it <= ' ' }.isNotEmpty()) {
                    inputCode3.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        inputCode3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().trim { it <= ' ' }.isNotEmpty()) {
                    inputCode4.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        inputCode4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().trim { it <= ' ' }.isNotEmpty()) {
                    inputCode5.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        inputCode5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().trim { it <= ' ' }.isNotEmpty()) {
                    inputCode6.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }
}