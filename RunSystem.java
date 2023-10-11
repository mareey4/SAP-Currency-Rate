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
        Exchange exchangeOne = new Exchange();
        Exchange exchangeTwo = new Exchange();
        
        exchangeOne.setBounds(0, 0, exchangeOne.width, exchangeOne.height);
        exchangeTwo.setBounds(exchangeOne.width, 0, exchangeTwo.width, exchangeTwo.height);
    }
}
