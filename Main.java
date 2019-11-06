import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        API api = new API();
        Scanner scr = new Scanner(System.in);
        // System.out.println("Enter desired URL:");
        // String url = scr.nextLine();
        System.out.println("Enter search key:");
        String key = scr.nextLine();
        // https://dealsea.com/search?q=router&search_mode=Deals
        api.queryAPI("https://dealsea.com", key);
    }
}