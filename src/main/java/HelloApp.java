public class HelloApp {
    public static void main(String[] args) {
        if (args.length < 1){
            return;
        }
        System.out.println("Hello " + args[0]);
    }
}
