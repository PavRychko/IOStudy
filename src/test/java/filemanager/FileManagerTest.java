package filemanager;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {
    FileManager fileManager = new FileManager();

    @BeforeAll
    private static void createResources() throws IOException {
        File file = new File("c://FileManagerTest/dir1/dir2/dir3");
        file.mkdirs();
        file = new File("c://FileManagerTest/dir1/dir2/dir3/TestFileFromDir3");
        file.createNewFile();
        file = new File("c://FileManagerTest/dir1/dir2/HTMLTestFileFromDir2.html");
        file.createNewFile();
        file = new File("c://FileManagerTest/dir1/dir2/TestFileFromDir2.txt");
        file.createNewFile();
        file = new File("c://FileManagerTest/dir1/dir2.2");
        file.mkdir();
    }

    @Test
    public void allMethodsThrows() {
        String actualMessage = assertThrows(IllegalArgumentException.class, () -> fileManager.countFiles(null)).getMessage();
        String expectedMessage = "path is  null! Can`t work with it!";
        assertEquals(expectedMessage, actualMessage);
        actualMessage = assertThrows(IllegalArgumentException.class, () -> fileManager.countDirs(null)).getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("Count files returns correct count with no files in a folder test")
    @Test
    public void countFilesTest1() {
        String path = "c://FileManagerTest/dir1/";
        assertEquals(0, fileManager.countFiles(path));
    }

    @DisplayName("Count files returns correct count with files in a folder, and no files in parent folders test")
    @Test
    public void countFilesTest2() {
        String path = "c://FileManagerTest/dir1/dir2";
        assertEquals(2, fileManager.countFiles(path));
    }


    @DisplayName("Count files returns correct count with a file in a folder, and two files in parent folders test")
    @Test
    public void countFilesTest3() {
        String path = "c://FileManagerTest/dir1/dir2/dir3/";
        assertEquals(3, fileManager.countFiles(path));
    }

    @DisplayName("Count files throws exception if path is not a directory test")
    @Test
    public void countFilesTest4() {
        String path = "c://FileManagerTest/dir1/dir2/dir3/TestFileFromDir3.txt";
        String actualMessage = assertThrows(IllegalStateException.class, () -> fileManager.countFiles(path)).getMessage();
        String expectedMessage = "path " + path + " is not a directory path!";
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("countDirs returns correct count with 2 dirs, and one dir in parent dir and no files test")
    @Test
    public void countDirsTest1() {
        String path = "c://FileManagerTest/dir1/";
        assertEquals(3, fileManager.countDirs(path));
    }

    @DisplayName("countDirs returns correct count with 2 files in a dir, and more than 1 dirs in parent dirs test")
    @Test
    public void countDirsTest2() {
        String path = "c://FileManagerTest/dir1/dir2/";
        assertEquals(4, fileManager.countDirs(path));
    }

    @DisplayName("countDirs returns correct count with one file no dirs, and few parent dirs test")
    @Test
    public void countDirsTest3() {
        String path = "c://FileManagerTest/dir1/dir2/dir3/";
        assertEquals(4, fileManager.countDirs(path));
    }

    @DisplayName("countDirs throws exception if path is not a directory test")
    @Test
    public void countDirsTest4() {
        String path = "c://FileManagerTest/dir1/dir2/dir3/TestFileFromDir3.txt";
        String actualMessage = assertThrows(IllegalStateException.class, () -> fileManager.countDirs(path)).getMessage();
        String expectedMessage = "path " + path + " is not a directory path!";
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("ensures that copied file has same length, so files is equals test")
    @Test
    public void copyTest() throws IOException {
        String from = "";
        String to = "";
        File fromFile = new File(from);
        File toFile = new File(to);
        assertTrue(fromFile.createNewFile());
        assertTrue(fromFile.exists());
        assertFalse(toFile.exists());
        fileManager.copy(from, to);
        assertTrue(toFile.exists());
        assertEquals(fromFile.length(), toFile.length());
    }

    @DisplayName("if dis is copied, it`s content also copied test")
    @Test
    public void copyTest2() {
        String from = "";
        String to = "";
        File fromFile = new File(from);
        File toFile = new File(to);
        File[] fromFileContent = fromFile.listFiles();
        File[] toFileContent = toFile.listFiles();
        assertNull(toFileContent);
        assertNotEquals(fromFileContent.length, toFileContent);
        fileManager.copy(from, to);
        assertNotNull(toFileContent);
        assertEquals(fromFileContent.length, toFileContent.length);
        assertEquals(fromFileContent[0], toFileContent[0]);
        assertEquals(fromFileContent[1], toFileContent[1]);
        assertEquals(fromFileContent[2], toFileContent[2]);
    }


    @DisplayName("Moved file has same length and old file is deleted")
    @Test
    public void moveTest(){
        String from = "";
        String to = "";
        File fromFile = new File(from);
        File toFile = new File(to);
        assertTrue(fromFile.exists());
        assertFalse(toFile.exists());
        long fileLength = fromFile.length();
        fileManager.move(from, to);
        assertTrue(toFile.exists());
        assertEquals(fileLength, toFile.length());
        assertFalse(fromFile.exists());
    }

    @DisplayName("if dis is moved, it`s content also moved test")
    @Test
    public void moveTest2() {
        String from = "";
        String to = "";
        File fromFile = new File(from);
        File toFile = new File(to);
        File[] fromFileContent = fromFile.listFiles();
        File[] toFileContent = toFile.listFiles();
        assertNull(toFileContent);
        assertNotEquals(fromFileContent.length, toFileContent);
        fileManager.move(from, to);
        assertNotNull(toFileContent);
        assertEquals(fromFileContent[0], toFileContent[0]);
        assertEquals(fromFileContent[1], toFileContent[1]);
        assertEquals(fromFileContent[2], toFileContent[2]);
    }

    @DisplayName("copy and move throws IllegalArgumentException if 'from' or 'to' is null test")
    @Test
    public void nullTest() {
        String expected = "you can`t copy from, or to null!";
        String actual = assertThrows(IllegalArgumentException.class, () -> fileManager.copy(null, "")).getMessage();
        assertEquals(expected, actual);
        actual = assertThrows(IllegalArgumentException.class, () -> fileManager.copy("", null)).getMessage();
        assertEquals(expected, actual);
        actual = assertThrows(IllegalArgumentException.class, () -> fileManager.copy(null, null)).getMessage();
        assertEquals(expected, actual);
        actual = assertThrows(IllegalArgumentException.class, () -> fileManager.move(null, "")).getMessage();
        assertEquals(expected, actual);
        actual = assertThrows(IllegalArgumentException.class, () -> fileManager.move("", null)).getMessage();
        assertEquals(expected, actual);
        actual = assertThrows(IllegalArgumentException.class, () -> fileManager.move(null, null)).getMessage();
        assertEquals(expected, actual);
    }

    @AfterAll
    private static void deleteResources(){
        File file = new File("c://FileManagerTest/dir1/dir2/dir3");
        file.delete();
        file = new File("c://FileManagerTest/dir1/dir2/dir3/TestFileFromDir3");
        file.delete();
        file = new File("c://FileManagerTest/dir1/dir2/HTMLTestFileFromDir2.html");
        file.delete();
        file = new File("c://FileManagerTest/dir1/dir2/TestFileFromDir2.txt");
        file.delete();
        file = new File("c://FileManagerTest/dir1/dir2.2");
        file.delete();
    }
}
