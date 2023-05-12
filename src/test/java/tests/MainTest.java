package tests;

import utils.VkApiUtil;

public class MainTest extends BaseTest {
    private final static String PATH = "src/test/resources/catzilla.png";

    public static void main(String[] args) {
        Integer postId = VkApiUtil.createPostAndGetId("Testing");
        //VkApiUtil.editPostById(id, "And this is the new message");
        String URLforPhoto = VkApiUtil.getWallUploadServer();
        String photoData = VkApiUtil.uploadPhoto(URLforPhoto, PATH);
        String uploadedPhotoData = VkApiUtil.saveWallPhoto(photoData);
        VkApiUtil.addPhotoToPostById(postId, uploadedPhotoData);
        //VkApiUtil.commentPostById(479, "Just a comment");

    }
}
