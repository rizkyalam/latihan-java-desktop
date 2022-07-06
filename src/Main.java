import javax.swing.*;
import components.*;
import java.util.ArrayList;

/**
 *
 * @author rizkyalam
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login login = new Login();
                login.setVisible(true);
            }
        });
    }
}
