import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Map;

public class Calculator {
    private Map<String, BigDecimal> currMap;
    private BigDecimal val;
    private String curr;

    public Calculator(Map<String, BigDecimal> currMap) {
        this.currMap = currMap;
    }

    public void start() {
        readAmount();
        readCurrency();
        calculate();
        sendOptions();
    }

    private void readAmount() {
        System.out.println("Enter amount in EUR:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            val = new BigDecimal(bufferedReader.readLine());
        } catch (NumberFormatException | IOException e) {
            System.out.println("*** You should enter a number value! ***");
            readAmount();
        }
    }

    private void readCurrency() {
        System.out.println("Enter a tag of currency from this list: ");
        for (Map.Entry<String, BigDecimal> entry : currMap.entrySet()) {
            System.out.print(entry.getKey() + " ");
        }
        System.out.println();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            curr = bufferedReader.readLine().toUpperCase();
            if (!curr.matches("[a-zA-Z]+")) {
                System.out.println("*** You should enter a tag! ***");
                readCurrency();
            } else if (currMap.get(curr) == null) {
                System.out.println("*** Tag is not correct! ***");
                readCurrency();
            }
        } catch (IOException e) {
            e.printStackTrace();
            readCurrency();
        }
    }

    private void calculate() {
        BigDecimal result = val.multiply(currMap.get(curr));
        System.out.println("EUR to " + curr + " = " + result);
    }

    private void sendOptions() {
        System.out.println("Would you like continue? 'y' - yes, 'n' - no");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String val = bufferedReader.readLine().toLowerCase();
            if (val.equals("y") || val.equals("yes")) {
                start();
            } else if (!val.equals("n") && !val.equals("no")) {
                System.out.println("*** You should enter only 'y' or 'n'! ***");
                sendOptions();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
