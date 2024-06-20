import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {

    // GUI Components
    JTextArea textArea;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem openItem, saveItem, exitItem;

    public TextEditor() {
        // Set the title of the window
        setTitle("Text Editor");

        // Set the size of the window
        setSize(800, 600);

        // Set the default close operation
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize the text area
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);

        // Add the scroll pane to the frame
        add(scrollPane, BorderLayout.CENTER);

        // Initialize the menu bar
        menuBar = new JMenuBar();

        // Initialize the file menu
        fileMenu = new JMenu("File");

        // Initialize the menu items
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        // Add action listeners to the menu items
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        // Add the menu items to the file menu
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Add the file menu to the menu bar
        menuBar.add(fileMenu);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);

        // Set the frame to be visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openItem) {
            openFile();
        } else if (e.getSource() == saveItem) {
            saveFile();
        } else if (e.getSource() == exitItem) {
            System.exit(0);
        }
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "File could not be opened", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "File could not be saved", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextEditor::new);
    }
}
