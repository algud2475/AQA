package steps;

import models.Post;

import java.util.List;

public class PostSteps {
    public static boolean postsSortedAscendingById(List<Post> posts) {
        for (int i = 0; i < posts.size()-2; i++) {
            if (posts.get(i+1).getId() < posts.get(i).getId()) {
                return false;
            }
        }
        return true;
    }
}
