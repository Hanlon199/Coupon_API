public class Main{
    public static void main(String[] args) throws Exception {
        API api = new API();
        api.queryAPI("https://dealsea.com/search?q=router&search_mode=Deals", "router");
    }
}