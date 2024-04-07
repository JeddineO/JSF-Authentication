package ma.fstm.ilisi.jsfauthentication.service;


public class UserService {


    public boolean authenticate(String login, String password) {
        if (login.equals("jalal Eddine") && password.equals("123456789")) {
            return true;
        }
        return false;
    }



}
