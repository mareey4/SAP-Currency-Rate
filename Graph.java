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
 * @author m4ria
 */
public class Graph extends JPanel {

    protected final int dotSize = 25;
    
    protected Shapes shape;

    public Graph(Shapes shape) {
        this.shape = shape;
    }

@Override
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.BLACK);

    int arrowSize = 10;
    int spacing = 5; 

    for (int i = 0; i < shape.nodes.length; i++) {
        Node sourceNode = shape.nodes[i];
        int x1 = sourceNode.x + dotSize / 2;
        int y1 = sourceNode.y + dotSize / 2;

        for (int j = 0; j < shape.nodes.length; j++) {
            if (i != j) {
                Node targetNode = shape.nodes[j];
                int x2 = targetNode.x + dotSize / 2;
                int y2 = targetNode.y + dotSize / 2;

                int dx = x2 - x1;
                int dy = y2 - y1;

                int perpDx = -dy;
                int perpDy = dx;

                double length = Math.sqrt(perpDx * perpDx + perpDy * perpDy);
                double scaleFactor = spacing / length;
                perpDx *= scaleFactor;
                perpDy *= scaleFactor;

                int arrowX1 = x1 + perpDx;
                int arrowY1 = y1 + perpDy;
                int arrowX2 = x2 + perpDx;
                int arrowY2 = y2 + perpDy;
                int arrowX3 = x1 - perpDx;
                int arrowY3 = y1 - perpDy;
                int arrowX4 = x2 - perpDx;
                int arrowY4 = y2 - perpDy;

                g.drawLine(arrowX1, arrowY1, arrowX2, arrowY2);
                drawArrowhead(g, x2, y2, arrowX1, arrowY1, arrowSize);

                g.drawLine(arrowX3, arrowY3, arrowX4, arrowY4);
                drawArrowhead(g, x1, y1, arrowX3, arrowY3, arrowSize);

            }
        }
    }

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

}