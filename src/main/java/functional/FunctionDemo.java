package functional;

@FunctionalInterface
interface Function{
    double calc(double a, double b);
}

class Addition implements Function{

    @Override
    public double calc(double a, double b) {
        return a + b;
    }

    @Override
    public String toString() {
        return "Addition{}";
    }
}


public class FunctionDemo {
    public static void main(String[] args) {
        Addition add = new Addition();
        System.out.println(add.calc(4.9,5.8));
        //Klasa anonimowa
        Function diff = new Function() {
            @Override
            public double calc(double a, double b) {
                return a - b;
            }
        };
        Function mul = (c, d) -> c * d;
        Function div = (c, d) -> {
          if (d != 0.0){
              return c/d;
          } else {
              return Double.POSITIVE_INFINITY;
          }
        };
        System.out.println(diff.calc(5, 9));
        System.out.println(div);
    }
}
