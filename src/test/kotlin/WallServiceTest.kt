import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.DefaultAsserter.assertTrue
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WallServiceTest {

    @Test
    fun add() {
        val service = WallService
        service.add(Post(1, 1, 1, date = Date(), "test1", 1, 1))

        val add = Post(1, 1, 1, date = Date(), "test1", 1, 1)
        val addResult = (add.id > 0)

        assertTrue(addResult)
    }

    @Test
    fun updateTrue() {
        val service = WallService
        service.add(Post(1, 1, 1, date = Date(), "test1", 1, 1))
        service.add(Post(2, 2, 2, date = Date(), "test2", 2, 2))

        val update = Post(2, 2, 2, date = Date(), "test3", 2, 2)

        val result = service.update(update)

        assertTrue(result)
    }


    @Test
    fun updateFalse() {
        val service = WallService
        service.add(Post(1, 1, 1, date = Date(), "test1", 1, 1))
        service.add(Post(2, 2, 2, date = Date(), "test2", 2, 2))
        service.add(Post(3, 3, 3, date = Date(), "test3", 2, 2))

        val update = Post(5, 2, 2, date = Date(), "test2", 2, 2)

        val result = service.update(update)

        assertFalse(result)
    }
}

