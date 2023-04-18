import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileObject {
    // Superclass
    public String nameTXT;
    public boolean formatting;
    public boolean commentsProcess;
    public File fileOfTXT;
    public PrintWriter outputWritten;

    public FileObject(String nameTXT, boolean formatting, boolean commentsProcess ) throws IOException {
        this.nameTXT = nameTXT;
        this.formatting = formatting;
        this.commentsProcess = commentsProcess;
        this.fileOfTXT = new File(nameTXT);
        this.outputWritten = new PrintWriter(nameTXT);
    }

}
