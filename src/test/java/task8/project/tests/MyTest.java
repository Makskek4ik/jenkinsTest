package task8.project.tests;

import org.testng.annotations.Test;
import task8.project.forms.LeftMenu;
import task8.project.forms.MyProfilePage;
import task8.project.forms.WelcomePage;
import task8.project.model.Author;
import task8.project.model.Image;
import task8.project.model.WallPost;
import task8.utils.ConfigFileReader;
import task8.utils.ImageUtils;
import task8.utils.RandomData;
import task8.vkApi.RequestVkApi;
import java.io.IOException;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class MyTest extends BaseTest {

    private final static String PHOTO_NAME = "poster.jpg";
    private int lengthRandomString = 3;

    @Test
    public void vkApi() throws IOException, InterruptedException {
        WelcomePage welcomePage = new WelcomePage();
        assertTrue("welcome page has not been opened", welcomePage.isOpened());
        welcomePage.logIn(ConfigFileReader.getLogin(), ConfigFileReader.getPassword());
        welcomePage.timeForCaptcha();
        welcomePage.goToMyProfile();
        BrowserActions.waitPage();
        LeftMenu leftMenu = new LeftMenu();
        MyProfilePage myProfilePage = leftMenu.getMyProfilePage();
        WallPost wallPost = new WallPost(new Author(myProfilePage.getNameProfile()), RandomData.randomString(lengthRandomString));
        RequestVkApi.sendPost(wallPost);
        assertEquals("Author of the post is not the owner of the page", wallPost.getAuthor().getName(), myProfilePage.getPostAuthorName(wallPost));
        assertEquals("Text in the post does not match", wallPost.getMessage(), myProfilePage.getPostText(wallPost));

        wallPost.setMessage(RandomData.randomString(lengthRandomString));
        RequestVkApi.editWallPost(wallPost, PHOTO_NAME);
        assertTrue("image has not been displayed", myProfilePage.imageIsDisplayed(wallPost));
        assertEquals("Text in the post does not match", wallPost.getMessage(), myProfilePage.getPostText(wallPost));
        Image expectedImage = new Image(PHOTO_NAME);
        Image actualImage = new Image(ImageUtils.downloadImage(myProfilePage.downloadFullImageFromPost()));
        assertEquals("image not equals", expectedImage, actualImage);

        BrowserActions.goBack();
        String comment = RandomData.randomString(lengthRandomString);
        RequestVkApi.createComment(wallPost, comment);
        myProfilePage.showComments();
        assertEquals("Author of the comment is not the owner of the page", wallPost.getAuthor().getName(), myProfilePage.getAuthorNameComment(wallPost));
        assertEquals("Comment text did not match", comment, myProfilePage.getCommentText(wallPost));

        myProfilePage.markLike(wallPost);
        assertTrue("Like has not been check", RequestVkApi.isLiked(wallPost));
        RequestVkApi.deletePost(wallPost);
        assertTrue("post has not been deleted", myProfilePage.isDeletedPost(ConfigFileReader.getTimeOutSec(), wallPost));
    }
}

