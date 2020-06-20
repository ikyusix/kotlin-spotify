package com.enigma.newspotify.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import com.enigma.newspotify.R
import com.enigma.newspotify.model.Artist
import com.squareup.picasso.Picasso

class ArtistArrayAdapter(@NonNull context: Context, @LayoutRes layoutRes: Int=0, var artistList: MutableList<Artist>): ArrayAdapter<Artist>(context, layoutRes, artistList) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false) // inject ke layout // ngambil view yg mn
        val artist = artistList.get(position)
        itemView?.findViewById<TextView>(R.id.artist_name)?.setText(artist.Name)
        itemView?.findViewById<TextView>(R.id.artist_debut)?.setText(artist.Debut)
        val imageView = itemView?.findViewById<ImageView>(R.id.imageView)
        Picasso.get().load(artist.ImgUrl).placeholder(R.drawable.ic_account_circle_black_24dp).into(imageView)
        // versi baru Picasso.get().load
        return itemView
    }
}