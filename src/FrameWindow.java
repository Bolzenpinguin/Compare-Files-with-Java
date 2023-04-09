import javax.swing.*;

public class FrameWindow {

    public static void FrameShowing() {
        JFrame firstFrame = new JFrame("Compare Files");
        firstFrame.setSize(200, 300);
        firstFrame.add(new JLabel("Beispiel"));
        firstFrame.setVisible(true);
    }

}
