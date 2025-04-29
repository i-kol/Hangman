package java;

public class Graphics {
    static void drawHangman(int wrongTriesNumber) {
        switch (wrongTriesNumber) {
            case (0):
                System.out.println("_________");
                System.out.println("|       |");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                break;
            case (1):
                System.out.println("_________");
                System.out.println("|       |");
                System.out.println("|       O");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                break;
            case (2):
                System.out.println("_________");
                System.out.println("|       |");
                System.out.println("|       O");
                System.out.println("|       |");
                System.out.println("|");
                System.out.println("|");
                break;
            case (3):
                System.out.println("_________");
                System.out.println("|       |");
                System.out.println("|       O");
                System.out.println("|      /|");
                System.out.println("|");
                System.out.println("|");
                break;
            case (4):
                System.out.println("_________");
                System.out.println("|       |");
                System.out.println("|       O");
                System.out.println("|      /|\\");
                System.out.println("|");
                System.out.println("|");
                break;
            case (5):
                System.out.println("_________");
                System.out.println("|       |");
                System.out.println("|       O");
                System.out.println("|      /|\\");
                System.out.println("|      /-");
                System.out.println("|");
                break;
            case (6):
                System.out.println("_________");
                System.out.println("|       |");
                System.out.println("|       O");
                System.out.println("|      /|\\");
                System.out.println("|      / \\");
                System.out.println("|");
                break;
            default:
                throw new IllegalArgumentException("Something broke!");
        }
    }
}
