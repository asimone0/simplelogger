package click.simone.simplelogger

import android.util.Log

interface Logger {
    fun logDebug(obj: Any, message: String)
    fun logThrowable(obj: Any, message: String, e: Throwable)
}

object SimpleLogger : Logger {
    var shouldLog = false
    var logger: Logger = AndroidLogger()

    override fun logDebug(obj: Any, message: String) {
        if (shouldLog) logger.logDebug(obj, message)
    }

    override fun logThrowable(obj: Any, message: String, e: Throwable) {
        if (shouldLog) logger.logThrowable(obj, message, e)
    }
}

class AndroidLogger : Logger {
    override fun logDebug(obj: Any, message: String) {
        Log.d(tag(obj), message)
    }

    override fun logThrowable(obj: Any, message: String, e: Throwable) {
        Log.e(tag(obj), message, e)
    }

    fun tag(obj: Any) =
            if (obj is String) obj
            else if (obj.javaClass.simpleName.trim().isEmpty()) obj.toString()
            else obj.javaClass.simpleName.trim()
}
