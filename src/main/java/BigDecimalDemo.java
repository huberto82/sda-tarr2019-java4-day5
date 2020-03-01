import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalDemo {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("15.67");
        BigDecimal b = new BigDecimal(10L);
        BigDecimal c = new BigDecimal("3.6");
        BigDecimal mulab = a.multiply(b);
        System.out.println(mulab);
        System.out.println(a.add(b).divide(c, RoundingMode.HALF_EVEN));
        //(1000.67 + 678.89)/(45.7*23)
        System.out.println((1000.67+678.89)/(45.7*23));


        System.out.println("abc".substring(0,2));

    }
}
