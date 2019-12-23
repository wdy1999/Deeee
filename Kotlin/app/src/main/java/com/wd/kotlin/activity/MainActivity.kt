package com.wd.kotlin.activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wd.kotlin.Api
import com.wd.kotlin.R
import com.wd.kotlin.tool.RetrofitUtlis
import com.wd.kotlin.tool.RsaCoder
import com.wd.kotlin.bean.LoginBean
import com.wd.kotlin.bean.RegisterBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    var context: Context? = null
    var api: Api? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login.setOnClickListener({ v -> login() })
        register.setOnClickListener({ v -> register() })
        api = RetrofitUtlis.instance.create(Api::class.java)


    }

    //登录
    fun login() {
        var phone = phone.text.toString()
        var pwd = pwd.text.toString()
        val encryptByPublicKey = RsaCoder.encryptByPublicKey(pwd)
        api!!.login(phone, encryptByPublicKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<LoginBean>() {
                override fun onComplete() {

                }

                override fun onNext(t: LoginBean) {
                    System.out.println(t.message)

                }


                override fun onError(e: Throwable) {
                    System.out.println(e.message)
                }

            })

    }

    //注册
    fun register() {
        var map = HashMap<String, String>()
        var phone = phone.text.toString()
        var name = name.text.toString()
        var pwd = pwd.text.toString()
        val encryptByPublicKey = RsaCoder.encryptByPublicKey(pwd)
        map.put("phone", phone)
        map.put("nickName", name)
        map.put("pwd", encryptByPublicKey)
        api!!.register(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<RegisterBean>() {
                override fun onComplete() {

                }

                override fun onNext(t: RegisterBean) {
                    System.out.println(t.message)

                }

                override fun onError(e: Throwable) {


                }

            })

    }


}
