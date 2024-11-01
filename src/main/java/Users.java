import java.util.ArrayList;
import java.util.List;

public class Users {

    private List<User> userList = new ArrayList<>();


    public User getUser(String userName) {
        for (User user : userList) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    public String getPassword(String userName) {
        for (User user : userList) {
            if (user.getUserName().equals(userName)) {
                return user.getPassword();
            }
        }
        return null;
    }

    public void addUser1(User user) {
        userList.add(user);
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}
