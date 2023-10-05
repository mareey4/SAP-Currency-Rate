/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Currency_Exchange;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author m4ria
 */
public class Graph extends JPanel {

    protected final int dotSize = 25;
    protected Shapes shape;
    protected String[] labels; 


    public Graph(Shapes shape) {
        this.shape = shape;
    }

@Override
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.BLACK);

    // Calculate arrow positions based on the number of nodes
    int numNodes = shape.nodes.length;

    for (int i = 0; i < numNodes; i++) {
        Node sourceNode = shape.nodes[i];
        int x1 = sourceNode.x + dotSize / 2;
        int y1 = sourceNode.y + dotSize / 2;

        for (int j = 0; j < numNodes; j++) {
            if (i != j) {
                Node targetNode = shape.nodes[j];
                int x2 = targetNode.x + dotSize / 2;
                int y2 = targetNode.y + dotSize / 2;

                // Calculate arrow direction (angle)
                double angle = Math.atan2(y2 - y1, x2 - x1);

                // Calculate arrow endpoints
                int arrowX1 = (int) (x1 + (dotSize / 2) * Math.cos(angle));
                int arrowY1 = (int) (y1 + (dotSize / 2) * Math.sin(angle));
                int arrowX2 = (int) (x2 - (dotSize / 2) * Math.cos(angle));
                int arrowY2 = (int) (y2 - (dotSize / 2) * Math.sin(angle));

                // Draw a line from sourceNode to targetNode
                g.drawLine(arrowX1, arrowY1, arrowX2, arrowY2);

                // Draw an arrowhead at the targetNode
                int arrowSize = 10; // Adjust this value as needed
                int dx1 = (int) (arrowSize * Math.cos(angle - Math.PI / 6));
                int dy1 = (int) (arrowSize * Math.sin(angle - Math.PI / 6));
                int dx2 = (int) (arrowSize * Math.cos(angle + Math.PI / 6));
                int dy2 = (int) (arrowSize * Math.sin(angle + Math.PI / 6));
                g.drawLine(arrowX2, arrowY2, arrowX2 - dx1, arrowY2 - dy1);
                g.drawLine(arrowX2, arrowY2, arrowX2 - dx2, arrowY2 - dy2);
            }
        }
    }

    // Draw nodes
    for (Node node : shape.nodes) {
        g.drawOval(node.x, node.y, dotSize, dotSize);
        Font labelFont = new Font("Calibri", Font.BOLD, 16);
        g.setFont(labelFont);
        g.drawString(node.name, (node.x + ((dotSize / 2) - 4)), (node.y + ((dotSize / 2) + 4)));
    }

    repaint();
}

private void drawArrowhead(Graphics g, int x1, int y1, int x2, int y2, int arrowSize) {
    double angle = Math.atan2(y2 - y1, x2 - x1);
    int x3 = (int) (x2 - arrowSize * Math.cos(angle - Math.PI / 6));
    int y3 = (int) (y2 - arrowSize * Math.sin(angle - Math.PI / 6));
    int x4 = (int) (x2 - arrowSize * Math.cos(angle + Math.PI / 6));
    int y4 = (int) (y2 - arrowSize * Math.sin(angle + Math.PI / 6));
    g.drawLine(x2, y2, x3, y3);
    g.drawLine(x2, y2, x4, y4);
}

//public void setExchangeRates(Map<String, Map<String, String>> exchangeRates) {
//        this.exchangeRates = exchangeRates;
//    }
}