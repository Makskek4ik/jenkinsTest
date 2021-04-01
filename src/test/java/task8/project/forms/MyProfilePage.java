package task8.project.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;
import task8.project.model.WallPost;

public class MyProfilePage extends Form {
    private final ITextBox postProfile = getElementFactory().getTextBox(By.xpath("//h5[contains(@class,'post_author')]//a[contains(@class,'author')]"), "post profile");
    private final ITextBox postText = getElementFactory().getTextBox(By.xpath("//div[contains(@class,'wall_post_text')]"), "postText");
    private final ITextBox nameProfile = getElementFactory().getTextBox(By.xpath("//div[@id='page_info_wrap']//h1[contains(@class,'page_name')]"), "name profile");
    private final IButton openImage = getElementFactory().getButton(By.xpath("//a[contains(@class,'page_post_thumb_wrap')][contains(@class,'page_post_thumb_last_column')]"), "open image");
    private final ILink image = getElementFactory().getLink(By.xpath("//div[@id='pv_photo']//img"), "image");
    private final ITextBox authorNameComment = getElementFactory().getTextBox(By.xpath("//div[contains(@class,'reply_content')]//a[contains(@class,'author')]"), "author name comment");
    private final ITextBox commentText = getElementFactory().getTextBox(By.xpath("//div[contains(@class,'reply_content')]//div[contains(@class,'wall_reply_text')]"), "comment text");
    private final IButton like = getElementFactory().getButton(By.xpath("//div[contains(@class,'like_btns')]//div[contains(@class,'like_button_icon')]"), "like");
    private final IButton comments = getElementFactory().getButton(By.xpath("//a[contains(@class,'replies_next')][contains(@class,'replies_next_main')]"), "comments");
    private ITextBox myPost;
    private static final int millSecToSec = 1000;

    public MyProfilePage() {
        super(By.id("page_info_wrap"), "myProfilePage");
    }

    public boolean isDeletedPost(int sec, WallPost wallPost) {
        RemoteWebElement post = getElementFactory().getTextBox(By.id(String.format("post%s_%s", wallPost.getAuthor().getId(), wallPost.getPostId())), "post").getElement();
        long currentTime = System.currentTimeMillis();
        while (post.isDisplayed()) {
            if (currentTime + sec * millSecToSec < System.currentTimeMillis()) break;
        }
        return !post.isDisplayed();
    }

    private void findMyPost(WallPost wallPost) {
        myPost = getElementFactory().getTextBox(By.id(String.format("post%s_%s", wallPost.getAuthor().getId(), wallPost.getPostId())), "MyPost");
    }

    public String getPostAuthorName(WallPost wallPost) {
        findMyPost(wallPost);
        return myPost.findChildElement(postProfile.getLocator(), ITextBox.class).getText();
    }

    public String getPostText(WallPost wallPost) {
        findMyPost(wallPost);
        return myPost.findChildElement(postText.getLocator(), ITextBox.class).getText();
    }

    public String getAuthorNameComment(WallPost wallPost) {
        findMyPost(wallPost);
        return myPost.findChildElement(authorNameComment.getLocator(), ITextBox.class).getText();
    }

    public String getCommentText(WallPost wallPost) {
        findMyPost(wallPost);
        return myPost.findChildElement(commentText.getLocator(), ITextBox.class).getText();
    }

    public void markLike(WallPost wallPost) {
        findMyPost(wallPost);
        myPost.findChildElement(like.getLocator(), IButton.class).click();
    }

    public void showComments() {
        comments.click();
    }

    public String getNameProfile() {
        return nameProfile.getText();
    }

    public String downloadFullImageFromPost() {
        openImage.click();
        return image.getAttribute("src");
    }

    public boolean imageIsDisplayed(WallPost wallPost) {
        findMyPost(wallPost);
        return myPost.findChildElement(openImage.getLocator(), ILink.class).getElement().isDisplayed();
    }
}
