package bootcamp.sparta.notlame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textSubject = "제목1 제목1 제목1"
        val textContent = "여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다." +
                "여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다." +
                "여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다." +
                "여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.\" +\n" +
                "                \"여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.\" +\n" +
                "                \"여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.\" +\n" +
                "                \"여기에 내용이 입력됩니다.여기에 내용이 입력됩니다.여기에 내용이 입력됩니다."

        val detailPage = findViewById<Button>(R.id.detailPage)
        detailPage.setOnClickListener {
            val intent = Intent(this, DetailPage::class.java)
            intent.putExtra("detailSubject", textSubject)
            intent.putExtra("detailContent", textContent)
            startActivity(intent)
        }

    }
}
