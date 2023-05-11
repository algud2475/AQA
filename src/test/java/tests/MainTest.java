package tests;

import utils.VkApiUtil;

public class MainTest extends BaseTest {
    private final static String PATH = "src/test/resources/catzilla.png";
    //"D:\A1QA\Level 2\a.gudin\src\test\resources"

    public static void main(String[] args) {
        //Integer id = VkApiUtil.createPostAndGetId("Testing");
        //VkApiUtil.editPostById(id, "And this is the new message");
        String URLforPhoto = VkApiUtil.getWallUploadServer();
        String photoData = VkApiUtil.uploadPhoto(URLforPhoto, PATH);
        String photoInfo = VkApiUtil.saveWallPhoto(photoData);
        VkApiUtil.addPhotoToPostById(479 ,photoInfo);
        //VkApiUtil.commentPostById(479, "Just a comment");

    }
}
