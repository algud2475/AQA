package steps;

import models.User;

import java.util.List;
import java.util.Optional;

public class UserSteps {
    public static User getUserById(List<User> users, Integer id) {
        Optional<User> userOpt = users.stream().filter(user -> user.getId().equals(id)).findFirst();
        if(userOpt.isPresent()) {
            return userOpt.get();
        } else {
            return null;
        }
    }

    /*
    public static User getUserById(List<User> users, Integer id) {
        for (User us : users) {
            if (us.getId() == id) {
                return us;
            }
        }
        return null;
    }

     */
}
