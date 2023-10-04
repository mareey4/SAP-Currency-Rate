/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Currency_Exchange;

/**
 *
 * @author snipi
 */
public class Shapes {
    final int dotSize = 25;
    int size;
    Node[] nodes;
    Arrow[] arrows;
    
    public Shapes(int size) {
        this.size = size;
        this.nodes = new Node[this.size];
        this.arrows = new Arrow[(this.size * 2)];
    }
}
