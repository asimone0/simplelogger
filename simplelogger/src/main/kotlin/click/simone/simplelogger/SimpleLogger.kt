package click.simone.simplelogger

import android.util.Log

interface Logger {
    fun log(obj: Any, message: String)
    fun log(obj: Any, message: String, e: Throwable)
}

object SimpleLogger : Logger {
    var shouldLog = false
    var logger: Logger = AndroidLogger()

    override fun log(obj: Any, message: String) {
        if (shouldLog) logger.log(obj, message)
    }

    override fun log(obj: Any, message: String, e: Throwable) {
        if (shouldLog) logger.log(obj, message, e)
    }
}

class AndroidLogger : Logger {
    override fun log(obj: Any, message: String) {
        Log.d(tag(obj), message)
    }

    override fun log(obj: Any, message: String, e: Throwable) {
        Log.e(tag(obj), message, e)
    }

    fun tag(obj: Any) =
            if (obj is String) obj
            else if (obj.javaClass.simpleName.trim().isEmpty()) obj.toString()
            else obj.javaClass.simpleName.trim()
}
