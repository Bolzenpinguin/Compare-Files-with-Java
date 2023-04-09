import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {

        // set only true if you want to have a good view in the txt data
        boolean formatting = true;
        // set false if you want to keep the txt files
        boolean deleteTempFiles = false;
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

        // OO Test
        ToScannedFiles fileMain = new ToScannedFiles(nameMainTXT, maindirpath, formatting, deleteTempFiles, commentsProcess);
        ToScannedFiles fileSub = new ToScannedFiles(nameSubTXT, subdirpath, formatting, deleteTempFiles, commentsProcess);

        ResultScanned fileResult = new ResultScanned(nameResultsTXT, formatting, commentsProcess, resultPrintTXT);


        PrintWriter outputMain = new PrintWriter(nameMainTXT);
        PrintWriter outputSub = new PrintWriter(nameSubTXT);
        PrintWriter outputResult = new PrintWriter(nameResultsTXT);





        // File object
        File maindir = new File(maindirpath);
        File subdir = new File(subdirpath);

        FillFiles(maindir, outputMain, formatting, nameMainTXT, commentsProcess);
        FillFiles(subdir, outputSub, formatting, nameSubTXT, commentsProcess);

        // maindir.txt  *****************************************
        String[] mainArray = fileMain.ReadFileIn();

        // subdir.txt  *****************************************
        String[] subArray = fileSub.ReadFileIn();

        // Find differences
        String[] cleanedArrayFinal = fileMain.MissingFiles(mainArray, subArray);

        // Delete maindir.txt and subdir.txt temp files
        if (deleteTempFiles) {
            fileMain.CleanUP();
            fileSub.CleanUP();
        }

        fileResult.PrintMissingFiles(cleanedArrayFinal);
        //PrintMissingFiles(cleanedArrayFinal, outputResult, resultPrintTXT);


    }
    static void PrintMissingFiles(String[] cleanedArrayFinal, PrintWriter outputResult, boolean resultPrintTXT) throws IOException {
        // Write Results in txt or console
        if (resultPrintTXT) {
            outputResult.println("Following files and [folders] are missing: ");
            outputResult.println();

            for (int i = 0; i < cleanedArrayFinal.length; i++) {
                outputResult.println(cleanedArrayFinal[i]);
            }

            outputResult.close();
        } else {
            System.out.println("Following files and [folders] are missing: ");
            System.out.println();

            for (int i = 0; i < cleanedArrayFinal.length; i++) {
                System.out.println(cleanedArrayFinal[i]);
            }

        }

    }


    static void FillFiles(File file, PrintWriter outputTXT, boolean formatting, String nameTXT, boolean commentsProcess) {
        if ((file.exists() && file.isDirectory())) {

            File[] listFiles = file.listFiles();

            // Check if files exists
            assert listFiles != null;

            RecursivePrint(listFiles, outputTXT, 0, formatting);

        } else {
            System.out.println("Paths not correct for: " + file.getName() +"Exit!");
            System.exit(1);
        }

        outputTXT.close();
        if(commentsProcess)
            System.out.println(nameTXT + " successful created and filled.");
    }

    static void RecursivePrint(File[] fileDelivered, PrintWriter output, int level, boolean formatting) {
        // for-each loop for main directory files

        for (File file : fileDelivered) {
            // enhanced for loop --> let you easy go through file

            if (formatting) {
                for (int i = 0; i < level; i++)
                    output.print("\t");
            }


            if (file.isFile())
                output.println((file.getName()));

            else if (file.isDirectory()) {
                output.println("[" + file.getName() + "]");
                // recursion for sub-directories
                RecursivePrint(Objects.requireNonNull(file.listFiles()), output, level + 1, formatting);
            }
        }
    }

}

/*
// TODO Ausgelagert!

    static int LineCount(String nameTXT) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(nameTXT)));

        int lineCount = 0;
        while (reader.readLine() != null)
            lineCount++;

        return lineCount;
    }

        static void CleanUP(File nameTXT, boolean commentsProcess) {

        // Delete of temp files
        if (nameTXT.delete()) {
            if(commentsProcess)
                System.out.println("Deleted the file: " +nameTXT.getName());
        } else {
            System.out.println("Failed to delete the file: " + nameTXT.getName());
            System.exit(1);
        }
    }

    static String[] ReadFromFiles(String nameTXT, int lineCount) {
        String[] filledArray = new String[lineCount];
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



    static String[] MissingFiles(String[] mainArray, String[] subArray) {
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
    static String[] CleanArrayUp (String[] arrayCleaner) {
        return Arrays.stream(arrayCleaner).filter(Objects::nonNull).toArray(String[]::new);
    }

 */