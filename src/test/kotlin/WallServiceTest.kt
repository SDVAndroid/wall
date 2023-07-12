import junit.framework.TestCase.*
import org.junit.Test
import java.util.*

class WallServiceTest {

    @Test
    fun add() {
        val service = WallService
        service.add(Post(1, 1, 1, date = Date(), "test1", 1, 1, false, false, false, likes = Likes(), "type", 2))

        val add = Post(1, 1, 1, date = Date(), "test1", 1, 1, false, false, false, likes = Likes(), "type", 2)
        val addResult = (add.id > 0)

        assertTrue(addResult)
    }

    @Test
    fun updateTrue() {
        val service = WallService
        service.add(Post(1, 1, 1, date = Date(), "test1", 1, 1, false, false, false, likes = Likes(), "type", 2))
        service.add(Post(2, 2, 2, date = Date(), "test2", 2, 2, false, false, false, likes = Likes(), "type", 2))

        val update = Post(2, 2, 2, date = Date(), "test3", 2, 2, false, false, false, likes = Likes(), "type", 2)

        val result = service.update(update)

        assertTrue(result)
    }


    @Test
    fun updateFalse() {
        val service = WallService
        service.add(Post(1, 1, 1, date = Date(), "test1", 1, 1, false, false, false, likes = Likes(), "type", 2))
        service.add(Post(2, 2, 2, date = Date(), "test2", 2, 2, false, false, false, likes = Likes(), "type", 2))
        service.add(Post(3, 3, 3, date = Date(), "test3", 2, 2, false, false, false, likes = Likes(), "type", 2))

        val update = Post(5, 2, 2, date = Date(), "test2", 2, 2, false, false, false, likes = Likes(), "type", 2)

        val result = service.update(update)

        assertFalse(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrowExceptionForInvalidPost() {
        val service = WallService
        val postId = 2
        val comment = Comment(100, 1, date = Date(), "Post post!")

        service.createComment(postId, comment)

    }

    @Test
    fun shouldCreateCommentForValidPost() {
        val service = WallService
        val postId = 2
        val comment = Comment(1, 1, date = Date(), "Post post!")
        val createdComment = service.createComment(postId, comment)

        assertEquals(comment.id, createdComment.id)
        assertEquals(comment.fromId, createdComment.fromId)
        assertEquals(comment.text, createdComment.text)
    }
}

