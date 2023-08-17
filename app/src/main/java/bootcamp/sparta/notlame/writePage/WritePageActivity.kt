package bootcamp.sparta.notlame.writePage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import bootcamp.sparta.notlame.R
import bootcamp.sparta.notlame.mainPage.MainPageActivity

class WritePageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_page)

        clickSet()
    }

    private fun clickSet(){

        val checkName = intent.getStringExtra("userName") ?: "name"
        val checkId = intent.getStringExtra("userId") ?: "id"
        val checkPw = intent.getStringExtra("userPw") ?: "pw"
        val checkTel = intent.getStringExtra("userTel") ?: "tel"
        val checkPosition = intent.getStringExtra("userPosition") ?: "position"
        val checkImage = intent.getIntExtra("userImage", 0)


        val checkBtn = findViewById<Button>(R.id.checkBtn)
        checkBtn.setOnClickListener{
            val writeTitle = findViewById<EditText>(R.id.writeTitle)
            val title = writeTitle.text.toString()

            val writeComment = findViewById<EditText>(R.id.writeComment)
            val comment = writeComment.text.toString()

            if (title.isNullOrEmpty()){
                Toast.makeText(this, "제목이 비어 있습니다 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if(comment.isNullOrEmpty()){
                Toast.makeText(this, "내용이 비어 있습니다. 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                // 값도 전달해야함
                val intent = Intent(this, MainPageActivity::class.java)

                intent.putExtra("userName", checkName)
                intent.putExtra("userId", checkId)
                intent.putExtra("userPw", checkPw)
                intent.putExtra("userTel", checkTel)
                intent.putExtra("userPosition", checkPosition)
                intent.putExtra("userImage", checkImage)

                intent.putExtra("Title",title)
                intent.putExtra("Comment",comment)

                //확인용 변수
                intent.putExtra("check", 1)

                startActivity(intent)
            }
        }

        val cancelBtn = findViewById<Button>(R.id.cancelBtn)
        cancelBtn.setOnClickListener{
            val intent = Intent(this, MainPageActivity::class.java)
            intent.putExtra("userName", checkName)
            intent.putExtra("userId", checkId)
            intent.putExtra("userPw", checkPw)
            intent.putExtra("userTel", checkTel)
            intent.putExtra("userPosition", checkPosition)
            intent.putExtra("userImage", checkImage)
            startActivity(intent)
        }
    }
}