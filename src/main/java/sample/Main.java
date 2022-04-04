package sample;

import presentation.LoginFrame;
import service.UserService;

public class Main {

    private static LoginFrame loginFrame;

    public Main() {

    }

    public final LoginFrame get_main_frame(){
        return this.loginFrame;
    }

    public static void main(String[] args){
        loginFrame = new LoginFrame();
    }

}
