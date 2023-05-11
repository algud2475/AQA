package utils;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.io.File;

import static io.restassured.RestAssured.given;

public class VkApiUtil {
    private static final String ACCESS_TOKEN = JSONReader.read().getValue("/vkLoginData/token").toString();
    private static final String BASE_URI = "https://api.vk.com/method/";
    private static final String wallPostMethod = "wall.post";
    private static final String wallEditMethod = "wall.edit";
    private static final String wallCreateCommentMethod = "wall.createComment";
    private static final String photosWallUploadServer = "photos.getWallUploadServer";
    private static final String photosSaveWallPhoto = "photos.saveWallPhoto";


    public static Integer createPostAndGetId(String text) {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = wallPostMethod;
        return given()
                .param("access_token", ACCESS_TOKEN)
                .param("v", "5.131")
                .param("message", text)
                .post()
                .path("response.post_id");
    }

    public static Integer editPostById(Integer post_id, String text) {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = wallEditMethod;
        return given()
                .param("access_token", ACCESS_TOKEN)
                .param("v", "5.131")
                .param("owner_id", 793123173)
                .param("post_id", post_id)
                .param("message", text)
                .post()
                .then().log().all()
                .extract()
                .path("response.post_id");
    }

    public static Integer addPhotoToPostById(Integer post_id, String photoData) {
        String owner_id = JsonPath.from(photoData).get("response.owner_id");
        String photo_id = JsonPath.from(photoData).get("response.id");
        String photoAttachments = String.format("photo%s_%s", owner_id, photo_id);
        return given()
                .param("access_token", ACCESS_TOKEN)
                .param("v", "5.131")
                .param("owner_id", 793123173)
                .param("post_id", post_id)
                .param("attachments", photoAttachments)
                .post()
                .then().log().all()
                .extract()
                .path("response.post_id");
        //photo100172_166443618
    }

    public static Integer commentPostById(Integer post_id, String text) {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = wallCreateCommentMethod;
        return given()
                .param("access_token", ACCESS_TOKEN)
                .param("v", "5.131")
                .param("post_id", post_id)
                .param("message", text)
                .post()
                .path("response.post_id");
    }

    public static String getWallUploadServer() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = photosWallUploadServer;
        return given()
                .param("access_token", ACCESS_TOKEN)
                .param("v", "5.131")
                .get()
                .path("response.upload_url");
    }

    public static String uploadPhoto(String URL, String path) {
        System.out.println("UPLOAD PHOTO TO SERVER");
        //RestAssured.baseURI = URL;
        return given()
                .multiPart("photo", new File(path))
                .post(URL)
                .asString();
    }

    public static String saveWallPhoto(String photoData) {
        System.out.println("SAVE UPLOADED PHOTO TO THE WALL");
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = photosSaveWallPhoto;
        return given()
                .contentType(ContentType.JSON)
                .body(photoData)
                .log().all()
                .post()
                .path("response.id");
    }
}
