package fileanalyzer;

import java.io.*;
import java.util.ArrayList;


public class FileAnalyzer {
    File file;
    String path;

    public FileAnalyzer(String pathToFile) {
        this.path = pathToFile;
        this.file = new File(pathToFile);
        try {
            checkIsFileExist(file);
        } catch (IOException e) {
            System.out.print("path " + file.getPath() + " is not File path!");
            file = null;
        }
    }


    public static void main(String[] args) throws IOException {
        String path = args[0];
        String word = args[1];
        FileAnalyzer fileAnalyzer = new FileAnalyzer(path);
        System.out.println("Word " + word + " repeated " + fileAnalyzer.wordRepeatCounts(word) + " times in a text");
        for (String s : fileAnalyzer.getSentencesWithWord(word)) {
            System.out.println(s);
        }
    }

    public int wordRepeatCounts(String word) throws IOException {
        String[] fileText = readFile(file = new File(path));
        String[] words;
        int counter = 0;
        for (int i = 0; i < fileText.length; i++) {
            if (fileText[i].toLowerCase().contains(word.toLowerCase())) {
                words = fileText[i].split(" ");
                for (int i1 = 0; i1 < words.length; i1++) {
                    if (words[i1].toLowerCase().contains(word.toLowerCase())) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }


    public String[] getSentencesWithWord(String word) throws IOException {
        String[] fileText = readFile(file = new File(path));
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < fileText.length; i++) {
            if (fileText[i].toLowerCase().contains(word.toLowerCase())) {
                arrayList.add(fileText[i]);
            }
        }
        return arrayList.toArray(String[]::new);
    }


    private String[] readFile(File file) throws IOException {
        checkIsFileExist(file);
        InputStream inputStream = new FileInputStream(file);
        ArrayList<String> sentence = new ArrayList<>();
        byte[] buffer = new byte[1024];
        for (int b = 0; b != -1; b = inputStream.read()) {
            for (int i = 0; i < 1024; i++) {
                buffer[i] = (byte) b;
                if ((b == 33 || b == 63 || b == 46)) {
                    sentence.add(new String(buffer, 1, i, "UTF-8"));
                    i = 0;
                }
                b = inputStream.read();
            }
        }
        String[] result = sentence.toArray(String[]::new);
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].replace("\n", "");
        }
        return result;
    }


    private void checkIsFileExist(File file) throws IOException {
        if (!file.isFile()) {
            throw new FileNotFoundException("path " + path + " is not File path!");
        }
    }
}



