package couponApi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;

public class API{
    public static String queryAPI(String url, String key) throws Exception{
            String baseUrl = url;
            url = url + "/search?q=" + key + "&search_mode=Deals";
            URL reqUrl = new URL(url);
            URLConnection yc = reqUrl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            HashMap <String, String> map = new HashMap<String, String>();
    
            while ((inputLine = in.readLine()) != null){
                // && inputLine.indexOf("commentdetail") == -1
                if (inputLine.indexOf("dealcontent") > -1 && inputLine.indexOf("Expired") == -1) {
                    String link = "";
                    String value = "";
                    String[] lines = inputLine.split(" ");
                    // System.out.println(Arrays.toString(lines));
                    int start = 0;
                    for (String phrase : lines) {
                        if (phrase.indexOf("dealcontent") > -1) {
                            // System.out.println(start);
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
                            //get link using href as a index
                            linkStart = lines[start+i].indexOf("href=");
                            // System.out.println(lines[start+i]);
                            temp = linkStart+6;
                            while(lines[start+i].charAt(temp) != 34){
                                temp++;
                            }
                            link = lines[start+i].substring(linkStart+6, temp);
                        }else if(lines[start+i].indexOf("</a>") == -1){
                            //parse the lines after the link to get the values
                            // System.out.println(lines[start+i]);
                            if (lines[start+i].indexOf("class=") == -1) {
                                value += lines[start+i] + " ";
                            }
                        }else{
                            // System.out.println(lines[start+i]);
                            int z = 0;
                            while(lines[start+i].charAt(z) != 60){
                                value += lines[start+i].charAt(z);
                                z++;
                            }
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
                System.out.println("Found deals on: " + (baseUrl+keyX) + " => \"" + value + "\"");
                String message = "Found deals on: " + (baseUrl+keyX) + " => \"" + value + "\"";
                return message;
               
            } 
            in.close();
			return "";
            
    }
    
}