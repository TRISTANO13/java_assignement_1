import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Excel {
    private List<Double> numbers;

    // Constructor accepting a List<Double>
    public Excel(List<Double> numbers) {
        this.numbers = numbers;
    }

    // Constructor accepting a string of numbers separated by spaces
    public Excel(String numbersStr) {
        this.numbers = new ArrayList<>();
        for (String strNum : numbersStr.split("\\s+")) {
            try {
                this.numbers.add(Double.parseDouble(strNum));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input: " + strNum, e);
            }
        }
    }

    public double findTotal() {
        return numbers.stream().mapToDouble(Double::doubleValue).sum();
    }

    public double findAvg() {
        return numbers.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
    }

    public double findMax() {
        return Collections.max(numbers);
    }

    public double findMin() {
        return Collections.min(numbers);
    }
}
