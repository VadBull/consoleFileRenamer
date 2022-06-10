import java.io.File;
import java.util.Scanner;
import java.util.TreeSet;

public class FileRenamer {
    public static void main(String[] args) {

        String path = consoleInput();
        String slash = "\\" + "\\";
        path.replace("\\", slash);
        path += "\\" + "\\";
        File dir = new File(path);
        int degreeCountWanted = degreeCountWantedConsoleInput();

        renamer(dir,degreeCountWanted, path);
    }
    public static String consoleInput() {
        System.out.println("Введите путь к директории с файлами для переименования");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public static int degreeCountWantedConsoleInput () {
        System.out.println("Введите желаемое количество знаков в наименовании файла");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static void renamer (File dir, int degreeCountWanted, String pathname) {
        TreeSet<String> fileNames = new TreeSet();

        if (dir.isDirectory()) {
            for (File item : dir.listFiles()) {
                if (!item.isDirectory()) {
                    fileNames.add(item.getName());
                }
            }

        }

        int filenameCounter = 1;
        int zerosInsert = 0;
        StringBuilder path = new StringBuilder(pathname);
        StringBuilder renamer = new StringBuilder();
        StringBuilder newFilename = new StringBuilder("");
        int regularExt = 4; // File extension char count
        StringBuilder ext = new StringBuilder();

        for(String fileName : fileNames) {
            newFilename.setLength(0);
            newFilename.append(filenameCounter);
            if (newFilename.length() < degreeCountWanted) {
                zerosInsert = degreeCountWanted - newFilename.length();
                for(int i = 0; i < zerosInsert; i++) {
                    newFilename.insert(0,"0");
                }
            }
            filenameCounter++;

            renamer.setLength(0);
            renamer.append(path);
            renamer.append(fileName);
            ext.setLength(0);
            ext.append(renamer.toString().substring(renamer.indexOf("."), renamer.length()));

            File oldfile = new File (renamer.toString());

            renamer.setLength(0);
            renamer.append(path);
            renamer.append(newFilename);
            renamer.append(ext);
            File newFile = new File(renamer.toString());

            oldfile.renameTo(newFile);
        }
    }
}
