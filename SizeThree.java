/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Currency_Exchange;

/**
 *
 * @author snipi
 */
public class SizeThree extends Shapes{
    
    // Sub constructor for a graph of size three which initializes node
    // coordinates
    public SizeThree() {
        super(3);
        
        // Node coordinates
        super.nodes[0] = new Node("A", 50, 60);
        super.nodes[1] = new Node("B", 370, 60);
        super.nodes[2] = new Node("C", 210, 320);
        
        // Exchange rates label coordinates
        super.labels[0] = new Node("A to B: ", 20, 35);
        super.labels[1] = new Node("A to C: ", 20, 50);
        super.labels[2] = new Node("B to A: ", 340, 35);
        super.labels[3] = new Node("B to C: ", 340, 50);
        super.labels[4] = new Node("C to A: ", 180, 360);
        super.labels[5] = new Node("C to B: ", 180, 375);
    }
}
