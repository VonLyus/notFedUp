package bootcamp.sparta.notlame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import java.util.regex.Pattern

class SignUpPageActivity : AppCompatActivity() {

    private val idPattern = Pattern.compile("^[a-zA-Z0-9]{5,10}\$")
    private val pwPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,15}\$")
    private val phonePattern = Pattern.compile("^[0-9]{10,11}\$")
    private val namePattern = Pattern.compile("^[가-힣a-zA-Z]*\$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_layout)

        val et_name = findViewById<EditText>(R.id.et_name)
        val et_id = findViewById<EditText>(R.id.et_id)
        val et_pw = findViewById<EditText>(R.id.et_pw)
        val et_phone = findViewById<EditText>(R.id.et_phone)
        val et_position = findViewById<EditText>(R.id.et_position)

        val btn_signUp = findViewById<Button>(R.id.btn_signupOk)
        val btn_signCancel = findViewById<Button>(R.id.btn_signupcancel)

        et_id.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val id = s.toString()
                val valid = idPattern.matcher(id).matches()
                if (!valid) {
                    et_id.error = "5~10자의 영어(대소문자)와 숫자만 입력하세요."
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        et_pw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val pw = s.toString()
                val valid = pwPattern.matcher(pw).matches()
                if (!valid) {
                    et_pw.error = "8~15자의 영어(대소문자), 숫자, 특수문자를 포함하세요."
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        et_phone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val phone = s.toString()
                val valid = phonePattern.matcher(phone).matches()
                if (!valid) {
                    et_phone.error = "10~11자의 숫자만 입력하세요."
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        et_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val name = s.toString()
                val valid = namePattern.matcher(name).matches()
                if (!valid) {
                    et_name.error = "한글 또는 영어만 입력하세요."
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        btn_signUp.setOnClickListener {
            val name = et_name.text.toString()
            val id = et_id.text.toString()
            val pw = et_pw.text.toString()
            val phone = et_phone.text.toString()
            val position = et_position.text.toString()

            if (name.isBlank() || id.isBlank() || pw.isBlank() || phone.isBlank() || position.isBlank()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val nameValid = namePattern.matcher(name).matches()
            val idValid = idPattern.matcher(id).matches()
            val pwValid = pwPattern.matcher(pw).matches()
            val phoneValid = phonePattern.matcher(phone).matches()

            if (!nameValid) {
                et_name.error = "한글 또는 영어만 입력하세요."
                return@setOnClickListener
            }

            if (!idValid) {
                et_id.error = "5~10자의 영어(대소문자)와 숫자만 입력하세요."
                return@setOnClickListener
            }

            if (!pwValid) {
                et_pw.error = "8~15자의 영어(대소문자), 숫자, 특수문자를 포함하세요."
                return@setOnClickListener
            }

            if (!phoneValid) {
                et_phone.error = "10~11자의 숫자만 입력하세요."
                return@setOnClickListener
            }

            //val intent = Intent(this, SignInPageActivity::class.java).apply {
                //putExtra("id", id)
                //putExtra("pw", pw)
            //}

            setResult(RESULT_OK, intent)
            if (!isFinishing) finish()
        }

        btn_signCancel.setOnClickListener {
            Toast.makeText(this@SignUpPageActivity, "취소 되었습니다.", Toast.LENGTH_SHORT).show()
            //val intent = Intent(this@SignUpActivity, SignInPageActivity::class.java)
            startActivity(intent)
            finish()
        }

        val iv_logo = findViewById<ImageView>(R.id.imageView)
        iv_logo.setOnClickListener {
            val id = when ((1..6).random()) {
                1 -> R.drawable.logo1
                2 -> R.drawable.logo2
                3 -> R.drawable.logo3
                4 -> R.drawable.logo4
                5 -> R.drawable.logo5
                else -> R.drawable.logo1
            }

            iv_logo.setImageDrawable(ResourcesCompat.getDrawable(resources, id, null))
        }
    }
}
