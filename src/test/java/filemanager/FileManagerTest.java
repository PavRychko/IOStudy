package filemanager;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {
    FileManager fileManager = new FileManager();

//    @BeforeAll
//    private static void createResources() throws IOException {
//        File file = new File("c://FileManagerTest/dir1/dir2/dir3");
//////        assertTrue(file.mkdirs());
//////        file = new File("c://FileManagerTest/dir1/dir2/dir3/TestFileFromDir3");
//////        assertTrue(file.createNewFile());
//////        file = new File("c://FileManagerTest/dir1/dir2/HTMLTestFileFromDir2.html");
//////        assertTrue(file.createNewFile());
//////        file = new File("c://FileManagerTest/dir1/dir2/TestFileFromDir2.txt");
//////        assertTrue(file.createNewFile());
//            file = new File("c://FileManagerTest/dir1/dir2.2");
//        assertTrue(file.mkdir());
//    }

    @Test
    public void allMethodsThrows(){
        String actualMessage = assertThrows(IllegalArgumentException.class, () -> fileManager.countFiles(null)).getMessage();
        String expectedMessage = "path is  null! Can`t work with it!";
        assertEquals(expectedMessage, actualMessage);
        actualMessage = assertThrows(IllegalArgumentException.class, () -> fileManager.countDirs(null)).getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("Count files returns correct count with no files in a folder")
    @Test
    public void countFilesTest1() {
        String path = "c://FileManagerTest/dir1/";
        assertEquals(0, fileManager.countFiles(path));
    }

    @DisplayName("Count files returns correct count with files in a folder, and no files in parent folders")
    @Test
    public void countFilesTest2() {
        String path = "c://FileManagerTest/dir1/dir2";
        assertEquals(2, fileManager.countFiles(path));
    }


    @DisplayName("Count files returns correct count with a file in a folder, and two files in parent folders")
    @Test
    public void countFilesTest3() {
        String path = "c://FileManagerTest/dir1/dir2/dir3/";
        assertEquals(3, fileManager.countFiles(path));
    }

    @DisplayName("Count files throws exception if path is not a directory")
    @Test
    public void countFilesTest4() {
        String path = "c://FileManagerTest/dir1/dir2/dir3/TestFileFromDir3.txt";
        String actualMessage = assertThrows(IllegalStateException.class, () -> fileManager.countFiles(path)).getMessage();
        String expectedMessage = "path " + path + " is not a directory path!";
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("countDirs returns correct count with 2 dirs, and one dir in parent dir and no files")
    @Test
    public void countDirsTest1() {
        String path = "c://FileManagerTest/dir1/";
        assertEquals(3, fileManager.countDirs(path));
    }

    @DisplayName("countDirs returns correct count with 2 files in a dir, and more than 1 dirs in parent dirs")
    @Test
    public void countDirsTest2() {
        String path = "c://FileManagerTest/dir1/dir2/";
        assertEquals(4, fileManager.countDirs(path));
    }

    @DisplayName("countDirs returns correct count with one file no dirs, and few parent dirs")
    @Test
    public void countDirsTest3() {
        String path = "c://FileManagerTest/dir1/dir2/dir3/";
        assertEquals(4, fileManager.countDirs(path));
    }

    @DisplayName("countDirs throws exception if path is not a directory")
    @Test
    public void countDirsTest4() {
        String path = "c://FileManagerTest/dir1/dir2/dir3/TestFileFromDir3.txt";
        String actualMessage = assertThrows(IllegalStateException.class, () -> fileManager.countDirs(path)).getMessage();
        String expectedMessage = "path " + path + " is not a directory path!";
        assertEquals(expectedMessage, actualMessage);
    }
}
