package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
                .path("response.post_id");
    }

    public static Integer addPhotoToPostById(Integer post_id, String photoData) {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = wallEditMethod;
        Integer owner_id = JsonPath.from(photoData).get("response[0].owner_id");
        Integer photo_id = JsonPath.from(photoData).get("response[0].id");
        String photoAttachments = String.format("photo%d_%d", owner_id, photo_id);
        return given()
                .queryParam("access_token", ACCESS_TOKEN)
                .queryParam("v", "5.131")
                .queryParam("owner_id", 793123173)
                .queryParam("post_id", post_id)
                .queryParam("attachments", photoAttachments)
                .post()
                .path("response.post_id");
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
        return given()
                .multiPart("photo", new File(path))
                .post(URL)
                .asString();
    }



    public static JsonPath uploadPhoto2(String URL, String path) {
        return given()
                .multiPart("photo", new File(path))
                .post(URL)
                .jsonPath();
    }


    public static String saveWallPhoto(String photoData) {
        System.out.println("SAVE UPLOADED PHOTO TO THE WALL");
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = photosSaveWallPhoto;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonPhotoData = null;
        try {
            jsonPhotoData = objectMapper.readTree(photoData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("access_token", ACCESS_TOKEN)
                .queryParam("v", "5.131")
                //.queryParam("server", jsonPhotoData.get("server").asText())
                //.queryParam("photo", jsonPhotoData.get("photo").asText())
                //.queryParam("hash", jsonPhotoData.get("hash").asText())
                .queryParam("server", jsonPhotoData.get("server"))
                .queryParam("photo", jsonPhotoData.get("photo"))
                .queryParam("hash", jsonPhotoData.get("hash"))
                .post()
                .asString();
    }



    public static String saveWallPhoto2(JsonPath photoData) {
        System.out.println("SAVE UPLOADED PHOTO TO THE WALL");
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = photosSaveWallPhoto;
        return given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("access_token", ACCESS_TOKEN)
                .queryParam("v", "5.131")
                .queryParam("server", photoData.getString("server"))
                .queryParam("photo", photoData.getString("photo"))
                .queryParam("hash", photoData.getString("hash"))
                .post()
                .asString();
    }
}
