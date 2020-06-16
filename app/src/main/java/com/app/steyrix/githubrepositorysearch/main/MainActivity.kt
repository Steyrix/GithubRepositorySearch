package com.app.steyrix.githubrepositorysearch.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.steyrix.githubrepositorysearch.R
import com.app.steyrix.githubrepositorysearch.core.GithubRepositorySearchApp
import com.app.steyrix.githubrepositorysearch.main.data.model.RepositoryInfo
import com.app.steyrix.githubrepositorysearch.main.view.MainViewModel
import com.app.steyrix.githubrepositorysearch.main.view.RepositoriesListAdapter
import com.app.steyrix.githubrepositorysearch.main.view.afterTextChanged
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (applicationContext as GithubRepositorySearchApp).appComponent.inject(this)

        val searchFieldEditText: EditText = findViewById(R.id.search_request_field)
        val repositoriesListView: RecyclerView = findViewById((R.id.repository_list))

        val repositoriesListAdapter = RepositoriesListAdapter(mutableListOf())
        repositoriesListView.layoutManager = LinearLayoutManager(this)
        repositoriesListView.adapter = repositoriesListAdapter

        val repositoriesObserver = Observer<MutableList<RepositoryInfo>> { list ->
            repositoriesListAdapter.setData(list)
        }

        viewModel.getRepositories().observe(this, repositoriesObserver)

        searchFieldEditText.afterTextChanged { viewModel.fetchRepositories(it) }
    }
}
