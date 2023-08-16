package bootcamp.sparta.notlame.myPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import bootcamp.sparta.notlame.MainPageActivity
import bootcamp.sparta.notlame.R

class MyPageActivity : AppCompatActivity() {
    // 핢일 추가하기 EditText
    private val todoEditText: EditText by lazy { findViewById(R.id.mypage_et_todo) }
    // 할일목록 입력 버튼
    private val todoBtn: Button by lazy { findViewById(R.id.mypage_btn_todo) }
    // 확인버튼
    private val doneBtn: Button by lazy { findViewById(R.id.mypage_btn_done) }
    // 할일목록 LinearLayout
    private val layout: LinearLayout by lazy { findViewById(R.id.mypage_linearlayout_todo) }

    // 이미지
    private val iv_Image : ImageView by lazy { findViewById(R.id.mypage_iv_image) }
    // 이름
    private val tv_name : TextView by lazy { findViewById(R.id.mypage_tv_name) }
    // 직책
    private val tv_position : TextView by lazy { findViewById(R.id.mypage_tv_position_value) }

    // 내 정보(이미지)
    private val intent_Image: Int by lazy { intent.getIntExtra(getString(R.string.intent_userImage), -1) }

    // 내 정보(이름)
    private val intent_Name: String? by lazy { intent.getStringExtra(getString(R.string.intent_userNameText)) }

    // 내 정보(직책)
    private val intent_Position: String? by lazy { intent.getStringExtra(getString(R.string.intent_userPositionText)) }

    // 할일목록 CheckBox관리를위한 List
    private var todoCheckBoxList: MutableList<CheckBox> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_page_activity)

        setLayoutData()
        registerButtonClickListeners()
    }

    // 인텐트에서 받아온 Data들을 Layout에 배치
    private fun setLayoutData() {
        iv_Image.setImageResource(intent_Image)
        tv_name.text = intent_Name
        tv_position.text = intent_Position
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
            finish()
        }
    }

    // 할일 목록 추가버튼
    private fun todoBtnOnClickListener() {
        todoBtn.setOnClickListener {
            // EditText 입력값이 있는지 체크
            if (todoEditText.text.length <= 0) {
                toastMsg(getString(R.string.mypage_edittext_null))
            } else {
                createAndAllocCheckBox()
                todoEditText.text = null
            }
        }
    }

    // 체크박스 생성 및 배치
    private fun createAndAllocCheckBox() {
        val checkBox = createCheckBox(todoEditText.text.toString())
        allocCheckBox(checkBox)
        todoCheckBoxList.add(checkBox)
        Log.d("MyPageActivity", checkBox.id.toString())
    }

    // 체크박스 컴포넌트 생성
    private fun createCheckBox(text: String): CheckBox {
        val checkBox = CheckBox(this)
        checkBox.text = text
        checkBox.id = todoCheckBoxList.size
        setCheckBoxOnClickListener(checkBox)
        return checkBox
    }

    // checkBox의 OnClick이벤트 발생시 Layout에서 해지
    private fun setCheckBoxOnClickListener(checkBox: CheckBox) {
        checkBox.setOnClickListener {
//            val selectedCheckBoxComponents = todoCheckBoxList.find { it.id == checkBox.id }
//            todoCheckBoxList.remove(selectedCheckBoxComponents)
            freeCheckBox(checkBox)
        }
    }

    // 체크박스 컴포넌트 Layout에 할당
    private fun allocCheckBox(checkBox: CheckBox) {
        layout.addView(
            checkBox,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )
    }

    // 체크박스 컴포넌트 Layout에 해지
    private fun freeCheckBox(checkBox: CheckBox) = layout.removeView(checkBox)

    // 토스트 메세지
    private fun toastMsg(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
