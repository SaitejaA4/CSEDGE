import java.awt.*;
import java.awt.event.*;

public class AWTCalculator extends Frame implements ActionListener {
    TextField num1Field, num2Field, resultField;
    Button addButton, subButton, mulButton, divButton;
    Label num1Label, num2Label, resultLabel;

    public AWTCalculator() {
        setTitle("AWT Calculator");

        setLayout(new GridLayout(5, 2, 5, 5));

        num1Label = new Label("Number 1:");
        num2Label = new Label("Number 2:");
        resultLabel = new Label("Result:");

        num1Field = new TextField();
        num2Field = new TextField();
        resultField = new TextField();
        resultField.setEditable(false); 

        addButton = new Button("Add");
        subButton = new Button("Subtract");
        mulButton = new Button("Multiply");
        divButton = new Button("Divide");

        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);

        add(num1Label);
        add(num1Field);
        add(num2Label);
        add(num2Field);
        add(addButton);
        add(subButton);
        add(mulButton);
        add(divButton);
        add(resultLabel);
        add(resultField);

        setSize(400, 300);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            double result = 0;

            if (e.getSource() == addButton) {
                result = num1 + num2;
            } else if (e.getSource() == subButton) {
                result = num1 - num2;
            } else if (e.getSource() == mulButton) {
                result = num1 * num2;
            } else if (e.getSource() == divButton) {
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    resultField.setText("Error: Divide by zero");
                    return;
                }
            }

            resultField.setText(String.valueOf(result));
        } catch (NumberFormatException ex) {
            resultField.setText("Error: Invalid input");
        }
    }

    public static void main(String[] args) {
        new AWTCalculator();
    }
}
