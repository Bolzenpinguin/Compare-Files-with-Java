import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// TODO: Print txt with missing files and folders --> PrintMissingFiles()

public class Main {
    public static void main(String[] args) throws IOException {

        // set only true if you want to have a good view in the txt data
        boolean formatting = true;
        // set false if you want to keep the txt files
        boolean deleteTempFiles = true;
        // set true for comments in process
        boolean commentsProcess = false;

        String nameMainTXT = "maindir.txt";
        String nameSubTXT = "subdir.txt";

        PrintWriter outputMain = new PrintWriter(nameMainTXT);
        PrintWriter outputSub = new PrintWriter(nameSubTXT);

        String maindirpath
                = "/Users/kurtschubert/Desktop/test/testA";
        String subdirpath
                = "/Users/kurtschubert/Desktop/test/testB";

        // File object
        File maindir = new File(maindirpath);
        File subdir = new File(subdirpath);


        FillFiles(maindir, outputMain, formatting, nameMainTXT, commentsProcess);
        FillFiles(subdir, outputSub, formatting, nameSubTXT, commentsProcess);

        // get Lines from mainTXT
        int lineCountMain = LineCount(nameMainTXT);
        int lineCountSub = LineCount(nameSubTXT);

        // maindir.txt  *****************************************
        String[] mainArray;

        mainArray = ReadFromFiles(nameMainTXT, lineCountMain);

        // subdir.txt  *****************************************
        String[] subArray;

        subArray = ReadFromFiles(nameSubTXT, lineCountSub);

        String[] cleandArrayFinal = MissingFiles( mainArray, subArray);

        // Delete maindir.txt and subdir.txt temp files
        if (deleteTempFiles) {
            CleanUP(new File(nameMainTXT), commentsProcess);
            CleanUP(new File(nameSubTXT),commentsProcess);
        }

        PrintMissingFiles(cleandArrayFinal);

    }
    static void PrintMissingFiles(String[] cleanedArrayFinal) {
        System.out.println("Following files and [folders] are missing: ");
        for (int i = 0; i < cleanedArrayFinal.length; i++) {
            System.out.println(cleanedArrayFinal[i]);
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
    static int LineCount(String nameTXT) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nameTXT)));

        int lineCount = 0;
        while (br.readLine() != null)
            lineCount++;

        return lineCount;
    }
    static String[] ReadFromFiles(String nameTXT, int lineCount) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nameTXT)));

        String[] filledArray = new String[lineCount];
        int place = 0;

        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(nameTXT));
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
    static void RecursivePrint(File[] file, PrintWriter outputMain, int level, boolean formatting)
    {
        // for-each loop for main directory files

        for (File fone : file) {
        //enhanced for loop --> let you easy go through file

            if (formatting) {
                for (int i = 0; i < level; i++)
                    outputMain.print("\t");
            }


            if (fone.isFile())
                outputMain.println((fone.getName()));

            else if (fone.isDirectory()) {
                outputMain.println("[" + fone.getName() + "]");
                // recursion for sub-directories
                RecursivePrint(Objects.requireNonNull(fone.listFiles()), outputMain, level + 1, formatting);
            }
        }


    }
}