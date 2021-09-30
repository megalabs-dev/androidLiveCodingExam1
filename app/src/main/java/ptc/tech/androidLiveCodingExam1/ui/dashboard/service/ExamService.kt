package ptc.tech.androidLiveCodingExam1.ui.dashboard.service

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import org.json.JSONTokener
import ptc.tech.androidLiveCodingExam1.data.ContactEntity

class ExamService {
    suspend fun fetchContacts(): Array<ContactEntity> {
        val url = URL("https://randomuser.me/api/?results=5")
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        val json = response.body?.string()

        val jsonObject = JSONTokener(json).nextValue() as JSONObject
        val jsonArray = jsonObject.getJSONArray("results")
        val contactList: MutableList<ContactEntity> = ArrayList()
        for (i in 0 until jsonArray.length()) {
            val contact = ContactEntity(jsonArray.getJSONObject(i))
            contactList.add(contact)
        }
        return contactList.toTypedArray()
    }
}