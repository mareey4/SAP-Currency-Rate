/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Currency_Exchange;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author snipi we still testing
 */
public class Node {
    String name;
    int x;
    int y;
    Map<Node, Double> links;
    
    public Node(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.links = new HashMap<>();
    }
    
    public void addLink(Node node, double rate) {
        this.links.put(node, rate);
    }
}
