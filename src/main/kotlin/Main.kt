import java.util.Date

data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val date: Date = Date(),
    val text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean = false,
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val likes: Likes = Likes()
)

object WallService {
    private var numPostsWall = emptyArray<Post>()

    fun add(post: Post): Post {
        var nextId = post.id
        numPostsWall += post.copy(id = ++nextId)
        return numPostsWall.last()
    }

    fun update(post: Post): Boolean {
        for ((id, wallPost) in numPostsWall.withIndex()) {
            if (wallPost.id == post.id) {
                numPostsWall[id] = post.copy()
                return true
            }
        }
        return false

    }
}
    class Likes(val countLikes: Int = 0)

    fun main(args: Array<String>) {
        println(WallService.add(Post(1, 1, 2, date = Date(), "First post", 3, 4)))
        println(WallService.add(Post(2, 2, 4, date = Date(), "Second Post", 5, 6)))
        println(WallService.update(Post(2,2,3, date = Date(), "Post", 5,5)))
    }
