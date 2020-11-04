package org.demon.hilt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @author : Demon
 * @version : 1.0
 * @Description :
 * @date : 2020/11/3
 */
/**
 *  如果 注解 fragment 然后还必须注解  fragment 依赖的 Activity, 否则会抛出以下异常
 * java.lang.IllegalStateException: Hilt Fragments must be attached to an @AndroidEntryPoint Activity. Found: class com.hi.dhl.hilt.MainActivity
 */
@AndroidEntryPoint
class HiltFragment : Fragment() {

    // 使用 @Inject 注解从组件中获取依赖
    @Inject
    lateinit var mHiltSimple: HiltSimple

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hilt, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHiltSimple.doSomething()
    }

}