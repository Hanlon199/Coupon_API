import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;

public class API{
    public static void queryAPI(String url, String key) throws Exception{
            URL reqUrl = new URL(url);
            URLConnection yc = reqUrl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            HashMap <String, String> map = new HashMap<String, String>();
    
            while ((inputLine = in.readLine()) != null){
                if (inputLine.indexOf("dealcontent") > -1 && inputLine.indexOf("Expired") == -1 && inputLine.indexOf("commentdetail") == -1) {
                    String link = "";
                    String value = "";
                    String[] lines = inputLine.split(" ");
                    System.out.println(Arrays.toString(lines));
                    int start = 0;
                    for (String phrase : lines) {
                        if (phrase.indexOf("dealcontent") > -1) {
                            System.out.println(start);
                            break;
                        }
                        start++;
                    }
                    for (int i = 1; start+i < lines.length; i++) {
                        int linkStart = 0;
                        int temp = 0;
                        int valStart = 0;
                        int valEnd = 0;
                        if (lines[start+i].indexOf("href=") > -1) {
                            linkStart = lines[start+i].indexOf("href=");
                            System.out.println(lines[start+i]);
                            temp = linkStart+6;
                            while(lines[start+i].charAt(temp) != 34){
                                temp++;
                            }
                            link = lines[start+i].substring(linkStart+6, temp);
                        }else if(lines[start+i].indexOf("</a>") == -1){
                            value += lines[start+i];
                        }else{
                            break;
                        }
                        // System.out.println(link);
                        // System.out.println(value);
                    }
                    map.put(link,value);
                    // value = inputLine.substring(valStart, (valStart+valEnd));
                }
            }
            for (String x: map.keySet()){
                String keyX = x.toString();
                String value = map.get(x).toString();  
                System.out.println("Deal Found here: " + keyX + " with value of " + value);
            } 
            in.close();
            
    }
}