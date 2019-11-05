import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class API{
    public static void queryAPI(String url, String key) throws Exception{
            URL reqUrl = new URL(url);
            URLConnection yc = reqUrl.openConnection();
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                    yc.getInputStream()));
            String inputLine;
    
            while ((inputLine = in.readLine()) != null) 
                if (inputLine.indexOf("dealcontent") > -1) {
                    System.out.println(inputLine);
                    
                }
            in.close();
        
    }
}