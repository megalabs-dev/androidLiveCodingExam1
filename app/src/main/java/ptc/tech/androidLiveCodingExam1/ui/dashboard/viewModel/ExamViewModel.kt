package ptc.tech.androidLiveCodingExam1.ui.dashboard.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONTokener
import ptc.tech.androidLiveCodingExam1.data.ContactEntity
import ptc.tech.androidLiveCodingExam1.ui.dashboard.service.ExamService

class ExamViewModel : ViewModel() {
    private val examService = ExamService()
    private val _contacts = MutableLiveData<Array<ContactEntity>>().apply {
        value = arrayOf<ContactEntity>()
    }
    private val _loading = MutableLiveData<Boolean>().apply {
        value = false
    }
    val contacts: LiveData<Array<ContactEntity>> = _contacts

    fun getContacts(): Array<ContactEntity> {
        return _contacts.value!!
    }

    fun fetchContacts() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                _contacts.value = withContext(Dispatchers.IO) { examService.fetchContacts() }
            } catch (exception: Exception) {
                println("Error = $exception")
            }
        }
    }
}