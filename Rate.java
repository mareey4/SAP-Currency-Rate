/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Currency_Exchange;

/**
 *
 * @author snipi
 */
public class Rate {
    // Variables for a rate of exchange between two currencies
    String from;
    String to;
    double rate;
    
    // Constructor for initializing the variables of a rate of exchange
    public Rate(String from, String to, double rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }
}
