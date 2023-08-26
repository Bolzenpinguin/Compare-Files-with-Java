import javax.swing.*;

public class FrameWindowStart extends JFrame {
    public FrameWindowStart() {
        setTitle("My Window");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel label1 = new JLabel("Argument 1:");
        JTextField textField1 = new JTextField(10);
        JLabel label2 = new JLabel("Argument 2:");
        JTextField textField2 = new JTextField(10);
        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        add(panel);

        setVisible(true);
    }

}
