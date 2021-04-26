package br.com.alessandro.marvelchallenge.feature.personage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alessandro.marvelchallenge.R
import br.com.alessandro.marvelchallenge.data.model.Character
import br.com.alessandro.marvelchallenge.util.Constants.PORTRAIT_FANTASTIC
import com.squareup.picasso.Picasso

class PersonagesCarouselAdapter(
    private val listPersonages: MutableList<Character>,
    private val picasso: Picasso
):RecyclerView.Adapter<PersonagesCarouselAdapter.ListPersonagesCarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPersonagesCarouselViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.personage_carousel_item, parent, false)
        return ListPersonagesCarouselViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPersonages.size
    }

    override fun onBindViewHolder(holder: ListPersonagesCarouselViewHolder, position: Int) {
        holder.bind(listPersonages[position], picasso)
    }

    inner class ListPersonagesCarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.personage_item_image)
        private val name: TextView = itemView.findViewById(R.id.personage_item_name)

        fun bind(
            personage: Character,
            picasso: Picasso
        ) {
            name.text = personage.name

            picasso
                .load("${personage.thumbnail.path}/$PORTRAIT_FANTASTIC.${personage.thumbnail.extension}")
                .centerCrop()
                .fit()
                .into(image)
        }
    }
}
