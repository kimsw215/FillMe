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
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.merge
import kr.ac.kpu.ce2019152012.fillme.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity() {

    private lateinit var binding:ActivityJoinBinding
    //nullable한 FirebaseAuth 객체 선언
    private lateinit var auth: FirebaseAuth
    private lateinit var db : FirebaseFirestore

    private var user = Firebase.auth.currentUser
    private var uid = user?.uid.toString()

    private val itemsCollectionRef = db.collection("User")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // auth 객체 초기화
        auth = FirebaseAuth.getInstance()
        setup2()

        binding.joinBackBtn.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.joinInBtn.setOnClickListener {
            val email = binding.joinIdEdit.text.toString().trim()
            val password = binding.joinPwEdit.text.toString().trim()

            val userInformation = hashMapOf(
                "email" to binding.joinIdEdit.text.toString().trim(),
                "passwd" to binding.joinPwEdit.text.toString().trim(),
                "nickname" to binding.joinNameEdit.text.toString().trim(),
            )
            itemsCollectionRef.document(uid)
                .set(userInformation, SetOptions.merge())
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

            createUser(email, password)
            // 회원가입 완료 후 로그인창으로 이동
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            // 이메일 형식 체크
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (task.isSuccessful) {
                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            } else {
                Toast.makeText(this, "이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    private fun reload() {

    }

    companion object {
        private const val TAG = "EmailPassword"
    }

    fun setup2(){
        db = FirebaseFirestore.getInstance()
        val settings = firestoreSettings {
            isPersistenceEnabled = true
        }
        db.firestoreSettings = settings
    }
}