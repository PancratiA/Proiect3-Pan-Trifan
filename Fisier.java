import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import Test.*;
public class Fisier {
    private static Fisier instance;
    private ArrayList<Test> teste;

    private Fisier() {
        teste = new ArrayList<>();
    }

    public static Fisier getInstance() {
        if (instance == null) {
            instance = new Fisier();
        }
        return instance;
    }

    public void addTest(Test test) {
        teste.add(test);
    }

    public void saveToFile(String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (Test test : teste) {
                fileWriter.write("Test:\n");
                for (Intrebare intrebare : test.getIntrebari()) {

                    if(intrebare instanceof CheckboxQuestion){
                        fileWriter.write("Check:\n");
                        fileWriter.write (intrebare.adaugareIntrebareFisier());
                        CheckboxQuestion check = (CheckboxQuestion)intrebare;
                        fileWriter.write(check.adaugareRaspunsuri() + "\n" );

                    }
                    if(intrebare instanceof TrueFalseQuestion){
                        fileWriter.write("Check:\n");
                        fileWriter.write (intrebare.adaugareIntrebareFisier());
                        fileWriter.write( "True\nFalse\n" );



                    }
                    fileWriter.write("Corect:\n");
                    fileWriter.write (intrebare.adaugareRaspunsCorectFisier());

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Test testCurent = null;
            Intrebare currentIntrebare = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Quiz:")) {
                    testCurent = new Test();
                } else if (testCurent != null) {
                    // Parse questions and answers
                    if (line.startsWith("Intrebare:")) {
                        // Create a new Intrebare object
                       // currentIntrebare = new Intrebare(line.substring(10));
                    } else if (currentIntrebare != null) {
                        if (!line.isEmpty()) {
                            // Assuming each line contains an answer option
                          //  currentIntrebare.addAnswerOption(line);
                        } else {
                            // Empty line indicates the end of answers for the current Intrebare
                            testCurent.addIntrebare(currentIntrebare);
                            currentIntrebare = null;
                        }
                    }
                }
            }

            if (testCurent != null) {
                teste.add(testCurent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
