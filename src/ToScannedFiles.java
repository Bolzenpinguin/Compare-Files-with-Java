import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class ToScannedFiles extends FileObject {

    public boolean deleteTempFile;
    private String path;
    private BufferedReader reader;
    private File pathOfFolderAsFile;

    // ************************************ Constructor ************************************
    public ToScannedFiles(String nameTXT, String path, boolean formatting, boolean deleteTempFile, boolean commentsProcess) throws IOException {
        // Inheritance from FileObject
        super(nameTXT, formatting, commentsProcess);
        /* return from constructor
        name,
        formatting,
        commentsProcess,
        fileOfTXT,
        outputWritten */

        this.deleteTempFile = deleteTempFile;
        this.path = path;
        this.reader = new BufferedReader(new InputStreamReader((new FileInputStream(nameTXT))));
        this.pathOfFolderAsFile = new File(path);

    }

    // ************************************ Methods ************************************
    public void CleanUP() {
        // Delete of temp files
        if (fileOfTXT.delete()) {
            if(commentsProcess)
                System.out.println("Deleted the file: " + fileOfTXT.getName());
        } else {
            System.out.println("Failed to delete the file: " + fileOfTXT.getName());
            System.exit(1);
        }
    }

    public String[] ReadFileIn() throws IOException{
        String[] filledArray = new String[LineCount()];
        int place = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(nameTXT));
            String line = reader.readLine();
            while (line != null) {
                filledArray[place] = line;
                place++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filledArray;
    }

    private int LineCount() throws IOException {
        int lineCount = 0;
        while (reader.readLine() != null)
            lineCount++;

        return lineCount;
    }

    public String[] MissingFiles(String[] mainArray, String[] subArray) {
        boolean contains;
        for (int i = 0; i < mainArray.length; i++) {
            for (int j = 0; j < subArray.length; j++) {
                contains = Arrays.asList(mainArray[i]).contains(subArray[j]);

                if (contains) {
                    mainArray[i] = null;
                }
            }
        }

        mainArray = CleanArrayUp(mainArray);
        return mainArray;
    }

    private String[] CleanArrayUp (String[] arrayCleaner) {
        return Arrays.stream(arrayCleaner).filter(Objects::nonNull).toArray(String[]::new);
    }

}