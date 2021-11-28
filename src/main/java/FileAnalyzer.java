import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class FileAnalyzer {
    File file;
    int repeats;
    String[] sentences;

    FileAnalyzer(String pathToFile) {
        this.file = new File(pathToFile);
        try {
            checkIsFileExist(file, pathToFile);
        } catch (IOException e) {
            System.out.print("path " + file.getPath() + " is not File path!");
            file = null;
        }
    }

    public static void main(String[] args) throws IOException {

    }


    public int wordRepeatCounts(String path, String word) throws IOException {
        String fileText = readFile(file = new File(path), path);
        if (isContains(fileText, word)) {
            char[] textInChars = fileText.toLowerCase().toCharArray();
            char[] wordInChars = word.toLowerCase().toCharArray();
            return this.repeats = containsCount(wordInChars, textInChars);
        }
        return 0;
    }


    public String[] getSentencesWithWord(String path, String word) throws IOException {
        String fileText = readFile(file = new File(path), path);
        if (isContains(fileText, word)) {
          String[] sentences = fileText.split(",!");
            ArrayList<String> arrayList = new ArrayList<>();
            for (int i = 0; i < sentences.length; i++) {
                if(sentences[i].contains(word)){
                    arrayList.add(sentences[i]);
                }
            }
            return arrayList.toArray(String[]::new);
        }

        return null;
    }


    private int containsCount(char[] word, char[] text) {
        int count = 0;
        int sequence = 0;
        for (int i = 0; i < text.length; i++) {
            if (text[i] == word[0]) {
                sequence = 0;
                for (int j = 0; j < word.length - 1; j++) {
                    if (i + j > text.length) {
                        break;
                    } else if (text[i + j] == word[j] && text[i + j + 1] == word[j + 1]) {
                        sequence++;
                    }
                }
                if (sequence == word.length - 1) {
                    count++;
                }

            }
        }
        return count;
    }

    private boolean isContains(String text, String word) {
        return text.toLowerCase().contains(word.toLowerCase());
    }

    private String readFile(File file, String path) throws IOException {
        checkIsFileExist(file, path);
        InputStream inputStream = new FileInputStream(file);
        StringBuilder stringBuilder = new StringBuilder();
        String sentence;
        byte[] buffer = new byte[1024];
        int b;
        while ((b =inputStream.read()) != -1){
            for (int i = 0; i < 1024; i++) {
                b = inputStream.read();
                buffer[i] = (byte)b;
                if ((b == 33 || b == 63 || b == 46)) {
                    sentence = new String(buffer, 0, i, "UTF-8");
                    stringBuilder.append(sentence);
                    stringBuilder.append(",!"); // problem!
                    i = 0;
                }
            }
        }
        inputStream.close();
        return stringBuilder.toString();
    }


    private void checkIsFileExist(File file, String path) throws IOException {
        if (!file.isFile()) {
            throw new FileNotFoundException("path " + path + " is not File path!");
        }
    }
}



