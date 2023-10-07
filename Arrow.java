/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Currency_Exchange;

/**
 *
 * @author m4ria
 */
public class Arrow {
    String link;
    int toX;
    int toY;
    int fromX;
    int fromY;
    
    public Arrow(String link ,int toX, int toY, int fromX, int fromY) {
        this.link = link;
        this.toX = toX;
        this.toY = toY;
        this.fromX = fromX;
        this.fromY = fromY;
    }
}