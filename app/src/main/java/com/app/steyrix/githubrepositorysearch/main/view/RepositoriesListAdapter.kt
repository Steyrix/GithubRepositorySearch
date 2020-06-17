package com.app.steyrix.githubrepositorysearch.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.steyrix.githubrepositorysearch.R
import com.app.steyrix.githubrepositorysearch.main.MainActivity
import com.app.steyrix.githubrepositorysearch.main.data.response.RepositoryInfo
import com.app.steyrix.githubrepositorysearch.main.data.response.ResponseUtils

class RepositoriesListAdapter(private var repositories: MutableList<RepositoryInfo>,
                              private val itemOnClickListener: MainActivity.ItemClickListener
) : RecyclerView.Adapter<RepositoriesListAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_list_item, parent, false)

        return RepositoryViewHolder(itemView, itemOnClickListener)
    }

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.setData(repositories[position])
    }

    fun setData(list: MutableList<RepositoryInfo>) {
        repositories = list
        notifyDataSetChanged()
    }

    class RepositoryViewHolder(itemView: View,
                               private val listener: MainActivity.ItemClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val repositoryNameView: TextView = itemView.findViewById(R.id.repository_name)
        private val repositoryLangView: TextView = itemView.findViewById(R.id.repository_lang)
        private var ownerInfo = ResponseUtils.notFoundUserInfo

        init {
            itemView.setOnClickListener(this)
        }

        fun setData(info: RepositoryInfo) {
            repositoryNameView.text = info.name
            repositoryLangView.text = info.language
            ownerInfo = info.owner
        }

        override fun onClick(v: View?) {
            listener.setBundle(ownerInfo)
            listener.onClick(v)
        }
    }
}