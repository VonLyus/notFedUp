package bootcamp.sparta.notlame.Util

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import bootcamp.sparta.notlame.R

// fadein and out 애니메이션
fun animSlideRight(context: Context) = (context as AppCompatActivity).overridePendingTransition(R.anim.slide_from_right, R.anim.static_animation)
fun animSlideLeft(context: Context) = (context as AppCompatActivity).overridePendingTransition(R.anim.slide_from_left, R.anim.static_animation)
