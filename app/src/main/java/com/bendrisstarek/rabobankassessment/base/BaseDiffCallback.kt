package com.bendrisstarek.rabobankassessment.base;

import androidx.recyclerview.widget.DiffUtil
import com.bendrisstarek.rabobankassessment.util.CollectionUtils.size

/**
 * this class represents a custom diffCallback
 * @param <D>
</D> */
abstract class BaseDiffCallback<D>(oldList: List<D>, newList: List<D>) : DiffUtil.Callback() {
    var oldList: List<D?>
        protected set
    var newList: List<D?>
        protected set

    override fun getOldListSize(): Int {
        return size(oldList)
    }

    override fun getNewListSize(): Int {
        return size(newList)
    }


    abstract override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
    abstract override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean

    init {
        this.oldList = oldList
        this.newList = newList
    }
}