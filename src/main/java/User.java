public class User {


    private String userName;
    private String password;
    private final String QUESTION1 = "Name of your first Pet?";
    private final String QUESTION2 = "City your were born in?";
    private String answer1;
    private String answer2;


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }








    public String getQUESTION1() {
        return QUESTION1;
    }

    public String getQUESTION2() {
        return QUESTION2;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
}
