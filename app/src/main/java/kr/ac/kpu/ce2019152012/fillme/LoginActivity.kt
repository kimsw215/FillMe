package kr.ac.kpu.ce2019152012.fillme

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
import kr.ac.kpu.ce2019152012.fillme.databinding.ActivityLoginBinding
import kr.ac.kpu.ce2019152012.fillme.user.UserMainAcivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)

        auth = FirebaseAuth.getInstance()
        setup2()
        val UserList = mutableListOf<String>()

        binding.loginBtn.setOnClickListener {
            auth.signInWithEmailAndPassword(
                binding.editId.text.toString().trim(),
                binding.editPwEdit.text.toString().trim()
            )
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                        val user = auth?.currentUser
                        updateUI(user)
                        db.collection(binding.editId.text.toString().trim())
                            .document("information")
                            .get().addOnSuccessListener {
                                UserList.add(it["email"].toString().trim())
                                Log.d("list",UserList.toString())
                            }
                        if (binding.editId.text.toString().trim() in UserList) {
                            val intent = Intent(this, UserMainAcivity::class.java)
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                            finish()
                        } else {
                            Log.w("Login", "Error getting documents ")
                        }
                    } else {
                        Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }

        binding.joinText.setOnClickListener {
            val intent = Intent(this,JoinActivity::class.java)
            startActivity(intent)
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accroingly.
        val currenUser = auth.currentUser
        if (currenUser != null) {
            reload()
        }
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    private fun reload() {

    }

    fun setup2() {
        db = FirebaseFirestore.getInstance()
        val settings = firestoreSettings {
            isPersistenceEnabled = true
        }
        db.firestoreSettings = settings
    }

    companion object {
        private const val TAG = "error"
    }
}