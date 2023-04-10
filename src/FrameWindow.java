import javax.swing.*;
import java.awt.*;

public class FrameWindow {

    public static void FrameShowing() {
        JFrame frame = new JFrame("Compare Files");

        // Styling
        frame.getContentPane().setBackground(Color.lightGray);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null); // JFrame in center


        // declare default functions
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // show Frame
        frame.setVisible(true);
    }

}
