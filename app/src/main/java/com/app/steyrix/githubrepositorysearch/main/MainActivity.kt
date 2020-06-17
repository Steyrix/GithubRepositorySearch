package com.app.steyrix.githubrepositorysearch.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.steyrix.githubrepositorysearch.R
import com.app.steyrix.githubrepositorysearch.core.GithubRepositorySearchApp
import com.app.steyrix.githubrepositorysearch.main.data.response.RepositoryInfo
import com.app.steyrix.githubrepositorysearch.main.data.response.ResponseUtils
import com.app.steyrix.githubrepositorysearch.main.data.response.UserInfo
import com.app.steyrix.githubrepositorysearch.main.view.*
import javax.inject.Inject
import android.view.inputmethod.InputMethodManager

class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (applicationContext as GithubRepositorySearchApp).appComponent.inject(this)

        val searchFieldEditText: EditText = findViewById(R.id.search_request_field)
        val repositoriesListView: RecyclerView = findViewById((R.id.repository_list))

        val repositoriesListAdapter = RepositoriesListAdapter(
            mutableListOf(), ItemClickListener(this))

        repositoriesListView.layoutManager = LinearLayoutManager(this)
        repositoriesListView.adapter = repositoriesListAdapter

        searchFieldEditText.setOnClickListener {
            deleteFragmentIfNeeded()
        }

        val repositoriesObserver = Observer<MutableList<RepositoryInfo>> { list ->
            repositoriesListAdapter.setData(list)
        }

        viewModel.getRepositories().observe(this, repositoriesObserver)

        searchFieldEditText.onTextChanged { viewModel.fetchRepositories(it) }
    }

    override fun onBackPressed() {
        if (deleteFragmentIfNeeded()) return
        super.onBackPressed()
    }

    private fun deleteFragmentIfNeeded() : Boolean {
        val view: FrameLayout = findViewById(R.id.user_info_container)

        if (view.visibility != View.GONE) {
            supportFragmentManager.findFragmentById(R.id.user_info_container)?.let {
                supportFragmentManager.beginTransaction().remove(it).commit()
            }
            view.visibility = View.GONE
            return true
        }

        return false
    }

    class ItemClickListener(private val context: MainActivity): View.OnClickListener {

        private var bundle = Bundle().apply {
            putString(BundleKeys.USER_NAME, ResponseUtils.notFoundUserInfo.login)
            putString(BundleKeys.USER_DESC, ResponseUtils.notFoundUserInfo.bio)
            putString(BundleKeys.AVATAR_URL, ResponseUtils.notFoundUserInfo.avatarUrl)
        }

        fun setBundle(info: UserInfo) {
            bundle.apply {
                putString(BundleKeys.USER_NAME, info.login)
                putString(BundleKeys.USER_DESC, info.bio)
                putString(BundleKeys.AVATAR_URL, info.avatarUrl)
            }
        }

        override fun onClick(v: View?) {
            val focused = context.currentFocus
            if (focused != null) {
                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(focused.windowToken, 0)
            }

            val fragmentTransaction = context.supportFragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(R.anim.abc_popup_enter, R.anim.abc_popup_exit)

            val fragment = UserInfoFragment.newInstance().apply {
                arguments = bundle
            }

            fragmentTransaction.replace(R.id.user_info_container, fragment)

            val view: FrameLayout = context.findViewById(R.id.user_info_container)
            view.visibility = View.VISIBLE

            fragmentTransaction.commit()
        }

    }
}
