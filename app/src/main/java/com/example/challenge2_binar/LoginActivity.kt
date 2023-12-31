package com.example.challenge2_binar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.challenge2_binar.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.core.Context

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()


//        binding.crashButton.setOnClickListener {
//            throw RuntimeException("Test Crash") // Force a crash
//        }


        binding.btnToRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.inputUsername.text.toString()
            val password = binding.inputPassword.text.toString()

            if(email.isEmpty()){
                binding.inputUsername.error = "Input email terlebih dahulu!"
                binding.inputUsername.requestFocus()
                return@setOnClickListener
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.inputUsername.error = "Input email tidak valid!"
                binding.inputUsername.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                binding.inputPassword.error = "Input password terlebih dahulu!"
                binding.inputPassword.requestFocus()
                return@setOnClickListener
            }

            loginFirebase(email, password)
        }
    }

//    private fun checkLogin(){
//        if(sharedPreference.getStatusLogin()){
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            Toast.makeText(this, "Selamat datang kembali", Toast.LENGTH_SHORT).show()
//        }
//    }

    private fun loginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it. isSuccessful){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Selamat datang $email", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}