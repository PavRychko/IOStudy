package filemanager;

import java.io.File;
import java.util.Objects;

public class FileManager {

    public static int countDirs(String path){
        File file =  pathCheck(path);
        int dirsCount = 0;
        int parentDirsCount = parentDirCount(file);
        for (int i = 0; i < parentDirsCount; i++) {
            File[] filesInDir = file.listFiles();
            dirsCount = dirsCount + filesAndDirsCount(filesInDir, true, false);
            file = file.getParentFile();
        }
        return dirsCount;
    }


    public static int countFiles(String path) {
        File file = pathCheck(path);
        int filesInDirCount = 0;
        int parentDirsCount = parentDirCount(file);
        for (int i = 0; i <parentDirsCount; i++) {
            File[] filesInDir = file.listFiles();
            filesInDirCount = filesInDirCount + filesAndDirsCount(filesInDir, false, true);
            file = file.getParentFile();
        }

        return filesInDirCount;
    }

    private static int parentDirCount(File file){
        if(Objects.equals(file.getParent(), null)){
            return 0;
        }
        int temp = 0;
        int dirCounter = 0;
        do{
            dirCounter++;
            file =file.getParentFile();
             temp = parentDirCount(file);
        } while(temp != 0);

        return dirCounter;
    }

    private static int filesAndDirsCount(File[] listOfFilesInDir, boolean dirCountFlag, boolean fileCountFlag) {
        int fileCounter = 0;
        int dirCounter = 0;
        for (int i = 0; i < listOfFilesInDir.length; i++) {
            if (listOfFilesInDir[i].isDirectory()) {
                dirCounter++;
            } else {
                fileCounter++;
            }
        }
        if (dirCountFlag && fileCountFlag) {
            return fileCounter + dirCounter;
        } else if (dirCountFlag) {
            return dirCounter;
        } else if (fileCountFlag) {
            return fileCounter;
        }
        return 0;
    }

    private static File pathCheck(String path){
        if(path == null){
            throw new IllegalArgumentException("path is  null! Can`t work with it!");
        }
        File file = new File(path);
        if (!file.isDirectory()) {
            throw new IllegalStateException("path " + path + " is not a directory path!");
        }
        return file;
    }
}
