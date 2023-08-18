package bootcamp.sparta.notlame.writePage

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import bootcamp.sparta.notlame.R
import bootcamp.sparta.notlame.mainPage.MainPageActivity
import com.google.android.material.snackbar.Snackbar

class WritePageActivity : AppCompatActivity() {


    private lateinit var title: String
    private lateinit var comment: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_page)


        clickSet()
    }


    private fun clickSet(){


        val writeTitle = findViewById<EditText>(R.id.writeTitle)
        val writeComment = findViewById<EditText>(R.id.writeComment)

        val checkBtn = findViewById<Button>(R.id.checkBtn)
        checkBtn.setOnClickListener{

            title = writeTitle.text.toString()

            comment = writeComment.text.toString()

            if (title.isNullOrEmpty()){
                Toast.makeText(this, "제목이 비어 있습니다 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if(comment.isNullOrEmpty()){
                Toast.makeText(this, "내용이 비어 있습니다. 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if((title.length<1)&&(comment.length<10)){
                Toast.makeText(this, "제목의 최소길이는 1글자 이상, 내용은 최소글자는 10글자 이상입니다.", Toast.LENGTH_SHORT).show()
            }
            else{
                // 값도 전달해야함

                val intent = Intent()//(this, MainPageActivity::class.java)
//
                intent.putExtra("Title",title)
                intent.putExtra("Comment",comment)
                //확인용 변수
                intent.putExtra("check", 1)
                //registerForActivityResult를 사용
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

        val cancelBtn = findViewById<Button>(R.id.cancelBtn)
        cancelBtn.setOnClickListener{


            title = writeTitle.text.toString()

            comment = writeComment.text.toString()

            if(title.isNullOrEmpty() && comment.isNullOrEmpty()){
                val intent = Intent()//(this, MainPageActivity::class.java)
                setResult(Activity.RESULT_OK, intent)
                finish()

            }

            else{
                val intent = Intent(this, MainPageActivity::class.java)
//
                var snackBar = Snackbar.make(it, R.string.warningBack, Snackbar.LENGTH_SHORT)
                    .setAction(R.string.button_done, View.OnClickListener {

                        setResult(Activity.RESULT_OK, intent)
                        finish()

                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    })
                snackBar.show()
            }

        }
    }
}