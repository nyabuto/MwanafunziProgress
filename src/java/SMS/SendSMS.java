/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SMS;

import org.json.JSONArray;
/**
 *
 * @author mwamb
 */
public class SendSMS {
    // My login credentials
    String username = "NyabutoGef";// africastalking username
    String apiKey   = "8add5e6a5be1a1f9959495c20d09e72af655cf23c836512e6ccf69d489008d11";// africastalking api key
    AfricasTalkingGateway gateway  = new AfricasTalkingGateway(username, apiKey);// create a gateway object

    
    public  boolean ActionSend(String message, String recipientNos){
        boolean returned=false;  
        
       
//        check for internet connections
        try {
            JSONArray results = gateway.sendMessage(recipientNos, message);
            returned=true;
        }
        catch (Exception e) {
            returned=false;
            System.out.println("Encountered an error while sending " + e.getMessage());
        } 
        return returned;
    }
    
    public boolean isConnected(){
     
        return true;
    }
}
