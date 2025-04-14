package hnu.multimedia.androiddh.week6

import android.app.Application
import android.content.Context

class AppContext : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: AppContext? = null

        fun get(): Context {
            return instance!!.applicationContext
        }
    }
}