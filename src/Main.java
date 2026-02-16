import Controller.ShopController;
import Model.*;
import Repository.ShopRepository;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ShopController controller = new ShopController();
        controller.run();
    }
}