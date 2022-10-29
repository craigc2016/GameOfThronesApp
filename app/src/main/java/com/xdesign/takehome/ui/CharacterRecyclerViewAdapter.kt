package com.xdesign.takehome.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.xdesign.takehome.R
import com.xdesign.takehome.common.createSpannableString
import com.xdesign.takehome.common.setTextColour
import com.xdesign.takehome.databinding.FragmentCharacterBinding
import com.xdesign.takehome.models.ApiCharacter

class CharacterRecyclerViewAdapter(
    private var values: List<ApiCharacter>
) : RecyclerView.Adapter<CharacterRecyclerViewAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {

        return CharacterViewHolder(
            FragmentCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(values[position])

    override fun getItemCount(): Int = values.size

    fun updateList(values: List<ApiCharacter>) {
        this.values = values
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(private val binding: FragmentCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: ApiCharacter) {
            binding.apply {
                name.text = character.name
                val cultureTitle = itemView.context.getString(R.string.culture_title)
                val cultureText = character.culture.setTextColour(ContextCompat.getColor(itemView.context, R.color.lavender_grey))
                cultureTV.text = cultureTitle.createSpannableString(cultureText)

                val bornTitle = itemView.context.getString(R.string.born_title)
                val bornText = character.born.setTextColour(ContextCompat.getColor(itemView.context, R.color.lavender_grey))
                bornTV.text = bornTitle.createSpannableString(bornText)

                val diedTitle = itemView.context.getString(R.string.died_title)
                val diedText = character.died.setTextColour(ContextCompat.getColor(itemView.context, R.color.lavender_grey))
                diedTV.text = diedTitle.createSpannableString(diedText)

                val formattedString = if (character.tvSeries.size == 6) {
                    "I - III, V-VII"
                } else {
                    "I - III"
                }

                val seasonsTitle = itemView.context.getString(R.string.seasons_title)
                val seasonsText = formattedString.setTextColour(ContextCompat.getColor(itemView.context, R.color.lavender_grey))
                seasonsTV.text = seasonsTitle.createSpannableString(seasonsText, spaceBetween = false)
            }
        }
    }
}

