import BusinessLayer.Order;
import PresentationLayer.Controller;
import PresentationLayer.LoginView;

import java.util.Date;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        LoginView view = new LoginView();
        new Controller(view);
        view.setVisible(true);

    }
}
