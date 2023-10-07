package Currency_Exchange;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Graph extends JPanel {

    protected final int dotSize = 25;
    protected Shapes shape;
    protected String[] labels;
    protected Rate[][] ratesArray; // Add this field

    public Graph(Shapes shape, Rate[][] ratesArray) { // Modify the constructor to accept ratesArray
        this.shape = shape;
        this.ratesArray = ratesArray; // Initialize ratesArray
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

                    // Add labels for exchange rates (you can customize this part)
                    String rateLabel = String.format("%.3f", 0.0);

                    if (ratesArray[i][j] != null) {
                        rateLabel = String.format("%.3f", ratesArray[i][j].rate);
                    }
//                    int labelX = (x1 + x2) / 2;
//                    int labelY = (y1 + y2) / 2;
//                    g.drawString(rateLabel, labelX, labelY);

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
