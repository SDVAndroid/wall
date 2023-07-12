import java.lang.RuntimeException
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
    val likes: Likes = Likes(),
    val postType: String?,
    val isPinned: Int?,
    val attachments: Array<Attachments> = emptyArray()
)

data class Comment(
    val id: Int,
    val fromId: Int,
    val date: Date = Date(),
    val text: String,
    val attachments: Array<Attachments> = emptyArray()
)

interface Attachments {
    val type: String
}

data class photoAttachments(val photo: Photo) : Attachments {
    override val type: String = "photo"
}

data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int,
    val text: String
)

data class videoAttachments(val video: Video) : Attachments {
    override val type: String = "video"
}

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val duration: Int
)

data class audioAttachments(val audio: Audio) : Attachments {
    override val type: String = "audio"
}

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int
)

data class noteAttachments(val note: Note) : Attachments {
    override val type: String = "note"
}

data class Note(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val comments: Int,
    val readComments: Int
)

data class pageAttachments(val page: Page) : Attachments {
    override val type: String = "page"
}

data class Page(
    val id: Int,
    val groupId: Int,
    val creatorId: Int,
    val title: String,
    val text: String
)

class PostNotFoundException(massage: String) : RuntimeException(massage)

object WallService {
    private var numPostsWall = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    var posts = emptyArray<Post>()

    fun createComment(postId: Int, comment: Comment): Comment {
        val post = numPostsWall.find { it.id == postId }
        if (post != null) {
            val lastCommentId = comments.lastOrNull()?.id ?: 0
            val newComment = comment.copy(id = lastCommentId + 1, date = Date())
            comments += newComment
            return newComment
        } else {
            throw PostNotFoundException("Пост с идентификатором $postId не найден")

        }
    }

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

fun main() {
    println(
        WallService.add(
            Post(1, 1, 2, date = Date(), "First post", 3, 4, false, false, false, likes = Likes(), "type", 2)
        )
    )
    println(
        WallService.add(
            Post(2, 2, 4, date = Date(), "Second Post", 5, 6, false, false, false, likes = Likes(), "type", 2)
        )
    )
    println(
        WallService.update(
            Post(
                2, 2, 3, date = Date(), "Post", 5, 5, false, false, false, likes = Likes(), "type", 2
            )
        )
    )
    println(
        WallService.createComment(postId = 2, comment = Comment(100,1,date = Date(),"Post post")))
}
