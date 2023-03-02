package Calculator;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        ArithmeticCalculator calculator = new ArithmeticCalculator();
        app(calculator);
    }

    public static void app(ArithmeticCalculator calculator) {
        try {
            // create the file
            String filename = "CalculationOut.txt";
            new File(filename);
            // Create the file writer object
            FileWriter myWriter = new FileWriter(filename);
            // Read the equations file
            File equationsTxt = new File("./equations.txt");
            Scanner myScanner = new Scanner(equationsTxt);
            while (myScanner.hasNextLine()) {
                String equation = myScanner.nextLine();
                myWriter.write(equation + "\n");
                // Do calculation
                String result = calculator.calculate(equation);
                myWriter.write(result + "\n");
            }
            System.out.print("Done");
            // close writer and scanner
            myWriter.close();
            myScanner.close();
        } catch (IOException e) {
            // Print the stack if exception
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
