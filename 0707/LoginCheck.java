public class LoginCheck {
    public static void main(String[] args) {
        String username = "admin";
        String password = "1234";

        String inputUsername = "admin";
        String inputPassword = "1234";

        boolean loginSuccess = username.equals(inputUsername) && password.equals(inputPassword);

        System.out.println("輸入帳號: " + inputUsername);
        System.out.println("輸入密碼: " + inputPassword);
        System.out.println("登入是否成功: " + loginSuccess);
    }
}