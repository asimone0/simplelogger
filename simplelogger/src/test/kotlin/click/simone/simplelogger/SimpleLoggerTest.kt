package click.simone.simplelogger

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class SimpleLoggerTest {

    class MockLogger: Logger{
        var obj : Any = ""
        var message: String = ""
        var e: Throwable? = null

        override fun logDebug(obj: Any, message: String) {
            this.obj = obj
            this.message = message
        }

        override fun logThrowable(obj: Any, message: String, e: Throwable) {
            this.obj = obj
            this.message = message
            this.e = e
        }
    }

    var mockLogger = MockLogger()

    @Before
    public fun setUp() {
        mockLogger = MockLogger()
        SimpleLogger.logger = mockLogger
    }

    @Test
    fun logDebug() {
        SimpleLogger.shouldLog = true
        SimpleLogger.log(this, "test")
        assertSame(this, mockLogger.obj)
        assertEquals("test", mockLogger.message)
        assertNull(mockLogger.e)
    }

    @Test
    fun logDebugWithShouldLogFalse() {
        SimpleLogger.shouldLog = false
        SimpleLogger.log(this, "test")
        assertEquals("", mockLogger.obj)
        assertEquals("", mockLogger.message)
        assertNull(mockLogger.e)
    }

    @Test
    fun logThrowable() {
        SimpleLogger.shouldLog = true
        val t = IllegalArgumentException()
        SimpleLogger.log(this, "test", t)
        assertSame(this, mockLogger.obj)
        assertEquals("test", mockLogger.message)
        assertSame(t, mockLogger.e)
    }

    @Test
    fun logThrowableWithShouldLogFalse() {
        SimpleLogger.shouldLog = false
        val t = IllegalArgumentException()
        SimpleLogger.log(this, "test", t)
        assertEquals("", mockLogger.obj)
        assertEquals("", mockLogger.message)
        assertNull(mockLogger.e)
    }

}