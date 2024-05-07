package bbambozzi.utils.logger;

public class ColorLogger {

    public void blue(String message) {
        System.out.println("\u001B[34m" + message + "\u001B[0m");
    }

    public void green(String message) {
        System.out.println("\u001B[32m" + message + "\u001B[0m");
    }

    public void red(String message) {
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }

    public void gray(String message) {
        System.out.println(message);
    }

}
