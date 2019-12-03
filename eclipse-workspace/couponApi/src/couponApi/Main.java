package couponApi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Main extends JFrame{

	
    public static void main(String[] args) throws Exception {
    		Main main = new Main();
    
     
        API api = new API();
        Scanner scr = new Scanner(System.in);
        // System.out.println("Enter desired URL:");
        // String url = scr.nextLine();
        System.out.println("Enter search key:");
        String key = scr.nextLine();
        System.out.println("Enter frequency of search (in minutes and must be >= 30):");
        int frequency = scr.nextInt();
        if (frequency < 30 ) {
            System.out.println("Enter frequency of search (in minutes and must be >= 30):");
            frequency = scr.nextInt();
        }
        else{
            int freqInMilliSec = (frequency * 60)  * 1000; //frequency converted to milliseconds
            Timer timer = new Timer(freqInMilliSec , new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        //code here to display popup if deal is found
        	 try {
				String message = api.queryAPI("https://dealsea.com", key);
				if(!message.equals("")) {
					
					 main.setTitle("Deals Found");
				       main.setSize(300, 200);
				       main.setLocationRelativeTo(null);
				       main.setDefaultCloseOperation(EXIT_ON_CLOSE);
					 JLabel l = new JLabel(message); 
				        main.add(l);
				    	main.setVisible(true);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 
           }    
           });
           timer.start();
        }
        // https://dealsea.com/search?q=router&search_mode=Deals
       
    }
}
