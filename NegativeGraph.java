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
public class NegativeGraph extends JPanel {

    protected final int dotSize = 25;
    protected final int arrowSize = 10;
    protected final int spacing = 5;
    protected Shapes shape;
    protected Exchange exch;

    public NegativeGraph(Shapes shape, Exchange exch) {
        this.shape = shape;
        this.exch = exch;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        int numNodes = shape.nodes.length;

        if (exch.negativeValues != null) { // Check if negativeValues is not null
            for (int i = 0; i < numNodes; i++) { // Draws arrows and labels for negative logarithm values
                Node sourceNode = shape.nodes[i];
                int x1 = sourceNode.x + dotSize / 2;
                int y1 = sourceNode.y + dotSize / 2;

                for (int j = 0; j < numNodes; j++) { // Draws arrows connecting nodes and add labels for negative logarithm values
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

                        // Add labels for negative logarithm if values exist
                        if (exch.negativeValues[i][j] != null) {
                            String rateLabel = String.format("%.3f", exch.negativeValues[i][j].rate);
                            Font labelFont = new Font("Calibri", Font.PLAIN, 16);
                            g.setFont(labelFont);

                            int labelX1, labelY1, labelX2, labelY2;

                            if (x1 < x2) {
                                // Arrow goes from left to right
                                labelX1 = (int) (x1 + (dotSize / 2) * Math.cos(angle)) +25;
                                labelY1 = (int) (y1 + (dotSize / 2) * Math.sin(angle))+25;
                                labelX2 = (int) (x2 + (dotSize / 2) * Math.cos(angle))-25;
                                labelY2 = (int) (y2 + (dotSize / 2) * Math.sin(angle))-25;
                            } else {
                                // Arrow goes from right to left
                                labelX1 = (int) (x1 - (dotSize / 2) * Math.cos(angle))+25;
                                labelY1 = (int) (y1 - (dotSize / 2) * Math.sin(angle))+25;
                                labelX2 = (int) (x2 - (dotSize / 2) * Math.cos(angle))-25;
                                labelY2 = (int) (y2 - (dotSize / 2) * Math.sin(angle))-25;
                            }

                            g.drawString(targetNode.name + " to " + sourceNode.name + ": " + rateLabel, labelX2, labelY2);
                        }
                    }
                }
            }

            for (Node node : shape.nodes) { // Draws nodes and add labels for negative logarithm values
                g.drawOval(node.x, node.y, dotSize, dotSize);
                Font labelFont = new Font("Calibri", Font.BOLD, 16);
                g.setFont(labelFont);
                g.drawString(node.name, (node.x + ((dotSize / 2) - 4)), (node.y + ((dotSize / 2) + 4)));
            }
        }

        repaint();
    }
}
