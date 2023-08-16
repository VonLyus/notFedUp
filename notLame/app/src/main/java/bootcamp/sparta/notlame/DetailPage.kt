package bootcamp.sparta.notlame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class DetailPage : AppCompatActivity() {
    lateinit var detailInputBtn: Button
    lateinit var detailInputArea: LinearLayout
    lateinit var detailInputEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        val detailSubject = findViewById<TextView>(R.id.detailSubject)
        val detailContent = findViewById<TextView>(R.id.detailContent)
        detailSubject.setText(intent.getStringExtra("detailSubject"))
        detailContent.setText(intent.getStringExtra("detailContent"))
        detailInputBtn = findViewById<Button>(R.id.detailInputBtn)
        detailInputArea = findViewById<LinearLayout>(R.id.detailInputArea)
        detailInputEdit = findViewById<EditText>(R.id.detailInputEdit)
        val backBtn = findViewById<ImageButton>(R.id.imageButton)

        backBtn.setOnClickListener {
            finish()
        }

        detailInputBtn.setOnClickListener {
            val text = detailInputEdit.text.toString()
            if(text.isNullOrBlank()){
                Toast.makeText(this, "입력된 값이 없습니다.", Toast.LENGTH_SHORT).show()
            }else{
                inputText(text)
                detailInputEdit.setText(/* text = */ null)
            }
        }
    }

    fun inputText(text:String){
        val newText = TextView(this)
        newText.setText(text)
        detailInputArea.addView(newText)
    }
}