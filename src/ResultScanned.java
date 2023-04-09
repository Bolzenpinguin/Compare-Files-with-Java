import java.io.IOException;

public class ResultScanned extends FileObject {

    public boolean resultPrintTXT;

    public ResultScanned(String nameTXT, boolean formatting, boolean commentsProcess, boolean resultPrintTXT) throws IOException {
        // Inheritance from FileObject
        super(nameTXT, formatting, commentsProcess);
        /* return from constructor
            String nameTXT;
            boolean formatting;
            boolean commentsProcess;
            File fileOfTXT;
            PrintWriter outputWritten;
        */
        this.resultPrintTXT = resultPrintTXT; // print out as txt or in console

    }

    public void PrintMissingFiles(String[] arrayGivenWithoutNull) {
        // Write Results in txt or console
        if (resultPrintTXT) {
            outputWritten.println("Following files and [folders] are missing: ");
            outputWritten.println();

            for (int i = 0; i < arrayGivenWithoutNull.length; i++) {
                outputWritten.println(arrayGivenWithoutNull[i]);
            }

            outputWritten.close();
        } else {
            System.out.println("Following files and [folders] are missing: ");
            System.out.println();

            for (int i = 0; i < arrayGivenWithoutNull.length; i++) {
                System.out.println(arrayGivenWithoutNull[i]);
            }

        }

    }
}
