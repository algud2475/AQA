package steps;

import models.User;

import java.util.List;

public class UserSteps {
    public static User getUserById(List<User> users, Integer id) {
        for (User us : users) {
            if (us.getId() == id) {
                return us;
            }
        }
        return null;
    }
}
