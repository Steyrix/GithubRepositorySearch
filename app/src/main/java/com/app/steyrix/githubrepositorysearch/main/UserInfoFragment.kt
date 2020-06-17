package com.app.steyrix.githubrepositorysearch.main

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.app.steyrix.githubrepositorysearch.R
import com.app.steyrix.githubrepositorysearch.main.data.response.ResponseUtils
import com.app.steyrix.githubrepositorysearch.main.view.BundleKeys
import com.squareup.picasso.Picasso

class UserInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.user_info_fragment, container, false)

        val userNameView: TextView = view.findViewById(R.id.user_name)
        val userDescriptionView: TextView = view.findViewById(R.id.user_description)
        val userAvatarView: ImageView = view.findViewById(R.id.user_avatar)

        if (arguments != null) {
            userNameView.text = arguments?.getString(BundleKeys.USER_NAME) ?: ResponseUtils.notFoundUserInfo.login
            userDescriptionView.text = arguments?.getString(BundleKeys.USER_DESC) ?: ""
            Picasso.get().load(arguments?.getString(BundleKeys.AVATAR_URL)).into(userAvatarView)
        }

        view.setOnKeyListener { _, _, keyEvent ->
            if (keyEvent.keyCode == KeyEvent.KEYCODE_BACK) {
                activity?.onBackPressed()
            }
            false
        }

        return view
    }

    companion object {
        fun newInstance() = UserInfoFragment()
    }
}
