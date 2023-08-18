package bootcamp.sparta.notlame.detailPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import bootcamp.sparta.notlame.R
import com.google.android.material.snackbar.Snackbar

class DetailPage : AppCompatActivity() {
    private val detailInputBtn: Button by lazy { findViewById<Button>(R.id.detailInputBtn) }
    private val detailInputArea: LinearLayout by lazy { findViewById<LinearLayout>(R.id.detailInputArea) }
    private val detailInputEdit: EditText by lazy { findViewById<EditText>(R.id.detailInputEdit) }
    private val detailSubject:TextView by lazy {findViewById<TextView>(R.id.detailSubject) }
    private val detailContent: TextView by lazy { findViewById<TextView>(R.id.detailContent) }
    private val detailName:TextView by lazy { findViewById<TextView>(R.id.detailName) }
    private val detailImage: ImageView by lazy { findViewById<ImageView>(R.id.detailImage) }
    private val userImageComment: ImageView by lazy { findViewById<ImageView>(R.id.userImageComment) }
    private val backBtn:ImageButton by lazy { findViewById<ImageButton>(R.id.imageButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        val userName = intent.getStringExtra("userName")?:"이름 없음"
        val userImage = intent.getIntExtra("userImage", R.drawable.mypage_dummy_image)
        detailSubject.setText(intent.getStringExtra("detailSubject"))
        detailContent.setText(intent.getStringExtra("detailContent"))
        detailName.setText(userName)

        detailImage.setImageResource(userImage)
        userImageComment.setImageResource(userImage)
        detailImage.clipToOutline = true
        userImageComment.clipToOutline = true

        //뒤로 가기 버튼
        backBtn.setOnClickListener {
            var snackBar = Snackbar.make(it, R.string.warningBack, Snackbar.LENGTH_SHORT).setAction(R.string.button_done, View.OnClickListener {
                finish()
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            })
            snackBar.show()
        }

        //댓글 입력 버튼
        detailInputBtn.setOnClickListener {
            val text = detailInputEdit.text.toString()
            if(text.isBlank()){
                Toast.makeText(this, R.string.warningNull, Toast.LENGTH_SHORT).show()
            }else{
                inputText(text, userName)
                detailInputEdit.setText(/* text = */ null)
            }
        }
    }

    // 댓글 생성
    fun inputText(text:String, userName:String){
        val newText = TextView(this)
        newText.setText(/* text = */ "$userName : $text")
        detailInputArea.addView(newText)
    }
}