package ptc.tech.androidLiveCodingExam1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ptc.tech.androidLiveCodingExam1.data.ContactEntity

class HomeViewModel : ViewModel() {

    private val _contacts = MutableLiveData<Array<ContactEntity>>().apply {
        value = arrayOf<ContactEntity>()
    }
    val contacts: LiveData<Array<ContactEntity>> = _contacts

    fun fetchContacts() {
        println("FETCH CONTACTS NOW")
        _contacts.value = arrayOf(
            ContactEntity(),
            ContactEntity(),
            ContactEntity()
        )
    }
}