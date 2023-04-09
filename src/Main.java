import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        // set only true if you want to have a good view in the txt data
        boolean formatting = true;
        // set false if you want to keep the txt files
        boolean deleteTempFiles = true;
        // set true for comments in process
        boolean commentsProcess = false;
        // set false for result print console
        boolean resultPrintTXT = true;

        String nameMainTXT = "Main directory.txt";
        String nameSubTXT = "Sub directory.txt";
        String nameResultsTXT = "Result.txt";

        // Paths of search folders
        String maindirpath
                = "/Users/kurtschubert/Desktop/test/testA";
        String subdirpath
                = "/Users/kurtschubert/Desktop/test/testB";

        // Object Creation
        ToScannedFiles fileMain = new ToScannedFiles(nameMainTXT, maindirpath, formatting, deleteTempFiles, commentsProcess);
        ToScannedFiles fileSub = new ToScannedFiles(nameSubTXT, subdirpath, formatting, deleteTempFiles, commentsProcess);
        ResultScanned fileResult = new ResultScanned(nameResultsTXT, formatting, commentsProcess, resultPrintTXT);

        // Fill txt Files with results
        fileMain.FillFiles();
        fileSub.FillFiles();

        // maindir.txt  *****************************************
        String[] mainArray = fileMain.ReadFileIn();

        // subdir.txt  *****************************************
        String[] subArray = fileSub.ReadFileIn();

        // Find differences and clean the array of null
        String[] cleanedArrayFinal = fileMain.MissingFiles(mainArray, subArray);

        // Delete maindir.txt and subdir.txt temp files
        if (deleteTempFiles) {
            fileMain.CleanUP();
            fileSub.CleanUP();
        }

        fileResult.PrintMissingFiles(cleanedArrayFinal);
    }
}