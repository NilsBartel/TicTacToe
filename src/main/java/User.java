import java.util.HashMap;
import java.util.Map;

public class User {


    private Map<String, String> map = new HashMap<>();




    public boolean validateUserName(String userName) {
        return map.containsKey(userName);
    }

    public boolean validatePassword(String userName, String password) {
        return map.get(userName).equals(password);
    }



    public void addUser(String userName, String password) {
        map.put(userName, password);
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
