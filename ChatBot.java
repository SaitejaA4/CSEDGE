import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ChatBot {

    public static void main(String[] args) {
        ChatBot bot = new ChatBot();
        bot.start();
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Hello! I am your chatbot. How can I assist you today?");
        System.out.println("You can ask me to open applications or search the web.");

        while (true) {
            System.out.print("> ");
            input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Bye..!");
                break;
            } else if (input.startsWith("open ")) {
                String application = input.substring(5);
                openApplication(application);
            } else if (input.startsWith("search ")) {
                String query = input.substring(7);
                searchWeb(query);
            } else {
                System.out.println("I don't understand that command. Please try 'open <application>' or 'search <query>'.");
            }
        }

        scanner.close();
    }

    private void openApplication(String application) {
        try {
            if (application.equals("notepad")) {
                Runtime.getRuntime().exec("notepad");
            } else if (application.equals("calculator")) {
                Runtime.getRuntime().exec("calc");
            } else {
                System.out.println("Application not recognized. Try 'notepad' or 'calculator'.");
            }
        } catch (IOException e) {
            System.out.println("Failed to open application: " + e.getMessage());
        }
    }

    private void searchWeb(String query) {
        try {
            String url = "https://www.google.com/search?q=" + query.replace(" ", "+");
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                System.out.println("Desktop is not supported. Cannot perform web search.");
            }
        } catch (IOException | URISyntaxException e) {
            System.out.println("Failed to open web browser: " + e.getMessage());
        }
    }
}
