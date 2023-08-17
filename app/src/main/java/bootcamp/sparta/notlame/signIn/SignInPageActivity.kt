package bootcamp.sparta.notlame.signIn

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bootcamp.sparta.notlame.R
import bootcamp.sparta.notlame.mainPage.MainPageActivity
import bootcamp.sparta.notlame.signUp.SignUpPageActivity

class SignInPageActivity : AppCompatActivity() {

    private lateinit var editTextId: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonSignUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_page)

        editTextId = findViewById(R.id.editTextId)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonSignUp = findViewById(R.id.buttonSignUp)

        buttonLogin.setOnClickListener {
            val id = editTextId.text.toString()
            val password = editTextPassword.text.toString()

            //값을 받아내야함

            val checkName = intent.getStringExtra("userName") ?: "name"
            val checkId = intent.getStringExtra("userId") ?: "id"
            val checkPw = intent.getStringExtra("userPw") ?: "pw"
            val checkTel = intent.getStringExtra("userTel") ?: "tel"
            val checkPosition = intent.getStringExtra("userPosition") ?: "position"
            val checkImage = intent.getIntExtra("userImage", 0)

            //값 판단이 안되어 있음

            if (id.isEmpty() || password.isEmpty()) {


                Toast.makeText(this, "아이디/비밀번호 둘 중 하나가 입력이 비어있습니다.", Toast.LENGTH_SHORT).show()
            } else if((id.equals(checkId)) && (password.equals(checkPw))){

                Toast.makeText(this, R.string.successLogin, Toast.LENGTH_SHORT).show()


//                // Get the user information from SharedPreferences
//                val sharedPrefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)
//                val name = sharedPrefs.getString("userName", "이름 없음")
//                val age = sharedPrefs.getString("userAge", "나이 없음")
//                val mbti = sharedPrefs.getString("userMBTI", "MBTI 없음")
// intent로 값을 넘기는 것으로 정했어서 우선 수정하겠습니다

                val intent = Intent(this, MainPageActivity::class.java)

                intent.putExtra("userName", checkName)
                intent.putExtra("userId", checkId)
                intent.putExtra("userPw", checkPw)
                intent.putExtra("userTel", checkTel)
                intent.putExtra("userPosition", checkPosition)
                intent.putExtra("userImage", checkImage)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, R.string.checkIDPassword, Toast.LENGTH_SHORT).show()
            }
        }

        buttonSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpPageActivity::class.java))
        }
    }
}