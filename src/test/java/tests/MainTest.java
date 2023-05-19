package tests;

import io.restassured.path.json.JsonPath;
import utils.VkApiUtil;

public class MainTest extends BaseTest {
    private final static String PATH = "src/test/resources/catzilla.png";

    public static void main(String[] args) {
        //Integer postId = VkApiUtil.createPostAndGetId("Testing");
        //VkApiUtil.editPostById(id, "And this is the new message");
        String URLforPhoto = VkApiUtil.getWallUploadServer();
        System.out.println(URLforPhoto);
        String photoData = VkApiUtil.uploadPhoto(URLforPhoto, PATH);
        System.out.println(JsonPath.from(photoData).getString(""));
        //JsonPath photoData2 = VkApiUtil.uploadPhoto(URLforPhoto, PATH);
        //System.out.println(photoData2.getString(""));
        String uploadedPhotoData = VkApiUtil.saveWallPhoto(photoData);
        //VkApiUtil.addPhotoToPostById(postId, uploadedPhotoData);
        //VkApiUtil.commentPostById(479, "Just a comment");

    }
}
