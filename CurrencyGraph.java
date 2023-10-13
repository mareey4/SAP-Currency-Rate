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
public class CurrencyGraph extends JPanel {
    // Variables for drawing the currency exchange graph
    protected final int dotSize = 25;
    protected final int arrowSize = 10;
    protected final int spacing = 5;
    protected Shapes shape;
    protected Exchange exch;

    // Constructor for initializing the variables needed to draw the currency
    // exchange graph
    public CurrencyGraph(Shapes shape, Exchange exch) {
        this.shape = shape;
        this.exch = exch;
    }

    // Function to draw the graph
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        
        int numNodes = shape.nodes.length;

        if (exch.ratesArray != null) { // Check if ratesArray is not null
            for (int i = 0, k = 0; i < numNodes; i++) { // Draws arrows and labels for exchange rates
                Node sourceNode = shape.nodes[i];
                int x1 = sourceNode.x + dotSize / 2;
                int y1 = sourceNode.y + dotSize / 2;

                for (int j = 0; j < numNodes; j++) { // Draws arrows connecting nodes and add labels for exchange
                    if (i != j) {
                        Node targetNode = shape.nodes[j];
                        int x2 = targetNode.x + dotSize / 2;
                        int y2 = targetNode.y + dotSize / 2;

                        double angle = Math.atan2(y2 - y1, x2 - x1);

                        int arrowX1 = (int) (x1 + (dotSize / 2) * Math.cos(angle));
                        int arrowY1 = (int) (y1 + (dotSize / 2) * Math.sin(angle));
                        int arrowX2 = (int) (x2 - (dotSize / 2) * Math.cos(angle));
                        int arrowY2 = (int) (y2 - (dotSize / 2) * Math.sin(angle));
                        g.drawLine(arrowX1, arrowY1, arrowX2, arrowY2);

                        int dx1 = (int) (arrowSize * Math.cos(angle - Math.PI / 6));
                        int dy1 = (int) (arrowSize * Math.sin(angle - Math.PI / 6));
                        int dx2 = (int) (arrowSize * Math.cos(angle + Math.PI / 6));
                        int dy2 = (int) (arrowSize * Math.sin(angle + Math.PI / 6));
                        g.drawLine(arrowX2, arrowY2, arrowX2 - dx1, arrowY2 - dy1);
                        g.drawLine(arrowX2, arrowY2, arrowX2 - dx2, arrowY2 - dy2);

                        // Add labels for exchange rates if rates exist
                        if (exch.ratesArray[i][j] != null && i != j) {
                            String rateLabel = String.format("%.3f", exch.ratesArray[i][j].rate);
                            Font labelFont = new Font("Calibri", Font.PLAIN, 16);
                            g.setFont(labelFont);
                            g.drawString(shape.labels[k].name + rateLabel, shape.labels[k].x, shape.labels[k].y);
                            k++;
                        }
                    }
                }
            }

            for (Node node : shape.nodes) { // Draws nodes and add labels for currencies
                g.drawOval(node.x, node.y, dotSize, dotSize);
                Font labelFont = new Font("Calibri", Font.BOLD, 16);
                g.setFont(labelFont);
                g.drawString(node.name, (node.x + ((dotSize / 2) - 4)), (node.y + ((dotSize / 2) + 4)));
            }
        }
        
        repaint();
    }
}
