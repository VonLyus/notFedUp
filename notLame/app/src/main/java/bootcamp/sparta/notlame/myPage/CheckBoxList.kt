package bootcamp.sparta.notlame.myPage

import android.view.View
import android.widget.CheckBox
import bootcamp.sparta.notlame.R

fun getCheckBoxList(view: View) : ArrayList<CheckBox> {
    return arrayListOf(
        view.findViewById(R.id.checkbox_01),
        view.findViewById(R.id.checkbox_02),
        view.findViewById(R.id.checkbox_03),
        view.findViewById(R.id.checkbox_04),
        view.findViewById(R.id.checkbox_05),
        view.findViewById(R.id.checkbox_06),
        view.findViewById(R.id.checkbox_07),
        view.findViewById(R.id.checkbox_08),
        view.findViewById(R.id.checkbox_09),
        view.findViewById(R.id.checkbox_10),
    )
}