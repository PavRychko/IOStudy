import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class FileAnalyzerTest {
    File file = new File("story.txt");
    FileAnalyzer fileAnalyzer = new FileAnalyzer(file.getPath());

    @BeforeEach
    private void createFile() throws IOException {
        if (file.exists()) {
            deleteFile();
        }
        file.createNewFile();
        assertEquals("story.txt", file.getPath());
        assertTrue(file.exists());
        OutputStream outputStream = new FileOutputStream("story.txt");
        byte[] storyBytes = getStoryText().getBytes();
        outputStream.write(storyBytes);
        outputStream.close();
    }


    @DisplayName("how many times word is repeated in a text test")
    @Test
    public void wordsRepeatCountTest() throws IOException {

        String actual = assertThrows(FileNotFoundException.class, () -> fileAnalyzer.wordRepeatCounts("c:/jdk-17", "заяц")).getMessage();
        String expected = "path c:/jdk-17 is not File path!";
        assertEquals(expected, actual);
        assertEquals(10, fileAnalyzer.wordRepeatCounts(file.getPath(), "Заяц"));
        assertEquals(0, fileAnalyzer.wordRepeatCounts(file.getPath(), "Волк"));


    }

    @DisplayName("Get and print all sentences that have your word test")
    @Test
    public void sentencesThatContainWordTest() throws IOException {
        String[] expected = {
                "Наверное, птенец, но тень от его крыльев город закрывает, в городе ночь настаёт.",
                "Город, небось, крохотный?",
                "Через тот город заяц бежал, не перебежал.",
                "Вышел из того города, где заяц бежал, на который тень от орла упала, и пошёл куда глаза глядят.",
                "Глаза того тулупа, который из шкуры зайца вышел, в городе где ночь настаёт, когда над ним птенец пролетает верхом на хромой блохе.",
                "Чего-чего, на хромой блохе с того берега моря, которое зайцу не перелететь, орлу не перебежать, хоть море не море, а так, лужа посреди города," +
                        " где тень от блохи на зайца упала и насмерть убила, а из шкуры зайца тулуп вышел и пошёл куда глаза глядят, а тут заяц ка-ак прыгнет!"
        };
        assertEquals(expected[0], fileAnalyzer.getSentencesWithWord("story.txt","город")[0]);
        assertEquals(expected[1], fileAnalyzer.getSentencesWithWord("story.txt","город")[1]);
        assertEquals(expected[2], fileAnalyzer.getSentencesWithWord("story.txt","город")[2]);
        assertEquals(expected[3], fileAnalyzer.getSentencesWithWord("story.txt","город")[3]);
        assertEquals(expected[4], fileAnalyzer.getSentencesWithWord("story.txt","город")[4]);
        assertEquals(expected[5], fileAnalyzer.getSentencesWithWord("story.txt","город")[5]);


    }


    private String getStoryText() {
        return "Это я, добрый Э-эх, я здесь." +
                "\n" +
                "И я здесь!" +
                "\n" +
                "А ты кто такой, откуда взялся?" +
                "\n" +
                "С того берега моря." +
                "\n" +
                "На чём приехал?" +
                "\n" +
                "Оседлал хромую блоху, сел и приехал." +
                "\n" +
                "Море что, лужа?" +
                "\n" +
                "Может, и лужа, да только ту лужу орёл не перелетел." +
                "\n" +
                "Значит, орёл — птенец?" +
                "\n" +
                "Наверное, птенец, но тень от его крыльев город закрывает, в городе ночь настаёт." +
                "\n" +
                "Город, небось, крохотный?" +
                "\n" +
                "Через тот город заяц бежал, не перебежал." +
                "\n" +
                "Выходит, заяц маленький?" +
                "\n" +
                "Заяц как заяц, из его шкуры тулуп вышел." +
                "\n" +
                "Куда вышел?" +
                "\n" +
                "Вышел из того города, где заяц бежал, на который тень от орла упала, и пошёл куда глаза глядят." +
                "\n" +
                "Чьи глаза?" +
                "\n" +
                "Глаза того тулупа, который из шкуры зайца вышел, в городе где ночь настаёт, когда над ним птенец пролетает верхом на хромой блохе." +
                "\n" +
                "Чего?!" +
                "\n" +
                "Чего-чего, на хромой блохе с того берега моря, которое зайцу не перелететь, орлу не перебежать, хоть море не море, а так, лужа посреди города, где тень от блохи на зайца упала и насмерть убила, а из шкуры зайца тулуп вышел и пошёл куда глаза глядят, а тут заяц ка-ак прыгнет!" +
                "\n" +
                "Какой заяц?!" +
                "\n" +
                "Насмерть убитый, как прыгнет куда глаза глядят — аж на тот берег моря, которое ни перелететь, ни перебежать, из которого тулуп вышел, на который тень от блохи упала и зайца убила, хоть заяц — не заяц, а орёл…" +
                "\n" +
                "Какой заяц, какой орёл, какая блоха?!" +
                "\n" +
                "Так повторить? Ну, значит, та самая блоха с того берега лужи…";
    }

    @AfterEach
    private void deleteFile() {
        file.delete();
    }
}
