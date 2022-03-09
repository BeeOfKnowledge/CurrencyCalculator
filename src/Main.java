import util.DOMxmlReader;

public class Main {

    public static void main(String[] args) {
        DOMxmlReader reader = new DOMxmlReader();
        reader.read("src/resources/eurofxref-daily.xml");
        Calculator calc = new Calculator(reader.getCurrMap());
        calc.start();
    }
}
