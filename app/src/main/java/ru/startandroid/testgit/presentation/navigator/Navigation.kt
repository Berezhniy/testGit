package ru.startandroid.testgit.presentation.navigator

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import ru.startandroid.testgit.R
import ru.startandroid.testgit.presentation.activities.main.MainActivity
import ru.startandroid.testgit.presentation.base.BaseActivity

interface Navigation {
    var navigatorSource: BaseActivity<*>
    fun openMainScreen()
    fun showExitConfirmDialog(onAccepted: () -> Unit)
}

class NavigationImpl(override var navigatorSource: BaseActivity<*>) : Navigation {


    override fun openMainScreen() {
        val intent = Intent(navigatorSource, MainActivity::class.java).apply {
            this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        navigatorSource.startActivity(intent)
    }

    override fun showExitConfirmDialog(onAccepted: () -> Unit) {
        navigatorSource.let {
            AlertDialog.Builder(it)
                .setMessage(R.string.exit_confirm)
                .setPositiveButton(R.string.yes) { dialog, _ ->
                    dialog.dismiss()
                    onAccepted()
                }.setNegativeButton(R.string.no) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }
}