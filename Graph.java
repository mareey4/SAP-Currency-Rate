////package Currency_Exchange;
////
////import java.awt.Color;
////import java.awt.Font;
////import java.awt.Graphics;
////import javax.swing.JPanel;
////
////public class Graph extends JPanel {
////
////    protected final int dotSize = 25;
////    protected Shapes shape;
////    protected String[] labels;
////    protected Rate[][] ratesArray; // Add this field
////
////    public Graph(Shapes shape, Rate[][] ratesArray) { // Modify the constructor to accept ratesArray
////        this.shape = shape;
////        this.ratesArray = ratesArray; // Initialize ratesArray
////    }
////
////    @Override
////    public void paintComponent(Graphics g) {
////        super.paintComponent(g);
////        g.setColor(Color.BLACK);
////
////        // Calculate arrow positions based on the number of nodes
////        int numNodes = shape.nodes.length;
////
////        for (int i = 0; i < numNodes; i++) {
////            Node sourceNode = shape.nodes[i];
////            int x1 = sourceNode.x + dotSize / 2;
////            int y1 = sourceNode.y + dotSize / 2;
////
////            for (int j = 0; j < numNodes; j++) {
////                if (i != j) {
////                    Node targetNode = shape.nodes[j];
////                    int x2 = targetNode.x + dotSize / 2;
////                    int y2 = targetNode.y + dotSize / 2;
////
////                    // Calculate arrow direction (angle)
////                    double angle = Math.atan2(y2 - y1, x2 - x1);
////
////                    // Calculate arrow endpoints
////                    int arrowX1 = (int) (x1 + (dotSize / 2) * Math.cos(angle));
////                    int arrowY1 = (int) (y1 + (dotSize / 2) * Math.sin(angle));
////                    int arrowX2 = (int) (x2 - (dotSize / 2) * Math.cos(angle));
////                    int arrowY2 = (int) (y2 - (dotSize / 2) * Math.sin(angle));
////
////                    // Draw a line from sourceNode to targetNode
////                    g.drawLine(arrowX1, arrowY1, arrowX2, arrowY2);
////
////                    // Draw an arrowhead at the targetNode
////                    int arrowSize = 10; // Adjust this value as needed
////                    int dx1 = (int) (arrowSize * Math.cos(angle - Math.PI / 6));
////                    int dy1 = (int) (arrowSize * Math.sin(angle - Math.PI / 6));
////                    int dx2 = (int) (arrowSize * Math.cos(angle + Math.PI / 6));
////                    int dy2 = (int) (arrowSize * Math.sin(angle + Math.PI / 6));
////                    g.drawLine(arrowX2, arrowY2, arrowX2 - dx1, arrowY2 - dy1);
////                    g.drawLine(arrowX2, arrowY2, arrowX2 - dx2, arrowY2 - dy2);
////
////                    // Add labels for exchange rates (you can customize this part)
////                    String rateLabel = String.format("%.3f", 0.0);
////
//////                    if (ratesArray[i][j] != null) {
//////                        rateLabel = String.format("%.3f", ratesArray[i][j].rate);
//////                    }
//////                    int labelX = (x1 + x2) / 2;
//////                    int labelY = (y1 + y2) / 2;
//////                    g.drawString(rateLabel, labelX, labelY);
////
////                }
////            }
////        }
////
////        // Draw nodes
////        for (Node node : shape.nodes) {
////            g.drawOval(node.x, node.y, dotSize, dotSize);
////            Font labelFont = new Font("Calibri", Font.BOLD, 16);
////            g.setFont(labelFont);
////            g.drawString(node.name, (node.x + ((dotSize / 2) - 4)), (node.y + ((dotSize / 2) + 4)));
////        }
////
////        repaint();
////    }
////}
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Currency_Exchange;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import javax.swing.JPanel;
//
///**
// *
// * @author m4ria
// */
//public class Graph extends JPanel {
//
//    protected final int dotSize = 25;
//    protected final int arrowSize = 10;
//    protected final int spacing = 5;
//    protected Shapes shape;
//
//    public Graph(Shapes shape) {
//        this.shape = shape;
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.setColor(Color.BLACK);
//        
//
//
//        for (int i = 0; i < shape.nodes.length; i++) {
//            Node sourceNode = shape.nodes[i];
//            int x1 = sourceNode.x + dotSize / 2;
//            int y1 = sourceNode.y + dotSize / 2;
//
//            for (int j = 0; j < shape.nodes.length; j++) {
//                if (i != j) {
//                    Node targetNode = shape.nodes[j];
//                    int x2 = targetNode.x + dotSize / 2;
//                    int y2 = targetNode.y + dotSize / 2;
//
//                    int dx = x2 - x1;
//                    int dy = y2 - y1;
//
//                    int perpDx = -dy;
//                    int perpDy = dx;
//
//                    double length = Math.sqrt(perpDx * perpDx + perpDy * perpDy);
//                    double scaleFactor = spacing / length;
//                    perpDx *= scaleFactor;
//                    perpDy *= scaleFactor;
//
//                    int arrowX1 = x1 + perpDx;
//                    int arrowY1 = y1 + perpDy;
//                    int arrowX2 = x2 + perpDx;
//                    int arrowY2 = y2 + perpDy;
//                    int arrowX3 = x1 - perpDx;
//                    int arrowY3 = y1 - perpDy;
//                    int arrowX4 = x2 - perpDx;
//                    int arrowY4 = y2 - perpDy;
//
//                    g.drawLine(arrowX1, arrowY1, arrowX2, arrowY2);
//                    drawArrowhead(g, x2, y2, arrowX1, arrowY1, arrowSize);
//
//                    g.drawLine(arrowX3, arrowY3, arrowX4, arrowY4);
//                    drawArrowhead(g, x1, y1, arrowX3, arrowY3, arrowSize);
//
//                }
//            }
//        }
//
//        for (Node node : shape.nodes) {
//            g.drawOval(node.x, node.y, dotSize, dotSize);
//            Font labelFont = new Font("Calibri", Font.BOLD, 16);
//            g.setFont(labelFont);
//            g.drawString(node.name, (node.x + ((dotSize / 2) - 4)), (node.y + ((dotSize / 2) + 4)));
//        }
//
//        repaint();
//    }
//
//    private void drawArrowhead(Graphics g, int x1, int y1, int x2, int y2, int arrowSize) {
//        double angle = Math.atan2(y2 - y1, x2 - x1);
//        int x3 = (int) (x2 - arrowSize * Math.cos(angle - Math.PI / 6));
//        int y3 = (int) (y2 - arrowSize * Math.sin(angle - Math.PI / 6));
//        int x4 = (int) (x2 - arrowSize * Math.cos(angle + Math.PI / 6));
//        int y4 = (int) (y2 - arrowSize * Math.sin(angle + Math.PI / 6));
//        g.drawLine(x2, y2, x3, y3);
//        g.drawLine(x2, y2, x4, y4);
//    }
//}
package Currency_Exchange;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Graph extends JPanel {

    protected final int dotSize = 25;
    protected final int arrowSize = 10;
    protected Shapes shape;
    protected Rate[][] ratesArray;

    public Graph(Shapes shape, Rate[][] ratesArray) {
        this.shape = shape;
        this.ratesArray = ratesArray;
    }

    public void updateRatesArray(Rate[][] newRatesArray) {
        this.ratesArray = newRatesArray;
        System.out.println("Updated rates in Graph class:");
        for (int i = 0; i < ratesArray.length; i++) {
            for (int j = 0; j < ratesArray[i].length; j++) {
                if (ratesArray[i][j] != null) {
                    System.out.println("Rate from " + i + " to " + j + ": " + ratesArray[i][j].rate);
                }
            }
        }
        repaint(); // This will trigger a repaint of the graph with updated rates
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        int numNodes = shape.nodes.length;

        if (ratesArray != null) { // Check if ratesArray is not null
            for (int i = 0; i < numNodes; i++) {
                Node sourceNode = shape.nodes[i];
                int x1 = sourceNode.x + dotSize / 2;
                int y1 = sourceNode.y + dotSize / 2;

                for (int j = 0; j < numNodes; j++) {
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

                        int arrowSize = 10; // Adjust this value as needed

                        int dx1 = (int) (arrowSize * Math.cos(angle - Math.PI / 6));
                        int dy1 = (int) (arrowSize * Math.sin(angle - Math.PI / 6));
                        int dx2 = (int) (arrowSize * Math.cos(angle + Math.PI / 6));
                        int dy2 = (int) (arrowSize * Math.sin(angle + Math.PI / 6));
                        g.drawLine(arrowX2, arrowY2, arrowX2 - dx1, arrowY2 - dy1);
                        g.drawLine(arrowX2, arrowY2, arrowX2 - dx2, arrowY2 - dy2);

                        // Add labels for exchange rates if rates exist
                        if (ratesArray[i][j] != null) {
                            String rateLabel = String.format("%.3f", ratesArray[i][j]);
                            int labelX = (x1 + x2) / 2;
                            int labelY = (y1 + y2) / 2;

                            Font labelFont = new Font("Calibri", Font.BOLD, 16);
                            g.setFont(labelFont);

                            // Adjust the label position for better visibility
                            labelX += dx1 / 2;
                            labelY += dy1 / 2;
                            g.drawString(rateLabel, labelX, labelY);
                        }
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
