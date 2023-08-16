package bootcamp.sparta.notlame.myPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import bootcamp.sparta.notlame.MainPageActivity
import bootcamp.sparta.notlame.R

class MyPageActivity : AppCompatActivity() {
    private lateinit var todoEditText : EditText // 핢일 추가하기 EditText
    private lateinit var todoBtn : Button // 할일목록 입력 버튼
    private lateinit var doneBtn : Button // 확인버튼
    private lateinit var todoCheckBoxList : ArrayList<CheckBox>
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = LayoutInflater.from(this).inflate(R.layout.my_page_activity, null)
        setContentView(view)
        initView()
        registerButtonClickListeners()
    }

    // button에 onClickLstener 등록
    private fun registerButtonClickListeners() {
        todoBtnOnClickListener()
        doneBtnOnClickListener()
    }

    // 확인버튼
    private fun doneBtnOnClickListener() {
        doneBtn.setOnClickListener {
            val intent = Intent(this, MainPageActivity::class.java)
            startActivity(intent)
        }
    }

    // 할일 목록 추가버튼
    private fun todoBtnOnClickListener() {
        todoBtn.setOnClickListener {

            // EditText 입력값이 있는지 체크
            if (todoEditText.text.length <= 0) {
                Toast.makeText(this, "할일을 추가하시려면 텍스트를 입력해주세요!", Toast.LENGTH_SHORT).show()
            }

            //CheckBoxList에서 visibility가 invisible인 제일 처음 값 가져온 후 EditText의 값 입력 후 visibility 상태변경
            todoCheckBoxList.find { it.visibility == View.INVISIBLE }?.let {
                it.text = todoEditText.text.toString()
                it.visibility = View.VISIBLE
            }
        }
    }

    private fun initView() {
        todoEditText = findViewById(R.id.mypage_et_todo)
        todoBtn  = findViewById(R.id.mypage_btn_todo)
        doneBtn = findViewById(R.id.mypage_btn_done)
        todoCheckBoxList = getCheckBoxList(view)
    }
}
