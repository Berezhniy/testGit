package ru.startandroid.testgit.presentation.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import ru.startandroid.testgit.R
import ru.startandroid.testgit.data.card_models.RepositoryDisplayModel
import ru.startandroid.testgit.databinding.ItemRepositoryBinding
import ru.startandroid.testgit.presentation.activities.main.MainListener
import ru.startandroid.testgit.presentation.fragments.repositories.RepositoryModelBinding

class PagingAdapter(diffUtil: DiffUtil.ItemCallback<RepositoryDisplayModel>) :
    MultiTypeDataBoundAdapter<RepositoryDisplayModel, ViewDataBinding>(diffUtil) {

    lateinit var listener: MainListener

    override fun getItemLayoutId(position: Int): Int {
        getItem(position)?.let {
            return R.layout.item_repository
        }
        return -1
    }

    override fun bindItem(
        holder: DataBoundViewHolder<ViewDataBinding>,
        position: Int,
        payloads: List<Any>
    ) {
        super.bindItem(holder, position, payloads)
        val item = getItem(position)
        if (holder.binding is ItemRepositoryBinding) {
            item?.let {
                holder.binding.bindingModel = RepositoryModelBinding(it, listener)
            }
        }
    }
}