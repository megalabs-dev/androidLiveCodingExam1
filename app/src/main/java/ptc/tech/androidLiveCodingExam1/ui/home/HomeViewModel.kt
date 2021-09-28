package ptc.tech.androidLiveCodingExam1.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONTokener
import ptc.tech.androidLiveCodingExam1.data.ContactEntity
import java.io.IOException

class HomeViewModel : ViewModel() {

    private val _contacts = MutableLiveData<Array<ContactEntity>>().apply {
        value = arrayOf<ContactEntity>()
    }
    val contacts: LiveData<Array<ContactEntity>> = _contacts

    fun fetchContacts(context: Context) {
        val json = getJsonDataFromAsset(context)

        val jsonArray = JSONTokener(json).nextValue() as JSONArray
        val contactList: MutableList<ContactEntity> = ArrayList()
        for (i in 0 until jsonArray.length()) {
            val contact = ContactEntity(jsonArray.getJSONObject(i))
            contactList.add(contact)
        }
        _contacts.value = contactList.toTypedArray()
    }

    private fun getJsonDataFromAsset(context: Context): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open("contacts.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}