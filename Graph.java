
package Currency_Exchange;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Graph extends JPanel {

    protected final int dotSize = 25;
    protected final int arrowSize = 10;
    protected Shapes shape;
    protected Exchange exch;
    public boolean negative = false;
    protected BellmanFord bf;

    public Graph(Shapes shape, Exchange exch) {
        this.shape = shape;
        this.exch = exch;
        this.bf = new BellmanFord();
    }

    // This will trigger a repaint of the graph with updated rates
    public void updateRatesArray(Rate[][] newRatesArray) {
        this.exch.ratesArray = newRatesArray;
        System.out.println("Updated rates in Graph class:");
        for (int i = 0; i < exch.ratesArray.length; i++) {
            for (int j = 0; j < exch.ratesArray[i].length; j++) {
                if (exch.ratesArray[i][j] != null) {
                    System.out.println("Rate from " + i + " to " + j + ": " + exch.ratesArray[i][j].rate);
                }
            }
        }
        repaint(); 
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        int numNodes = shape.nodes.length;

        if (exch.ratesArray != null) { // Check if ratesArray is not null
            for (int i = 0; i < numNodes; i++) { // Draws arrows and labels for exchange rates
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
                        if (exch.ratesArray[i][j] != null) {
                            String rateLabel = String.format("%.3f", exch.ratesArray[i][j].rate);
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

//
//                            negative = true;
//                            if (exch.ratesArray[exch.selectedSize - 1][exch.selectedSize - 1] != null) {
//                                List<Rate> negativeCycle = bf.findNegativeCycle(exch.ratesArray);
//                                List<Rate> arbitrage = bf.findArbitrageOpportunity(exch.ratesArray);
//                                if (negative) {
//
//                                    rateLabel = String.format("%.3f", negativeCycle.get(i).rate);
//                                    labelX = (x1 + x2) / 2;
//                                    labelY = (y1 + y2) / 2;
//
//                                    labelFont = new Font("Calibri", Font.BOLD, 16);
//                                    g.setFont(labelFont);
//
//                                    labelXSpacing = 10;
//                                    labelYSpacing = 10;
//                                    // Adjust the label position for better visibility
//                                    labelX += dx1 / 2 + labelXSpacing;
//                                    labelY += dy1 / 2;
//                                    g.drawString(rateLabel, labelX, labelY);
//                                }
//                            }
//                            negative = false;
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
    }

}
