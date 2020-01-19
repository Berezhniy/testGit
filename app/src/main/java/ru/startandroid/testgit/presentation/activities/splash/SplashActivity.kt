package ru.startandroid.testgit.presentation.activities.splash

import android.os.Handler
import ru.startandroid.testgit.R
import ru.startandroid.testgit.databinding.SplashBinding
import ru.startandroid.testgit.di.component.ViewModelComponent
import ru.startandroid.testgit.presentation.base.BaseActivity
import ru.startandroid.testgit.utils.DELAY_3000

class SplashActivity : BaseActivity<SplashBinding>() {

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun setupViewLogic(binding: SplashBinding) {
        Handler().postDelayed({
            navigator.openMainScreen()
            finish()
        }, DELAY_3000)
    }
}
