package users;

import aquality.selenium.browser.AqualityServices;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import constants.Constants;
import utils.RandomStringUtil;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Post {
    private String title;
    private String body;
    private Integer userId;
    private Integer id;

    public Post() {
    }

    public static Post getRandomPost(Integer userId) {
        Post post = new Post();
        post.setTitle(RandomStringUtil.getRandomString(Constants.RANDOM_STRING_LENGTH));
        post.setBody(RandomStringUtil.getRandomString(Constants.RANDOM_STRING_LENGTH));
        post.setUserId(userId);
        return post;
    }

    public static String getJsonString(Post post) {
        String postAsString = null;
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            postAsString = ow.writeValueAsString(post);
        } catch (JsonProcessingException e) {
            AqualityServices.getLogger().info(e.getMessage());
            throw new RuntimeException(e);
        }
        return postAsString;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
