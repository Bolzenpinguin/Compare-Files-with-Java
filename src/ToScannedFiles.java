import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class ToScannedFiles extends FileObject {

    public boolean deleteTempFile;
    private String path;
    private BufferedReader reader;
    private File pathOfFolderAsFile;
    private File[] listFiles;

    // ************************************ Constructor ************************************
    public ToScannedFiles(String nameTXT, String path, boolean formatting, boolean deleteTempFile, boolean commentsProcess) throws IOException {
        // Inheritance from FileObject
        super(nameTXT, formatting, commentsProcess);
        /* return from constructor
            String nameTXT;
            boolean formatting;
            boolean commentsProcess;
        */
        this.deleteTempFile = deleteTempFile;
        this.path = path;
        this.reader = new BufferedReader(new InputStreamReader((new FileInputStream(nameTXT))));
        this.pathOfFolderAsFile = new File(path);
        this.listFiles = pathOfFolderAsFile.listFiles();

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
        // clean all null values from array and simultaneously shorten the array
        return Arrays.stream(arrayCleaner).filter(Objects::nonNull).toArray(String[]::new);
    }


    public void RecursiveSearchThroughFiles(File[] fileDelivered, int level) {
        // for-each loop for main directory files

        for (File file : fileDelivered) {
            // enhanced for loop --> let you easy go through file
            // works only with this method probably do constructor creation otherwise

            if (formatting) {
                for (int i = 0; i < level; i++)
                    outputWritten.print("\t");
            }

            if (file.isFile())
                outputWritten.println((file.getName()));

            else if (file.isDirectory()) {
                outputWritten.println("[" + file.getName() + "]");
                // recursion for sub-directories
                RecursiveSearchThroughFiles(Objects.requireNonNull(file.listFiles()), level + 1);
            }
        }
    }


    public void FillFiles() {
        if ((pathOfFolderAsFile.exists() && pathOfFolderAsFile.isDirectory())) {

            // Check if files exists
            assert listFiles != null;

            RecursiveSearchThroughFiles(listFiles, 0);

        } else {
            System.out.println("Paths not correct for: " + pathOfFolderAsFile.getName() +"Exit!");
            System.exit(1);
        }

        outputWritten.close();
        if(commentsProcess)
            System.out.println(nameTXT + " successful created and filled.");
    }

}