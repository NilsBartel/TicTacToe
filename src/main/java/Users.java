import java.util.HashMap;
import java.util.Map;

public class Users {


    private Map<String, String> map = new HashMap<>();




    public boolean userNameExists(String userName) {
        return map.containsKey(userName);
    }

    public boolean validatePassword(String userName, String password) {
        return map.get(userName).equals(password);
    }

    public String getPassword(String userName) {
        return map.get(userName);
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
