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
import br.com.alessandro.marvelchallenge.util.getDescription
import com.squareup.picasso.Picasso

class PersonagesAdapter(
    private val listPersonages: MutableList<Character>,
    private val picasso: Picasso
):RecyclerView.Adapter<PersonagesAdapter.ListPersonagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPersonagesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.personage_list_item, parent, false)
        return ListPersonagesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPersonages.size
    }

    override fun onBindViewHolder(holder: ListPersonagesViewHolder, position: Int) {
        holder.bind(listPersonages[position], picasso)
    }

    fun updateItems(newItems: List<Character>) {
        listPersonages.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ListPersonagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.personage_list_item_image)
        private val name: TextView = itemView.findViewById(R.id.personage_list_item_name)
        private val description: TextView = itemView.findViewById(R.id.personage_list_item_description)

        fun bind(
            personage: Character,
            picasso: Picasso
        ) {
            name.text = personage.name
           description.text = personage.description.getDescription()

            picasso
                .load("${personage.thumbnail.path}/$PORTRAIT_FANTASTIC.${personage.thumbnail.extension}")
                .centerCrop()
                .fit()
                .into(image)
        }
    }
}
