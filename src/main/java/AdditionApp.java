public class AdditionApp {
    public static void main(String[] args) {
        if (args.length != 2){
            System.out.println("Błędna liczba argumentów!!!");
            return;
        }
        try {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            System.out.println(a + b);
        } catch(NumberFormatException e){
            System.out.println("Niepoprawny format liczb!!!");
        }
    }
}
