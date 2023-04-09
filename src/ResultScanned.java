import java.io.IOException;
import java.io.PrintWriter;

public class ResultScanned extends FileObject {

    public boolean resultPrintTXT;

    public ResultScanned(String nameTXT, boolean formatting, boolean commentsProcess, boolean resultPrintTXT) throws IOException {
        // Inheritance from FileObject
        super(nameTXT, formatting, commentsProcess);
        /* return from constructor
        name,
        formatting,
        commentsProcess,
        fileOfTXT,
        outputWritten */
        this.resultPrintTXT = resultPrintTXT; // print out as txt or in console

    }

    public void PrintMissingFiles(String[] arrayGivenWithNull) throws IOException {
        // Write Results in txt or console
        if (resultPrintTXT) {
            outputWritten.println("Following files and [folders] are missing: ");
            outputWritten.println();

            for (int i = 0; i < arrayGivenWithNull.length; i++) {
                outputWritten.println(arrayGivenWithNull[i]);
            }

            outputWritten.close();
        } else {
            System.out.println("Following files and [folders] are missing: ");
            System.out.println();

            for (int i = 0; i < arrayGivenWithNull.length; i++) {
                System.out.println(arrayGivenWithNull[i]);
            }

        }

    }
}
