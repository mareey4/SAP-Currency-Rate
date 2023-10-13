/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Currency_Exchange;

/**
 *
 * @author snipi
 */
public class SizeFive extends Shapes{
    
    // Sub constructor for a graph of size five which initializes node
    // coordinates
    public SizeFive() {
        super(5);
        
        // Node coordinates
        super.nodes[0] = new Node("A", 220, 90);
        super.nodes[1] = new Node("B", 350, 190);
        super.nodes[2] = new Node("C", 310, 350);
        super.nodes[3] = new Node("D", 140, 350);
        super.nodes[4] = new Node("E", 90, 190);
        
        // Exchange rates label coordinates
        super.labels[0] = new Node("A to B: ", 190, 40);
        super.labels[1] = new Node("A to C: ", 190, 55);
        super.labels[2] = new Node("A to D: ", 190, 70);
        super.labels[3] = new Node("A to E: ", 190, 85);
        super.labels[4] = new Node("B to A: ", 350, 135);
        super.labels[5] = new Node("B to C: ", 350, 150);
        super.labels[6] = new Node("B to D: ", 350, 165);
        super.labels[7] = new Node("B to E: ", 350, 180);
        super.labels[8] = new Node("C to A: ", 280, 390);
        super.labels[9] = new Node("C to B: ", 280, 405);
        super.labels[10] = new Node("C to D: ", 280, 420);
        super.labels[11] = new Node("C to E: ", 280, 435);
        super.labels[12] = new Node("D to A: ", 110, 390);
        super.labels[13] = new Node("D to B: ", 110, 405);
        super.labels[14] = new Node("D to C: ", 110, 420);
        super.labels[15] = new Node("D to E: ", 110, 435);
        super.labels[16] = new Node("E to A: ", 20, 135);
        super.labels[17] = new Node("E to B: ", 20, 150);
        super.labels[18] = new Node("E to C: ", 20, 165);
        super.labels[19] = new Node("E to D: ", 20, 180);
    }
}
