package com.xdesign.takehome.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.xdesign.takehome.R
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
                culture.text = ""
                born.text = ""
                died.text = ""

                name.text = character.name
                culture.append(itemView.context.getString(R.string.culture_title))
                culture.append(character.culture.setTextColour(ContextCompat.getColor(itemView.context, R.color.lavender_grey)))
                born.append(itemView.context.getString(R.string.born_title))
                born.append(character.born.setTextColour(ContextCompat.getColor(itemView.context, R.color.lavender_grey)))
                died.append(itemView.context.getString(R.string.died_title))
                died.append(character.died.setTextColour(ContextCompat.getColor(itemView.context, R.color.lavender_grey)))

                val formattedString = character.tvSeries.joinToString {
                    when (it) {
                        "Season 1" -> "I"
                        "Season 2" -> "II"
                        "Season 3" -> "III"
                        "Season 4" -> "IV"
                        "Season 5" -> "V"
                        "Season 6" -> "VI"
                        "Season 7" -> "VII"
                        "Season 8" -> "VIII"
                        else -> ""
                    }
                }
                seasons.text = String.format(
                    itemView.context.getString(R.string.seasons_title),
                    formattedString
                )
            }
        }
    }
}

