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
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import bootcamp.sparta.notlame.R
import bootcamp.sparta.notlame.detailPage.DetailPage
import bootcamp.sparta.notlame.myPage.MyPageActivity
import bootcamp.sparta.notlame.writePage.WritePageActivity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

class MainPageActivity : AppCompatActivity() {

//    private lateinit var getContent: ActivityResultLauncher<Intent>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)



        userInfoSetting()
        teamBoardSetting()
        myPageSet()


    }

    private fun userInfoSetting(){
        // 임시로 수정 불가로 설정

        val checkName = intent.getStringExtra("userName") ?: "name"
        val checkId = intent.getStringExtra("userId") ?: "id"
        val checkPw = intent.getStringExtra("userPw") ?: "pw"
        val checkTel = intent.getStringExtra("userTel") ?: "tel"
        val checkPosition = intent.getStringExtra("userPosition") ?: "position"
        val checkImage = intent.getIntExtra("userImage", 0)

        var userImage = findViewById<ImageView>(R.id.userImage)
        userImage.setImageResource(checkImage)
        userImage.setOnClickListener{
            val intent = Intent(this, MyPageActivity::class.java)

            intent.putExtra("userName", checkName)
            intent.putExtra("userId", checkId)
            intent.putExtra("userPw", checkPw)
            intent.putExtra("userTel", checkTel)
            intent.putExtra("userPosition", checkPosition)
            intent.putExtra("userImage", checkImage)

            startActivity(intent) }


        var userNameText = findViewById<TextView>(R.id.userNameText)
        userNameText.text=checkName

        var userPositionText = findViewById<TextView>(R.id.userPositionText)
        userPositionText.text=checkPosition

        var userImageList1 = findViewById<ImageView>(R.id.userImageList1)
        userImageList1.setImageResource(checkImage)
        userImageList1.setOnClickListener{
            val intent = Intent(this, MyPageActivity::class.java)

            intent.putExtra("userName", checkName)
            intent.putExtra("userId", checkId)
            intent.putExtra("userPw", checkPw)
            intent.putExtra("userTel", checkTel)
            intent.putExtra("userPosition", checkPosition)
            intent.putExtra("userImage", checkImage)

            startActivity(intent) }

        var userImageList2 = findViewById<ImageView>(R.id.userImageList2)
        userImageList2.setImageResource(R.drawable.profile_set1)

        var userImageList3 = findViewById<ImageView>(R.id.userImageList3)
        userImageList3.setImageResource(R.drawable.profile_set2)


        var userImageList4 = findViewById<ImageView>(R.id.userImageList4)
        userImageList4.setImageResource(R.drawable.profile_set3)


        var userImageList5 = findViewById<ImageView>(R.id.userImageList5)
        userImageList5.setImageResource(R.drawable.profile_set4)



    }

    // 팀 게시판 세팅
    private fun teamBoardSetting(){

        //게시판 값 받아오기
        //빈 리스트 생성
        val title : MutableList<String> = mutableListOf()  //: String? = null
        var comment: MutableList<String> = mutableListOf() //: String? = null

        var checkValue: Int? = null


        var index = 0

        val startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                if (result.resultCode == Activity.RESULT_OK) {



                    //결과에 대해 받아오게 되면 실행되는 거기 때문에 teamboard를 여기에 생성해야 한다
                    //값을 받아 온다      ->registerForActivityResult는 여기서만 받아오기 때문에
                    //받아 온 값은 여기서만 초기화 해야한다

                    val checkImage = intent.getIntExtra("userImage", 0)
                    val checkName = intent.getStringExtra("userName") ?: "name"

                    val data: Intent? = result.data

                    title.add(data?.getStringExtra("Title").toString())
                    comment.add(data?.getStringExtra("Comment").toString())

                    checkValue = data?.getIntExtra("check",0)


                    if(checkValue == 1){
                        val parentLayout = findViewById<LinearLayout>(R.id.teamBoardLayout)


                        val inflater = LayoutInflater.from(this)

                        // 동적 생성할 layout을 선언
                        val teamLayout = inflater.inflate(R.layout.team_board_frame, null)
                        parentLayout.addView(teamLayout)
                        val titleEditText = teamLayout.findViewById<TextView>(R.id.teamBoardTitle1)
                        titleEditText.text=title[index]



                        // 동적 생성할 layout을 선언
                        val commentLayout = inflater.inflate(R.layout.team_board_comment_frame, null)
                        //동적으로 id 생성하기      -> 1부터 값이 생성됨
                        //commentLayout.id = View.generateViewId()

                        parentLayout.addView(commentLayout)
                        val commentEditText = commentLayout.findViewById<TextView>(R.id.teamBoardComment1)
                        commentEditText.text=comment[index]



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


                        val userDateText= teamLayout.findViewById<TextView>(R.id.teamBoardUserDate1)
                        userDateText.text = getCurrentDate()
                        val userNameText= teamLayout.findViewById<TextView>(R.id.teamBoardUserName1)
                        userNameText.text = checkName

                        //DetailPage
                        //동적 생성에 대한 아이디를 받아야 한다.        --> 결국 list해서 값을 저장해야 한다.
                        //더 보기 기능
                        //index 판단을 해야함

                        val teamBoardDetailViewMore = commentLayout.findViewById<TextView>(R.id.teamBoardDetailViewMore1)
                        teamBoardDetailViewMore.id = index


                        teamBoardDetailViewMore.setOnClickListener{

                            val intent = Intent(this, DetailPage::class.java)

                            intent.putExtra("userName", checkName)

                            intent.putExtra("userImage", checkImage)

                            intent.putExtra("detailSubject",title[teamBoardDetailViewMore.id])
                            intent.putExtra("detailContent",comment[teamBoardDetailViewMore.id])

                            startActivity(intent)

                        }

                    }
                    ///
                    index++
                }
            }




        // 게시판 등록하기
        val boardAddBtn = findViewById<ImageButton>(R.id.boardAddBtn)
        boardAddBtn.setOnClickListener{
            val intent = Intent(this, WritePageActivity::class.java)

            startForResult.launch(intent) }

    }

    //날짜 갖고오기
    private fun getCurrentDate(): String {
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(currentDate)
    }

    private fun myPageSet(){

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

        }


}