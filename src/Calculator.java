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

        BigDecimal res = val.multiply(currMap.get(curr));
        System.out.println("EUR to " + curr + " = " + res);
    }

    private void readAmount() {
        System.out.println("Enter the amount in EUR:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            val = new BigDecimal(bufferedReader.readLine());
        } catch (NumberFormatException | IOException e) {
            System.out.println("*** You should enter a number value! ***");
            readAmount();
        }
    }

    private void readCurrency() {
        System.out.println("Enter the tag of currency from this list: ");
        for (Map.Entry<String, BigDecimal> entry : currMap.entrySet()) {
            System.out.print(entry.getKey() + " ");
        }
        System.out.println();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            curr = bufferedReader.readLine().toUpperCase();
            if (!curr.matches("[a-zA-Z]+")) {
                System.out.println("*** You should enter a value with tag! ***");
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
}
