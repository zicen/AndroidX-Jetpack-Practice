package com.hi.dhl.hilt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.hi.dhl.hilt.R
import com.hi.dhl.hilt.di.HiltSimple
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_hilt.*
import javax.inject.Inject

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/6/24
 *     desc  :
 * </pre>
 */
/**
 *  如果 注解 fragment 然后还必须注解  fragment 依赖的 Activity, 否则会抛出以下异常
 * java.lang.IllegalStateException: Hilt Fragments must be attached to an @AndroidEntryPoint Activity. Found: class com.hi.dhl.hilt.ui.MainActivity
 */
@AndroidEntryPoint
class HiltFragment : Fragment() {
    // 使用 @Inject 注解从组件中获取依赖
    @Inject
    lateinit var mHiltSimple: HiltSimple
    val mHiltViewModel: HiltViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hilt, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHiltSimple.doSomething()

        mHiltViewModel.mAdressLiveData.observe(viewLifecycleOwner, Observer {
            tvAddress.text = it
        })
        // 手动依赖注入
        if (activity is HitAppCompatActivity2) {
            val ac = activity as HitAppCompatActivity2
            tvAddress.text = ac.userInfo.name
        }
    }
}
