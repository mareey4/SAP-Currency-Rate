/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Currency_Exchange;

/**
 *
 * @author snipi
 */
public class SizeFour extends Shapes{
    
    // Sub constructor for a graph of size four which initializes node
    // coordinates
    public SizeFour() {
        super(4);
        
        // Node coordinates
        super.nodes[0] = new Node("A", 80, 80);
        super.nodes[1] = new Node("B", 360, 80);
        super.nodes[2] = new Node("C", 360, 360);
        super.nodes[3] = new Node("D", 80, 360);
        
        // Exchange rates label coordinates
        super.labels[0] = new Node("A to B: ", 50, 45);
        super.labels[1] = new Node("A to C: ", 50, 60);
        super.labels[2] = new Node("A to D: ", 50, 75);
        super.labels[3] = new Node("B to A: ", 330, 45);
        super.labels[4] = new Node("B to C: ", 330, 60);
        super.labels[5] = new Node("B to D: ", 330, 75);
        super.labels[6] = new Node("C to A: ", 330, 400);
        super.labels[7] = new Node("C to B: ", 330, 415);
        super.labels[8] = new Node("C to D: ", 330, 430);
        super.labels[9] = new Node("D to A: ", 50, 400);
        super.labels[10] = new Node("D to B: ", 50, 415);
        super.labels[11] = new Node("D to C: ", 50, 430);
    }
}
