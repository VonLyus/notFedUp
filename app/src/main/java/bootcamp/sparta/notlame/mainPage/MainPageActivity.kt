package bootcamp.sparta.notlame.mainPage

import android.app.Activity
import android.content.Intent
import android.media.Image
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
import bootcamp.sparta.notlame.myPage.MyPageActivity
import bootcamp.sparta.notlame.writePage.WritePageActivity

class MainPageActivity : AppCompatActivity() {
    private lateinit var getContent: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)


        userInfoSetting()
        teamBoardSetting()
        btnClickSet()



    }

    private fun userInfoSetting(){
        // 임시로 수정 불가로 설정

        val checkName = intent.getStringExtra("userName") ?: "name"
        val checkId = intent.getStringExtra("userId") ?: "id"
        val checkPw = intent.getStringExtra("userPw") ?: "pw"
        val checkTel = intent.getStringExtra("userTel") ?: "tel"
        val checkPosition = intent.getStringExtra("userPosition") ?: "position"
        val checkImage = intent.getIntExtra("userImage", 0)


        var userImageList1 = findViewById<ImageButton>(R.id.userImageList1)
        userImageList1.setImageResource(checkImage)

        var userNameText = findViewById<EditText>(R.id.userNameText)
        userNameText.setText(checkName)
        userNameText.isEnabled = false

        var userPositionText = findViewById<EditText>(R.id.userPositionText)
        userPositionText.setText(checkPosition)
        userPositionText.isEnabled = false



    }

    // 팀 게시판 세팅
    private fun teamBoardSetting(){

        val checkName = intent.getStringExtra("userName") ?: "name"
        val checkId = intent.getStringExtra("userId") ?: "id"
        val checkPw = intent.getStringExtra("userPw") ?: "pw"
        val checkTel = intent.getStringExtra("userTel") ?: "tel"
        val checkPosition = intent.getStringExtra("userPosition") ?: "position"
        val checkImage = intent.getIntExtra("userImage", 0)
        val checkValue = intent.getIntExtra("check", 0)

        //이름이랑 데이터 갖고 와야함
        //
        //teamBoardUserName1
        //teamBoardUserDate1




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

                intent.putExtra("userName", checkName)
                intent.putExtra("userId", checkId)
                intent.putExtra("userPw", checkPw)
                intent.putExtra("userTel", checkTel)
                intent.putExtra("userPosition", checkPosition)
                intent.putExtra("userImage", checkImage)

                intent.putExtra("detailSubject",title)
                intent.putExtra("detailContent",comment)
                startActivity(intent)

            }

        }

    }



    private fun btnClickSet(){

        val checkName = intent.getStringExtra("userName") ?: "name"
        val checkId = intent.getStringExtra("userId") ?: "id"
        val checkPw = intent.getStringExtra("userPw") ?: "pw"
        val checkTel = intent.getStringExtra("userTel") ?: "tel"
        val checkPosition = intent.getStringExtra("userPosition") ?: "position"
        val checkImage = intent.getIntExtra("userImage", 0)


        val personInfoBtn = findViewById<ImageButton>(R.id.personInfoBtn)
        personInfoBtn.setOnClickListener{
            val intent = Intent(this, MyPageActivity::class.java)

            intent.putExtra("userName", checkName)
            intent.putExtra("userId", checkId)
            intent.putExtra("userPw", checkPw)
            intent.putExtra("userTel", checkTel)
            intent.putExtra("userPosition", checkPosition)
            intent.putExtra("userImage", checkImage)

            startActivity(intent) }

        // 게시판 등록하기
        val boardAddBtn = findViewById<ImageButton>(R.id.boardAddBtn)
        boardAddBtn.setOnClickListener{
            val intent = Intent(this, WritePageActivity::class.java)
            intent.putExtra("userName", checkName)
            intent.putExtra("userId", checkId)
            intent.putExtra("userPw", checkPw)
            intent.putExtra("userTel", checkTel)
            intent.putExtra("userPosition", checkPosition)
            intent.putExtra("userImage", checkImage)
            startActivity(intent) }
        }


}
