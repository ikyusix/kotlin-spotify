package com.enigma.newspotify

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.enigma.newspotify.adapter.ArtistArrayAdapter
import com.enigma.newspotify.model.Artist
import org.json.JSONArray
import org.json.JSONException

class ListActivityS: AppCompatActivity() {
    lateinit var listView: ListView
    //lateinit var arrayAdapter: ArrayAdapter<String>
    lateinit var arrayAdapter: ArtistArrayAdapter
    val url = "https://10.10.17.165:8081/artist"
    lateinit var requestQue: RequestQueue
    //var listArtist = mutableListOf<String>()
    var listArtist = mutableListOf<Artist>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.third_layout)
        listView = findViewById(R.id.artisList)
        //arrayAdapter = ArrayAdapter<String>(this, R.layout.item_get_name) // untuk mengambil data satuan menjadi array
        arrayAdapter = ArtistArrayAdapter(
            context = this,
            artistList = listArtist
        )
        requestQue = Volley.newRequestQueue(this) // buat request antrian utk aktivitas ini aja (this)
        listView.adapter = arrayAdapter
        fetchAll()
    }

    fun fetchAll(){ // ini fetching terjadi saat on create
        // baru ke sini
        val artistRequest = StringRequest(Request.Method.GET,
            url,
            Response.Listener { response ->
                resolveSuccess(response)
            },
            Response.ErrorListener { error: VolleyError? ->
                Log.e("FETCH: FAIL: ", error?.message)
            } )

        requestQue.add(artistRequest) // yg dijalanin ini dulu
    }

    private fun resolveSuccess(response: String?) {
        try {
            val arrayResponse = JSONArray(response) // krn banyak json, jd pk json array, gbs json object
            for (i in 0 until arrayResponse.length()) {
                //val item =
                //   arrayResponse.getJSONObject(i).getString("name") // ngambil json object(satuan), hanya namanya
                //listArtist.add(item) // lalu di tambahkan ke list
                val item = arrayResponse.getJSONObject(i)
                val artist = Artist(
                    item.getString("Name"),
                    item.getString("Debut"),
                    item.getString("Category"),
                    item.getString("ImgUrl")
                )
                Log.i("ARTIST $i", artist.toString())
                //listArtist.add(artist)
                arrayAdapter.add(artist)
            }
//            arrayAdapter.clear()
//            arrayAdapter.addAll(listArtist)
//            arrayAdapter.notifyDataSetChanged()
        } catch (jsonEx: JSONException) {
            Log.e("PARSE: FAIL:", jsonEx.message)
        }
    }
}