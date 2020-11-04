package org.demon.mvvm_jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 显示数据的界面控制器
 * @date : 2020/11/4
 */
class UserProfileFragment : Fragment() {

    private val viewModel: UserProfileViewModel by viewModels(
        factoryProducer = { SavedStateViewModelFactory(this)}
    )


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.user.observe(viewLifecycleOwner) {
            // update UI
        }
    }
}