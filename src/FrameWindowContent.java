import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FrameWindowContent extends JFrame {

    public FrameWindowContent(String txtFileName) {

        super("Compare Files with Java");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create JTextArea
        JTextArea textArea = new JTextArea();

        // Set Background color
        getContentPane().setBackground(Color.lightGray);
        textArea.setBackground(Color.lightGray);

        // Set padding
        textArea.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(new Insets(30, 30, 30, 30)),
                textArea.getBorder()));

        // Make Text read only
        textArea.setEditable(false);


        // Create JScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Read text from file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(txtFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add JScrollPane to JFrame
        add(scrollPane);

        // Set JFrame visible
        setVisible(true);
    }
}
