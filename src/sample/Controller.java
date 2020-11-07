package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Controller {
    @FXML
    private ToggleGroup answers;

    @FXML
    private Text question_text;

    @FXML
    private RadioButton radio_btn_1;

    @FXML
    private RadioButton radio_btn_2;

    @FXML
    private RadioButton radio_btn_3;

    @FXML
    private RadioButton radio_btn_4;

    @FXML
    private Button answerBtn;
    private Questions[] questions = new Questions[] {
            new Questions("What of the following is the default value of a local variable?", new String[] {"null", "0", "Depends upon the type of variable", "Not assigned"}),
            new Questions("What is composition?", new String[] {"Composition is a data structure", "Composition is a way to create an object", "None of the above", "Holding the reference of the other class within some other class is known as composition"}),
            new Questions("What is polymorphism?", new String[] {"Polymorphism is a technique to define different objects of same type.", "Polymorphism is a technique to define different methods of same type", "None of the above", "Polymorphism is the ability of an object to take on many forms"}),
            new Questions("What is local variable?", new String[] {"Static variables defined outside methods, constructors or blocks are called local variables", "Variables defined outside methods, constructors or blocks are called local variables", "None of the above", "Variables defined inside methods, constructors or blocks are called local variables"}),
            new Questions("Which method can be used to return a string in upper case letters?", new String[] {"upperCase()","touppercase()", "tuc()", "toUpperCase()"}),
            new Questions("Which keyword is used to import a package from the Java API library?", new String[] {"lib", "getlib", "package", "import"})
    };
    private int nowQuestion = 0, correctAnswers;
    private String nowCorrectAnswer;

    @FXML
    public void initialize() {
        nowCorrectAnswer = questions[nowQuestion].correctAnswer();
        answerBtn.setOnAction(e -> {
            RadioButton selectedRadioButton = (RadioButton) answers.getSelectedToggle();
            if(selectedRadioButton != null) {
                String toogleGroupValue = selectedRadioButton.getText();
                if(toogleGroupValue.equals(nowCorrectAnswer)) {
                    System.out.println("Верный ответ");
                    correctAnswers++;
                } else
                    System.out.println("Не верный ответ");
                if(nowQuestion + 1 == questions.length) {
                    radio_btn_1.setVisible(false);
                    radio_btn_2.setVisible(false);
                    radio_btn_3.setVisible(false);
                    radio_btn_4.setVisible(false);
                    answerBtn.setVisible(false);

                    question_text.setText("You got " + correctAnswers + " of " + questions.length + " points!");
                } else {
                    nowQuestion++;
                    nowCorrectAnswer = questions[nowQuestion].correctAnswer();
                    question_text.setText(questions[nowQuestion].getQuestion());
                    String[] answers = questions[nowQuestion].getAnswers();


                    List<String> intList = Arrays.asList(answers);

                    Collections.shuffle(intList);

                    radio_btn_1.setText(intList.get(0));
                    radio_btn_2.setText(intList.get(1));
                    radio_btn_3.setText(intList.get(2));
                    radio_btn_4.setText(intList.get(3));

                    selectedRadioButton.setSelected(false);
                }
            }
        });
    }
}