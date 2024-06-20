import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class RegistrationManager extends Frame implements ActionListener {
    // GUI Components
    Label nameLabel, genderLabel, ageLabel, courseLabel, messageLabel;
    TextField nameField, ageField;
    CheckboxGroup genderGroup;
    Checkbox male, female;
    Choice courseChoice;
    Button submitButton, displayButton, saveButton;
    TextArea displayArea;

    // Data storage
    StringBuilder userData;

    public RegistrationManager() {
        setTitle("Registration Manager");

        userData = new StringBuilder();

        setLayout(new GridLayout(8, 2, 5, 5));

        nameLabel = new Label("Name:");
        genderLabel = new Label("Gender:");
        ageLabel = new Label("Age:");
        courseLabel = new Label("Course:");
        messageLabel = new Label();

        nameField = new TextField();
        ageField = new TextField();

        genderGroup = new CheckboxGroup();
        male = new Checkbox("Male", genderGroup, false);
        female = new Checkbox("Female", genderGroup, false);

        courseChoice = new Choice();
        courseChoice.add("Computer Science");
        courseChoice.add("Information Technology");
        courseChoice.add("Machine Learning");
        courseChoice.add("Data Structures");

        
        submitButton = new Button("Submit");
        displayButton = new Button("Display");
        saveButton = new Button("Save");

        displayArea = new TextArea();
        displayArea.setEditable(false);

        submitButton.addActionListener(this);
        displayButton.addActionListener(this);
        saveButton.addActionListener(this);

        add(nameLabel);
        add(nameField);
        add(genderLabel);
        add(male);
        add(new Label());
        add(female);
        add(ageLabel);
        add(ageField);
        add(courseLabel);
        add(courseChoice);
        add(submitButton);
        add(displayButton);
        add(saveButton);
        add(new Label()); 
        add(messageLabel);
        add(displayArea);

        setSize(500, 600);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            handleSubmission();
        } else if (e.getSource() == displayButton) {
            handleDisplay();
        } else if (e.getSource() == saveButton) {
            handleSave();
        }
    }

    private void handleSubmission() {
        String name = nameField.getText();
        String gender = genderGroup.getSelectedCheckbox().getLabel();
        String age = ageField.getText();
        String course = courseChoice.getSelectedItem();

        if (name.isEmpty() || age.isEmpty()) {
            messageLabel.setText("Please fill all fields.");
            return;
        }

        try {
            int ageInt = Integer.parseInt(age);
            userData.append("Name: ").append(name).append(", Gender: ").append(gender)
                    .append(", Age: ").append(ageInt).append(", Course: ").append(course).append("\n");
            messageLabel.setText("User details submitted.");
            clearFields();
        } catch (NumberFormatException ex) {
            messageLabel.setText("Invalid age. Please enter a number.");
        }
    }

    private void handleDisplay() {
        displayArea.setText(userData.toString());
    }

    private void handleSave() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userData.txt"))) {
            writer.write(userData.toString());
            messageLabel.setText("User details saved to userData.txt");
        } catch (IOException ex) {
            messageLabel.setText("Error saving user details.");
        }
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        genderGroup.setSelectedCheckbox(null);
        courseChoice.select(0);
    }

    public static void main(String[] args) {
        new RegistrationManager();
    }
}
