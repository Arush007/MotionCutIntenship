import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    // Components
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton addButton, subButton, mulButton, divButton, eqButton, clrButton;
    private JButton decimalButton, negButton;

    // Variables
    private double num1, num2, result;
    private char operator;

    public Calculator() {
        // Frame setup
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new GridLayout(5, 4));

        // Text field
        textField = new JTextField();
        add(textField);

        // Number buttons
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            add(numberButtons[i]);
        }

        // Operator buttons
        operatorButtons = new JButton[4];
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");

        operatorButtons[0] = addButton;
        operatorButtons[1] = subButton;
        operatorButtons[2] = mulButton;
        operatorButtons[3] = divButton;

        for (JButton button : operatorButtons) {
            button.addActionListener(this);
            add(button);
        }

        // Other buttons
        eqButton = new JButton("=");
        clrButton = new JButton("C");
        decimalButton = new JButton(".");
        negButton = new JButton("+/-");

        eqButton.addActionListener(this);
        clrButton.addActionListener(this);
        decimalButton.addActionListener(this);
        negButton.addActionListener(this);

        add(eqButton);
        add(clrButton);
        add(decimalButton);
        add(negButton);

        // Display the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // Number buttons
        for (int i = 0; i < 10; i++) {
            if (source == numberButtons[i]) {
                textField.setText(textField.getText() + i);
            }
        }

        // Operator buttons
        if (source == addButton || source == subButton || source == mulButton || source == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = e.getActionCommand().charAt(0);
            textField.setText("");
        }

        // Equals button
        if (source == eqButton) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textField.setText("Error");
                        return;
                    }
                    break;
            }

            textField.setText(String.valueOf(result));
        }

        // Clear button
        if (source == clrButton) {
            textField.setText("");
        }

        // Decimal button
        if (source == decimalButton) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText() + ".");
            }
        }

        // Negation button
        if (source == negButton) {
            double currentValue = Double.parseDouble(textField.getText());
            textField.setText(String.valueOf(-currentValue));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}

