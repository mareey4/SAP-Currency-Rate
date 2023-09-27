/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Currency_Exchange;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author snipi
 */
public class Node {
    String name;
    Map<Node, Double> links;
    
    public Node(String name) {
        this.name = name;
        this.links = new HashMap<>();
    }
    
    public void addLink(Node node, double rate) {
        this.links.put(node, rate);
    }
}
