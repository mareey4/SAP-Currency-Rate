/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Currency_Exchange;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author snipi
 */
public class Graph extends JPanel {

    protected final int dotSize = 25;
    protected Shapes shape;
    protected BellmanFord bf;

    public Graph(Shapes shape, BellmanFord bf) {
        this.shape = shape;
        this.bf = bf;
    }

      public Graph(Shapes shape) {
        this.shape = shape;

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        for (Node node : shape.nodes) {
            g.drawOval(node.x, node.y, dotSize, dotSize);
            Font labelFont = new Font("Calibri", Font.BOLD, 16);
            g.setFont(labelFont);
            g.drawString(node.name, (node.x + ((dotSize / 2) - 4)), (node.y + ((dotSize / 2) + 4)));
        }
        
        repaint();
    }
}
