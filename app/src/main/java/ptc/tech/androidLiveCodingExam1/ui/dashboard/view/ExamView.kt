package ptc.tech.androidLiveCodingExam1.ui.dashboard.view


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.material.composethemeadapter.MdcTheme
import ptc.tech.androidLiveCodingExam1.data.ContactEntity

@Composable
fun ExamView(
    contacts: List<ContactEntity>
) {
    Text("Exam here")
}

@Preview()
@Composable
fun DefaultPreview() {
    MdcTheme {
        ExamView(List(4) { ContactEntity() })
    }
}