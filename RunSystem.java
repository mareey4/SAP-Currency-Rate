/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Currency_Exchange;

/**
 *
 * @author snipi
 */
public class RunSystem {
    
    // Creates and run the GUI as two separate windows for the two different
    // currency tables
    public static void main(String[] args) {
        Exchange exchange = new Exchange();
        
        exchange.setBounds(0, 0, exchange.width, exchange.height);
    }
}
