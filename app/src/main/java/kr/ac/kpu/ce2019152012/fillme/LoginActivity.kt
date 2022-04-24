package kr.ac.kpu.ce2019152012.fillme

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

    private var user = Firebase.auth.currentUser
    private var uid = user?.uid.toString()

    private val itemsCollectionRef = db.collection("User")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)

        auth = Firebase.auth
        setup2()

        var UserList = arrayListOf<String>()

        itemsCollectionRef.get().addOnSuccessListener { result ->
                for(document in result) {
                    UserList.add(document.toString())
                    Log.d("list",UserList.toString())
                }
            }.addOnFailureListener { exception ->
                Log.d("Cloud",UserList.toString(),exception)
            }

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
                        if (binding.editId.text.toString().trim() in UserList) {
                            val intent = Intent(this, UserMainAcivity::class.java)
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                            finish()
                        } else {
                            Log.w(TAG, "Error getting documents ")
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