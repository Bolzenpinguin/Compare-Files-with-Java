import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        // set only true if you want to have a good view in the txt data
        boolean formatting = true;
        // set false if you want to keep the txt files
        boolean deleteTempFiles = true;

        PrintWriter outputMain = new PrintWriter("maindir.txt");
        PrintWriter outputSub = new PrintWriter("subdir.txt");

        String maindirpath
                = "/Users/kurtschubert/Desktop/test/testA";
        String subdirpath
                = "/Users/kurtschubert/Desktop/test/testB";

        // File object
        File maindir = new File(maindirpath);
        File subdir = new File(subdirpath);

        if ((maindir.exists() && subdir.exists()) && (maindir.isDirectory() && subdir.isDirectory())) {

            File[] arrOne = maindir.listFiles();
            File[] arrTwo = subdir.listFiles();

            // Check if files exists
            assert arrTwo != null;
            assert arrOne != null;

            RecursivePrintMain(arrOne, outputMain, 0, formatting);
            RecursivePrintSub(arrTwo, outputSub, 0, formatting);

        } else {
            System.out.println("Paths not correct! Exit!");
            System.exit(1);
        }

        outputMain.close();
        outputSub.close();
        System.out.println("maindir.txt and subdir.txt successful created.");

        // maindir.txt
        BufferedReader brMain = new BufferedReader(new InputStreamReader(new FileInputStream("maindir.txt")));

        //get lines
        int lineCountMain = 0;
        while (brMain.readLine() != null)
            lineCountMain++;

        String[] mainArray = new String[lineCountMain];
        int place = 0;

        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("maindir.txt"));
            String line = reader.readLine();

            while (line != null) {
                mainArray[place] = line;
                place++;
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // subdir.txt  *****************************************
        BufferedReader brSub = new BufferedReader(new InputStreamReader(new FileInputStream("subdir.txt")));

        //get lines
        int lineCountSub = 0;
        while (brSub.readLine() != null)
            lineCountSub++;

        String[] subArray = new String[lineCountSub];
        place = 0;

        try {
            reader = new BufferedReader(new FileReader("subdir.txt"));
            String line = reader.readLine();

            while (line != null) {
                subArray[place] = line;
                place++;
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] result = new String[lineCountMain];
        boolean contains;

        for (int i = 0; i < mainArray.length; i++) {
            for (int j = 0; j < subArray.length; j++) {
                contains = Arrays.asList(mainArray[i]).contains(subArray[j]);

                if (contains) {
                    mainArray[i] = null;
                }
            }
        }

        // Array cleaner --> null delete
        String[] cleandArrayFinal = Arrays.stream(mainArray).filter(Objects::nonNull).toArray(String[]::new);

        for (int i = 0; i < cleandArrayFinal.length; i++) {
            System.out.println(cleandArrayFinal[i]);
        }

        // Delete maindir.txt and subdir.txt
        File mainTXT = new File("maindir.txt");
        File subTXT = new File("subdir.txt");

        if (mainTXT.delete()) {
            System.out.println("Deleted the file: " +mainTXT.getName());
        } else {
            System.out.println("Failed to delete the file: " + mainTXT.getName());
            System.exit(2);
        }

        if (subTXT.delete()) {
            System.out.println("Deleted the file: " + subTXT.getName());
        } else {
            System.out.println("Failed to delete the file: " + subTXT.getName());
            System.exit(3);
        }
    }

    static void RecursivePrintMain(File[] arrOne, PrintWriter outputMain, int level, boolean formatting)
    {
        // for-each loop for main directory files
        for (File fone : arrOne) {

            if (formatting) {
                for (int i = 0; i < level; i++)
                    outputMain.print("\t");
            }


            if (fone.isFile())
                outputMain.println((fone.getName()));

            else if (fone.isDirectory()) {
                outputMain.println("[" + fone.getName() + "]");
                // recursion for sub-directories
                RecursivePrintMain(Objects.requireNonNull(fone.listFiles()), outputMain, level + 1, formatting);
            }
        }


    }

    static void RecursivePrintSub(File[] arrTwo, PrintWriter outputSub, int level, boolean formatting) {
        // for-each loop for subdirectory files
        for (File ftwo : arrTwo) {

            if (formatting) {
                for (int i = 0; i < level; i++)
                    outputSub.print("\t");
            }


            if (ftwo.isFile())
                outputSub.println((ftwo.getName()));

            else if (ftwo.isDirectory()) {
                outputSub.println("[" + ftwo.getName() + "]");
                // recursion for sub-directories
                RecursivePrintSub(Objects.requireNonNull(ftwo.listFiles()), outputSub, level + 1, formatting);
            }
        }
    }

}