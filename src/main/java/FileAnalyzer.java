import java.io.*;
import java.util.Arrays;

public class FileAnalyzer {
    int repeats;
    String[] sentences;


    public int wordRepeatCounts(String path, String word) throws IOException {
        File file = new File(path);
        if (!file.isFile()) {
            throw new FileNotFoundException("path " + path + " is not File path!");
        }
        InputStream inputStream = new FileInputStream(file);
        String fileText = new String(inputStream.readAllBytes());
        inputStream.close();
        char[] textInChars = fileText.toLowerCase().toCharArray();
        char[] wordInChars = word.toLowerCase().toCharArray();
        return this.repeats = containsCount(wordInChars, textInChars);
    }


    public String[] getSentencesWithWord(String word) {
        return null;
    }


    private int containsCount(char[] word, char[] text) {
        int count = 0;
        int sequence = 0;
        for (int i = 0; i < text.length; i++) {
            if (text[i] == word[0]) {
                sequence = 0;
                for (int j = 0; j < word.length-1; j++) {
                    if (i + j > text.length) {
                        break;
                    } else if (text[i+j] == word[j] && text[i+j+1] == word[j+1] ) {
                        sequence++;
                    }
                }
                if (sequence == word.length-1) {
                    count++;
                }

            }
        }
        return count;
    }
}



