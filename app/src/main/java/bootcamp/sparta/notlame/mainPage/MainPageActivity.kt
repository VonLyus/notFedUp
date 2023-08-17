package bootcamp.sparta.notlame.mainPage

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
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

        // 임시로 수정 불가로 설정
        var userNameText = findViewById<EditText>(R.id.userNameText)
        userNameText.isEnabled = false
        var userPositionText = findViewById<EditText>(R.id.userPositionText)
        userPositionText.isEnabled = false


        teamBoardSetting()
        //setResultSignUp()
        btnClickSet()

    }


    // 팀 게시판 세팅
    private fun teamBoardSetting(){

        val checkValue = intent.getIntExtra("check", 0)

        if(checkValue == 1){
            val parentLayout = findViewById<LinearLayout>(R.id.teamBoardLayout)

            val inflater = LayoutInflater.from(this)

            // 동적 생성할 layout을 선언
            val teamLayout = inflater.inflate(R.layout.team_board_frame, null)
            parentLayout.addView(teamLayout)
            val titleEditText = teamLayout.findViewById<EditText>(R.id.teamBoardTitle1)
            val title = intent.getStringExtra("Title") ?: "동적 혐오가 생긴거 같아요"
            titleEditText.setText(title)
            //입력 불가능 상태
            titleEditText.isEnabled = false



            // 동적 생성할 layout을 선언
            val commentLayout = inflater.inflate(R.layout.team_board_comment_frame, null)
            parentLayout.addView(commentLayout)
            val commentEditText = commentLayout.findViewById<EditText>(R.id.teamBoardComment1)
            val comment = intent.getStringExtra("Comment") ?: "정신을 놓았습니다"
            commentEditText.setText(comment)
            commentEditText.isEnabled = false


            //teamLayout
            val teamBoardFrame1 = teamLayout.findViewById<ConstraintLayout>(R.id.teamBoardFrame1)
            val teamBoardDetailFrame1 = commentLayout.findViewById<ConstraintLayout>(R.id.teamBoardDetailFrame1)
            teamBoardFrame1.setOnClickListener{

                if(teamBoardDetailFrame1.visibility == View.VISIBLE) {
                    teamBoardDetailFrame1.visibility = View.GONE
                } else {
                    teamBoardDetailFrame1.visibility = View.VISIBLE

                }
            }
            //더 보기 기능
            val teamBoardDetailViewMore1 = commentLayout.findViewById<TextView>(R.id.teamBoardDetailViewMore1)
            teamBoardDetailViewMore1.setOnClickListener{

                val intent = Intent(this, DetailPage::class.java)
                intent.putExtra("detailSubject",title)
                intent.putExtra("detailContent",comment)
                startActivity(intent)

            }

        }

    }

    //값을 수정해야함      이름 직책 이미지 받아오기
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


        }


}
