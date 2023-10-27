
import Utilizator.User;
import Utilizator.UserElev;
import Utilizator.UserProfesor;
import Test.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlatformaTeste {
    private static Fisier fisier = Fisier.getInstance();
    public static void main(String[] args) {
        fisier.loadFromFile("quizzes.txt");
        Test test = new Test();
        fisier.addTest(test);
        fisier.saveToFile("quizzes.txt");
        test.addIntrebare(new TrueFalseQuestion("salut","true"));

        JFrame frame = new JFrame("Inceput");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center-align components with spacing
        Font customFont = new Font("Serif", Font.PLAIN, 20);

        JLabel label = new JLabel("Bine ai venit!");
        label.setFont(new Font(customFont.getName(), Font.BOLD, 24)); // Set a bigger font
        frame.add(label);

        JLabel label2 = new JLabel("Esti un student sau un profesor?");
        label2.setFont(customFont);
        frame.add(label2);

        JButton teacherButton = new JButton("Profesor");
        teacherButton.setFont(customFont);
        JButton studentButton = new JButton("Student");
        studentButton.setFont(customFont);
        frame.add(teacherButton);
        frame.add(studentButton);

        teacherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String teacherInputID = JOptionPane.showInputDialog("Introduceti ID-ul de profesor:");
                if (teacherInputID != null) {
                    UserProfesor profesor = new UserProfesor(teacherInputID);
                    if (profesor.isValidID()) {


                        addTestPanel();

                    } else {
                        JOptionPane.showMessageDialog(frame, "ID Invalid");
                    }
                }
            }
        });

        studentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentInputID = JOptionPane.showInputDialog("Introduceti ID-ul de student:");
                if (studentInputID != null) {
                    UserElev elev = new UserElev(studentInputID);
                    if (elev.isValidID()) {
                        takeTestPanel();
                    } else {
                        JOptionPane.showMessageDialog(frame, "ID Invalid");
                    }
                }
            }
        });

        frame.setPreferredSize(new Dimension(400, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void takeTestPanel(){


    }

    public static void addTestPanel(){
        Test test = new Test();
        //test.addIntrebare(new TrueFalseQuestion("salut","true"));

        fisier.addTest(test);
        fisier.saveToFile("quizzes.txt");


    }




}

