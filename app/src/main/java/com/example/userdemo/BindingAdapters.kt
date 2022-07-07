package com.example.userdemo
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.userdemo.network.User
import com.example.userdemo.ui.UserListAdapter
import de.hdodenhof.circleimageview.CircleImageView

class BindingAdapters{
    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun bindImage(imgView: CircleImageView, imgUrl: String?) {
            imgUrl?.let {
                val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
                imgView.load(imgUri) {
                }
            }
        }

        @BindingAdapter("listData")
        @JvmStatic
        fun bindRecyclerView(
            recyclerView: RecyclerView,
            data: List<User>?) {
            val adapter = recyclerView.adapter as UserListAdapter
            adapter.submitList(data)
        }
    }
}