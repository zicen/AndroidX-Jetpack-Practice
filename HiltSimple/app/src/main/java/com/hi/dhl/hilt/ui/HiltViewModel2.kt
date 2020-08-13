package com.hi.dhl.hilt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hi.dhl.paging3.data.local.PersonDao
import com.hi.dhl.paging3.data.local.PersonEntity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/6/24
 *     desc  :
 * </pre>
 */
class HiltViewModel2 @ViewModelInject constructor(
    val personDao: PersonDao

) : ViewModel() {

    private val _mAdressLiveData = MutableLiveData<String>()
    val mAdressLiveData: LiveData<String> = _mAdressLiveData

    /**
     * 在 LifeCycle 2.2.0 之后，可以用更精简的方法来完成，使用 LiveData 协程构造方法 (coroutine builder)。
     * liveData 协程构造方法提供了一个协程代码块，产生的是一个不可变的 LiveData，emit() 方法则用来更新 LiveData 的数据。
     *
     * 具体可以查看之前写的这篇文章 [https://juejin.im/post/5ee998e8e51d4573d65df02b#heading-10] 有详细介绍
     */
    val mHitLiveData = liveData {
        emit("i am a ViewModelInject2")
    }


    fun insert() {
        // 为了保持项目的简单，这里仅仅做测试用，实际开发的时候，不能在这里进行数据库的操作
        AppExecutors.disIO {
            personDao.insert(PersonEntity(name = "dhl", updateTime = System.currentTimeMillis()))
        }
    }

    fun passArgument(address: String) {
        _mAdressLiveData.value = address
    }
}
