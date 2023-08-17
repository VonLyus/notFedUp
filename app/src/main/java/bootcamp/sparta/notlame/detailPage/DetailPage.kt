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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        val userName = intent.getStringExtra("userName")
        val userImage = intent.getIntExtra("userImage", R.drawable.mypage_dummy_image)
        val detailSubject = findViewById<TextView>(R.id.detailSubject)
        val detailContent = findViewById<TextView>(R.id.detailContent)
        val detailName = findViewById<TextView>(R.id.detailName)
        val detailImage = findViewById<ImageView>(R.id.detailImage)
        detailSubject.setText(intent.getStringExtra("detailSubject"))
        detailContent.setText(intent.getStringExtra("detailContent"))
        detailName.setText(userName)
        detailImage.setImageResource(userImage)
        val backBtn = findViewById<ImageButton>(R.id.imageButton)

        backBtn.setOnClickListener {
            var snackBar = Snackbar.make(it, R.string.warningBack, Snackbar.LENGTH_SHORT).setAction(R.string.button_done, View.OnClickListener {
                finish()
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            })
            snackBar.show()
        }

        detailInputBtn.setOnClickListener {
            val text = detailInputEdit.text.toString()
            if(text.isNullOrBlank()){
                Toast.makeText(this, R.string.warningNull, Toast.LENGTH_SHORT).show()
            }else{
                if (userName != null) {
                    inputText(text, userName)
                }
                detailInputEdit.setText(/* text = */ null)
            }
        }
    }

    fun inputText(text:String, userName:String){
        val newText = TextView(this)
        newText.setText(userName + " : " + text)
        detailInputArea.addView(newText)
    }
}