package com.chugunov.testapp.presentation.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chugunov.testapp.databinding.UserCardBinding
import com.chugunov.testapp.domain.entity.User

class UsersAdapter: ListAdapter<User, UsersAdapter.UsersViewHolder>(DiffCallback) {
    inner class UsersViewHolder(private val binding: UserCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.tvUserName.text = user.name
            binding.tvUserAge.text = user.age
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding = UserCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    companion object  {
        private val DiffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.name == newItem.name && oldItem.age == newItem.age
            }

        }
    }


}