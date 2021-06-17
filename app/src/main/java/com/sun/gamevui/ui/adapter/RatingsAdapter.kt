package com.sun.gamevui.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sun.gamevui.R
import com.sun.gamevui.base.BaseAdapter
import com.sun.gamevui.base.BaseViewHolder
import com.sun.gamevui.data.model.Rating
import com.sun.gamevui.databinding.ItemRatingBinding

class RatingsAdapter() : BaseAdapter<Rating, RatingsAdapter.RatingsViewHolder>(Rating.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RatingsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_rating,
                parent,
                false
            )
        )

    class RatingsViewHolder(
        private val itemRatingBinding: ItemRatingBinding
    ) : BaseViewHolder<Rating>(itemRatingBinding, {}) {

        override fun bindData(item: Rating) {
            super.bindData(item)
            itemRatingBinding.rating = item
            when(item.title){
                "exceptional" -> itemRatingBinding.imageCircle.setImageResource(R.drawable.ic_circle_green)
                "recommended" -> itemRatingBinding.imageCircle.setImageResource(R.drawable.ic_circle_blue)
                "meh" -> itemRatingBinding.imageCircle.setImageResource(R.drawable.ic_circle_yellow)
                "skip" -> itemRatingBinding.imageCircle.setImageResource(R.drawable.ic_circle_red)
            }
        }
    }
}
