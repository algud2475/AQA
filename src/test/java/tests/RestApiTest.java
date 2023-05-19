package tests;

import aquality.selenium.browser.AqualityServices;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import models.Post;
import models.User;
import steps.PostSteps;
import steps.UserSteps;
import utils.ApiUtil;
import utils.FileSystemUtil;
import utils.JSONReader;

import java.util.List;

public class RestApiTest extends BaseTest {
    private static final String POSTS_PATH = JSONReader.getConfig().getValue("/POSTS_PATH").toString();
    private static final String POST_VALID_PATH = JSONReader.getConfig().getValue("/POST_VALID_PATH").toString();
    private static final String POST_INVALID_PATH = JSONReader.getConfig().getValue("/POST_INVALID_PATH").toString();
    private static final String USERS_PATH = JSONReader.getConfig().getValue("/USERS_PATH").toString();
    private static final String USER_TEST_PATH = JSONReader.getConfig().getValue("/USER_TEST_PATH").toString();
    private static final Integer POST_TEST_USER_ID = (Integer) JSONReader.getTestData().getValue("/POST_TEST_USER_ID");
    private static final Integer USER_TEST_ID = (Integer) JSONReader.getTestData().getValue("/USER_TEST_ID");
    private static final Integer EXPECTED_USER_ID = 10;
    private static final Integer EXPECTED_ID = 99;
    private static final String FILE_WITH_USER_TEST_DATA = "src/test/resources/testData/users/testUser.json";
    private final User testUser = JSONReader.getObject(FileSystemUtil.readFileToString(FILE_WITH_USER_TEST_DATA), User.class);
    private final Post randomPost = Post.getRandomPost(POST_TEST_USER_ID);

    @Test
    public void test() {
        AqualityServices.getLogger().info("1 STEP ACTION: 'Send GET Request to get all posts (/posts)'");
        AqualityServices.getLogger().info("1 STEP EXPECTED RESULT: 'Posts are sorted ascending (by id)'");
        List<Post> posts = JSONReader.getListObjects(ApiUtil.getRequest(POSTS_PATH), Post.class);
        boolean postsSortedAscendingById = PostSteps.postsSortedAscendingById(posts);

        Assert.assertTrue(postsSortedAscendingById, "Posts are not sorted ascending (by id)");

        AqualityServices.getLogger().info("2 STEP ACTION: 'Send GET request to get post with id=99 (/posts/99)'");
        AqualityServices.getLogger().info("2 STEP EXPECTED RESULT: 'Post information is correct: userId is 10, id is " +
                "99, title and body aren't empty'");
        Post post = JSONReader.getObject(ApiUtil.getRequest(POST_VALID_PATH), Post.class);

        Assert.assertEquals(post.getUserId(), EXPECTED_USER_ID, "Post information is not correct: userId is not 10");
        Assert.assertEquals(post.getId(), EXPECTED_ID, "Post information is not correct: id is not 99");
        Assert.assertNotNull(post.getTitle(), "Post information is not correct: title are empty");
        Assert.assertNotNull(post.getBody(), "Post information is not correct: body are empty");

        AqualityServices.getLogger().info("3 STEP ACTION: 'Send GET request to get post with id=150 (/posts/150)'");
        AqualityServices.getLogger().info("3 STEP EXPECTED RESULT: 'Status code is 404'");
        AqualityServices.getLogger().info("3 STEP EXPECTED RESULT: 'Response body is empty'");

        Assert.assertEquals(ApiUtil.getRequest(POST_INVALID_PATH, HttpStatus.SC_NOT_FOUND), "{}", "Response body is not empty");

        AqualityServices.getLogger().info("4 STEP ACTION: 'Send POST request to create post with userId=1 and random " +
                "body and random title (/posts)'");
        AqualityServices.getLogger().info("4 STEP EXPECTED RESULT: 'Status code is 201'");
        AqualityServices.getLogger().info("4 STEP EXPECTED RESULT: 'Post information is correct: title, body, userId " +
                "match data from request, id is present in response'");
        Post recievedPost = JSONReader.getObject(ApiUtil.postRequest(POSTS_PATH, Post.getJsonString(randomPost), HttpStatus.SC_CREATED), Post.class);

        Assert.assertEquals(randomPost.getTitle(), recievedPost.getTitle(), "Post information is not correct: " +
                "title doesn`t match data from request");
        Assert.assertEquals(randomPost.getBody(), recievedPost.getBody(), "Post information is not correct: " +
                "body doesn`t match data from request");
        Assert.assertEquals(randomPost.getUserId(), recievedPost.getUserId(), "Post information is not correct: " +
                "userId doesn`t match data from request");
        Assert.assertNotNull(recievedPost.getId(), "Post information is not correct: id is not present in response");

        AqualityServices.getLogger().info("5 STEP ACTION: 'Send GET request to get users (/users)'");
        AqualityServices.getLogger().info("5 STEP EXPECTED RESULT: 'User (id=5) data equals to TestUserData'");
        User recievedUserFromList = UserSteps.getUserById(JSONReader.getListObjects(ApiUtil.getRequest(USERS_PATH), User.class), USER_TEST_ID);

        Assert.assertEquals(recievedUserFromList, testUser, "User (id=5) data doesn`t equal to TestUserData");

        AqualityServices.getLogger().info("6 STEP ACTION: 'Send GET request to get user with id=5 (/users/5)'");
        AqualityServices.getLogger().info("6 STEP EXPECTED RESULT: 'User data matches with user data in the previous step'");
        User recievedUser = JSONReader.getObject(ApiUtil.getRequest(USER_TEST_PATH), User.class);

        Assert.assertEquals(recievedUser, recievedUserFromList);
    }
}
