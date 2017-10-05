package click.simone.simplelogger

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class SimpleLoggerTest {

    class MockLogger: Logger{
        var obj : Any = ""
        var message: String = ""
        var e: Throwable? = null

        override fun log(obj: Any, message: String) {
            this.obj = obj
            this.message = message
        }

        override fun log(obj: Any, message: String, e: Throwable) {
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
    fun getLogger() {
        assertSame(mockLogger, SimpleLogger.logger)
    }

    @Test
    fun getShouldLog() {
        assertFalse(SimpleLogger.shouldLog)
    }

    @Test
    fun log() {
        SimpleLogger.shouldLog = true
        SimpleLogger.log(this, "test")
        assertSame(this, mockLogger.obj)
        assertEquals("test", mockLogger.message)
        assertNull(mockLogger.e)
    }

    @Test
    fun logWithShouldLogSetToFalse() {
        SimpleLogger.shouldLog = false
        SimpleLogger.log(this, "test")
        assertEquals("", mockLogger.obj)
        assertEquals("", mockLogger.message)
        assertNull(mockLogger.e)
    }

    @Test
    fun logWithThrowable() {
        SimpleLogger.shouldLog = true
        val t = IllegalArgumentException()
        SimpleLogger.log(this, "test", t)
        assertSame(this, mockLogger.obj)
        assertEquals("test", mockLogger.message)
        assertSame(t, mockLogger.e)
    }

    @Test
    fun logWithThrowableWithShouldLogSetToFalse() {
        SimpleLogger.shouldLog = false
        val t = IllegalArgumentException()
        SimpleLogger.log(this, "test", t)
        assertEquals("", mockLogger.obj)
        assertEquals("", mockLogger.message)
        assertNull(mockLogger.e)
    }

}