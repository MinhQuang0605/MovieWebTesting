package Utils;

public class LoginData {
    private String username;
    private String password;
    private boolean expected;
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isExpected() {
        return expected;
    }

    public LoginData(String username, String password, boolean expected){
        this.username = username;
        this.password = password;
        this.expected = expected;
    }


}
