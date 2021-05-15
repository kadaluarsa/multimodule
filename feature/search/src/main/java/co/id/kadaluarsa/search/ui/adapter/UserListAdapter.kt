package co.id.kadaluarsa.search.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import co.id.kadaluarsa.core.AppExecutors
import co.id.kadaluarsa.core.domain.model.User
import co.id.kadaluarsa.search.R
import co.id.kadaluarsa.search.databinding.UserItemBinding
import co.id.kadaluarsa.search.ui.common.DataBoundListAdapter
import com.bumptech.glide.Glide

class UserListAdapter(
    appExecutors: AppExecutors,
    private val userClickCallback: ((User) -> Unit)
): DataBoundListAdapter<User,UserItemBinding>(
    appExecutors,object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.avatarUrl == newItem.avatarUrl
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.reposUrl == newItem.reposUrl
        }
    }
) {
    override fun createBinding(parent: ViewGroup): UserItemBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_item,
            parent,
            false
        )
    }

    override fun bind(binding: UserItemBinding, item: User) {
        Glide.with(binding.root.context).load(item.avatarUrl).into(
            binding.imageThumb
        )
        binding.root.setOnClickListener {
            userClickCallback.invoke(item)
        }
    }

}