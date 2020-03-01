public class DoubleDemo {
    public static void main(String[] args) {
        //każda wartośc nie będąca wielokrotnością 2 jest zaogrąglana
        double a = 0.1;
        double b = 0.25;
        double c = 0.5;
        //Dzielenie przez zero
        System.out.println(a / 0.0);

        System.out.println(a * a);
        System.out.println(b * b);
        System.out.println(c * c);
        //Not a Number
        System.out.println(0.0 / 0.0);

        double d = 0.0;
        double e = 0.0;
        if ( d == e) {
            System.out.println(d / e == e / d);
        }


    }
}
