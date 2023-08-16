package bootcamp.sparta.notlame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        val detailSubject = findViewById<TextView>(R.id.detailSubject)
        val detailContent = findViewById<TextView>(R.id.detailContent)

        detailSubject.setText(intent.getStringExtra("detailSubject"))
        detailContent.setText(intent.getStringExtra("detailContent"))

    }
}