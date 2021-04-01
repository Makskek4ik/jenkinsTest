package task8.project.model;

public class WallPost {
    private String message;
    private Image image;
    private Author author;
    private String postId;

    public WallPost(Author author) {
        this.author = author;
    }

    public WallPost(Author author, String message) {
        this.message = message;
        this.author = author;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getPostId() {
        return postId;
    }

    public String getMessage() {
        return message;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Author getAuthor() {
        return author;
    }
}
