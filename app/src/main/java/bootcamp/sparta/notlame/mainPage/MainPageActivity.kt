package bootcamp.sparta.notlame.mainPage

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import bootcamp.sparta.notlame.R
import bootcamp.sparta.notlame.detailPage.DetailPage
import bootcamp.sparta.notlame.writePage.WritePageActivity

class MainPageActivity : AppCompatActivity() {
    private lateinit var getContent: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        setResultSignUp()
        btnClickSet()

    }

    private fun teamBoardConstraintLayoutSetting(){
        // ConstraintLayout 부모 Layout

        val teamBoardLayout = findViewById<LinearLayout>(R.id.teamBoardLayout)

        val teamBoardNewWriteConstraintLayout = ConstraintLayout(this)

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        teamBoardNewWriteConstraintLayout.layoutParams = layoutParams // 레이아웃 파라미터 설정

        teamBoardLayout.addView(teamBoardNewWriteConstraintLayout) // ConstraintLayout을 부모 레이아웃에 추가



    }

    private fun setResultSignUp(){

        var userNameText = findViewById<EditText>(R.id.userNameText)
        var userPositionText = findViewById<EditText>(R.id.userPositionText)
        var userImage = findViewById<ImageView>(R.id.userImage)

        getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK){
                val username = result.data?.getStringExtra("userNameText") ?: ""
                val userposition = result.data?.getStringExtra("userPositionText")?:""
                val userimage = result.data?.getIntExtra("userImage", 0) ?: 0
                userNameText.setText(username)
                userPositionText.setText(userposition)
                userImage.setImageResource(userimage)
            }
        }

    }

    private fun btnClickSet(){

        // personAdd

        // 게시판 등록하기
        val boardAddBtn = findViewById<ImageButton>(R.id.boardAddBtn)
        boardAddBtn.setOnClickListener{
            val intent = Intent(this, WritePageActivity::class.java)
            startActivity(intent)
        }


        // 팀 게시판
        val teamBoardFrame1 = findViewById<ConstraintLayout>(R.id.teamBoardFrame1)
        val teamBoardDetailFrame1 = findViewById<ConstraintLayout>(R.id.teamBoardDetailFrame1)
        teamBoardFrame1.setOnClickListener{
            if(teamBoardDetailFrame1.visibility == View.VISIBLE) {
                teamBoardDetailFrame1.visibility = View.GONE

            } else {
                teamBoardDetailFrame1.visibility = View.VISIBLE

            }
        }


        //더 보기 기능
        val teamBoardDetailViewMore1 = findViewById<TextView>(R.id.teamBoardDetailViewMore1)
        teamBoardDetailViewMore1.setOnClickListener{
            val intent = Intent(this, DetailPage::class.java)
            intent.putExtra("userName", "정나미")
            intent.putExtra("userImage", R.drawable.mypage_dummy_image)
            intent.putExtra("detailSubject", "제목" )
            intent.putExtra("detailContent", "내용")
            startActivity(intent)
        }
    }
}
