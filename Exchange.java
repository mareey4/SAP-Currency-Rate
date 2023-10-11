/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Currency_Exchange;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import org.json.JSONException;

/**
 *
 * @author snipi
 */
public class Exchange extends JFrame {

    // Variables for GUI components
    private JLabel sizeLabel;
    private JComboBox<Integer> matrixSizeCB;
    private JPanel inputsPanel;
    private JPanel matrix;
    private JPanel matrixPanel;
    private JPanel comboboxPanel;
    private JPanel currencyGraphPanel;
    private JLabel cGraphLabel;
    private JPanel currencyGraph;
    private JPanel negativeGraphPanel;
    private JLabel nGraphLabel;
    private JPanel negativeGraph;
    private JLabel answerLabel;
    private JPanel mainPanel;

    // Variables for GUI dimensions
    protected final int screenWidth;
    protected final int screenHeight;
    protected final int width = 955;
    protected final int height = 950;
    private final Toolkit toolKit;
    private final Dimension screenDim;

    // Variables for exchange rates api
    protected ExchangeRates exRates;

    // Variables for containing the data from exchange rates api
    protected Map<String, String> currencyNameMap = new HashMap<>();
    protected List<Map.Entry<String, String>> sortedCurrencyNames;
    protected Map<String, Double> currencyRatesMap = new HashMap<>();
    protected List<Map.Entry<String, Double>> sortedCurrencyRates;
    protected JComboBox<String>[] cbList;
    protected JLabel[][] matrixArray;
    protected Rate[][] ratesArray;

    // Variables for making the matrix
    protected final int[] matrixSize = {3, 4, 5,};
    protected String matrixKey = "From/To";
    protected final String[] labels = {"A", "B", "C", "D", "E"};
    protected int selectedSize;

    // Variables for the graph
    protected Rate[][] negativeValues;

    // Variables for arbitrage opportunity
    protected String arbitrageOpportunity = "Arbitrage Opportunity: ";

    // Constructor for the GUI
    public Exchange() {
        this.toolKit = Toolkit.getDefaultToolkit();
        this.screenDim = toolKit.getScreenSize();
        this.screenWidth = screenDim.width;
        this.screenHeight = screenDim.height;
        this.exRates = new ExchangeRates(this);
        setComponents();
    }

    // Function which initializes the GUI components and sets their attributes
    // and properties
    private void setComponents() {
        sizeLabel = new JLabel();
        matrixSizeCB = new JComboBox<>();
        inputsPanel = new JPanel();
        matrix = new JPanel(); // Comment for testing
        matrixPanel = new JPanel(); // Comment for testing
        comboboxPanel = new JPanel(); // Comment for testing
        currencyGraphPanel = new JPanel();
        cGraphLabel = new JLabel();
        negativeGraphPanel = new JPanel();
        nGraphLabel = new JLabel();
        answerLabel = new JLabel();
        mainPanel = new JPanel();

        setSizeLabel();
        setMatrixSizeCB();
        setInputsPanel();
        setMatrixPanel(); // Comment for testing
        setComboBoxPanel(); // Comment for testing
        setCurrencyGraphPanel();
        setCGraphLabel();
        setNegativeGraphPanel();
        setNGraphLabel();
        setMainPanel();
        setFrame();
    }

    // Function to set the attributes and properties of the size label
    private void setSizeLabel() {
        sizeLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        sizeLabel.setText("No. of Currencies:");
    }

    // Function to set the attributes and properties of the comboboxes to select
    // currencies with their action listeners
    private void setMatrixSizeCB() {
        for (int i = 0; i < matrixSize.length; i++) {
            matrixSizeCB.addItem(matrixSize[i]);
        }

        selectedSize = (int) matrixSizeCB.getSelectedItem();
        int size = selectedSize + 1; // Comment for testing
        createComboBoxes(); // Comment for testing
        createMatrix(size); // Comment for testing
        createCurrencyGraph();
        createNegativeGraph();

        // Sets action listeners for each combobox to call the functions to
        // update the currency table/matrix and graphs
        matrixSizeCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSize = (int) matrixSizeCB.getSelectedItem();
                int size = selectedSize + 1; // Comment for testing
                createComboBoxes(); // Comment for testing
                createMatrix(size); // Comment for testing
                createCurrencyGraph();
                createNegativeGraph();
//                    setAnswerLabel();
            }
        });
    }

    // Function to set the attributes and properties of the panel containing
    // the GUI components which take inputs to set the size of the table
    private void setInputsPanel() {
        inputsPanel.setBounds(0, 10, 200, 30);
        inputsPanel.setLayout(new FlowLayout());

        inputsPanel.add(sizeLabel);
        inputsPanel.add(matrixSizeCB);
    }

    // Function to set the attributes and properties of the panel containing the
    // currency table/matrix
    private void setMatrixPanel() {
        matrixPanel.setBounds(220, 10, 720, 280);
        matrixPanel.setLayout(new FlowLayout());

        matrixPanel.add(matrix);
    }

    // Function to set the attributes and properties of the panel to hold the
    // comboboxes for selecting currencies
    private void setComboBoxPanel() {
        comboboxPanel.setBounds(10, 50, 200, 280);
    }

    // Function to set the attributes and properties of the panel which draws
    // the currency graph
    private void setCurrencyGraphPanel() {
        currencyGraphPanel.setBounds(0, 370, 450, 450);
        currencyGraphPanel.setLayout(null);
    }

    // Function to set the attributes and properties of the label for the
    // currency graph
    private void setCGraphLabel() {
        cGraphLabel.setBounds(130, 350, 300, 17);
        cGraphLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        cGraphLabel.setText("Currency Exchange Graph");
    }

    // Function to set the attributes and properties of the panel which draws
    // the negative logarithm graph
    private void setNegativeGraphPanel() {
        negativeGraphPanel.setBounds(450, 370, 450, 450);
        negativeGraphPanel.setLayout(null);
    }

    // Function to set the attributes and properties of the label for the
    // negative logarithm graph
    private void setNGraphLabel() {
        nGraphLabel.setBounds(580, 350, 300, 17);
        nGraphLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        nGraphLabel.setText("Negative Logarithm Graph");
    }

    // Function to set the attributes and properties of the label which prints
    // the arbitrage opportunity
    private void setAnswerLabel() {
        // Label for Rauen to print the arbitrage opportunity
    }

    // Function to set the attributes and properties of the panel containing all
    // components of the GUI
    private void setMainPanel() {
        mainPanel.setPreferredSize(new Dimension(width, height));
        mainPanel.setLayout(null);

        mainPanel.add(inputsPanel);
        mainPanel.add(comboboxPanel); // Comment for testing
        mainPanel.add(matrixPanel); // Comment for testing
        mainPanel.add(currencyGraphPanel);
        mainPanel.add(cGraphLabel);
        mainPanel.add(negativeGraphPanel);
        mainPanel.add(nGraphLabel);
//        mainPanel.add(answerLabel);
    }

    // Function to set the attributes and properties of the frame containing the
    // GUI itself
    private void setFrame() {
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Currency Exchange");
        setBackground(Color.WHITE);
        setLocation(((screenWidth / 2) - (width / 2)), ((screenHeight / 2) - (height / 2)));
        setResizable(false);
        getContentPane().add(mainPanel);
        pack();

        // Window listener to close all windows when one of the windows is closed
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();

                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Function to create/update the currency table/matrix based on the user
    // selected size and currencies
    private void createMatrix(int size) {
        if (matrixArray != null) {
            matrixArray = null;
        }

        if (ratesArray != null) {
            ratesArray = null;
        }

        matrixArray = new JLabel[selectedSize][selectedSize];
        ratesArray = new Rate[selectedSize][selectedSize];
        negativeValues = new Rate[selectedSize][selectedSize];
        matrix.removeAll();
        matrix.setLayout(new GridLayout(size, size));
        exRates.calculateExchangeRates(); // Calculates/recalculates the exchange rates based on the selected currencies of the user

        // Draws each cell of the currency table/matrix
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setPreferredSize(new Dimension(80, 30));
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                if (row == 0 && col == 0) { // Sets 
                    JLabel label = new JLabel(matrixKey);
                    label.setFont(new Font("Segoe UI", Font.BOLD, 12));
                    cellPanel.add(label);
                } else if (row == 0 && col != 0) {
                    JLabel label = new JLabel(labels[col - 1]);
                    label.setFont(new Font("Segoe UI", Font.BOLD, 15));
                    cellPanel.add(label);
                } else if (row != 0 && col == 0) {
                    JLabel label = new JLabel(labels[row - 1]);
                    label.setFont(new Font("Segoe UI", Font.BOLD, 15));
                    cellPanel.add(label);
                } else {
                    JLabel label = new JLabel();
                    String text = "";

                    // Sets the String of each the cell of the matrix as the 
                    // exchange rate value if there is a value for it available
                    if ((row - 1) < selectedSize && (col - 1) < selectedSize
                            && ratesArray[(row - 1)][(col - 1)] != null && ratesArray[(row - 1)][(col - 1)].rate != 0.0) {
                        text += String.format("%.3f", ratesArray[(row - 1)][(col - 1)].rate);
                    }

                    label.setText(text);
                    label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                    cellPanel.add(label);

                    // Sets the text of each cell of the matrix based on the
                    // String
                    if (row < selectedSize && col < selectedSize) {
                        matrixArray[row][col] = label;
                    }
                }

                matrix.add(cellPanel);
            }
        }

        matrix.revalidate();
    }

    // Function to create/update the comboboxes where the user can select
    // different currencies
    private void createComboBoxes() {
        if (cbList != null) {
            cbList = null;
        }

        cbList = new JComboBox[selectedSize];
        comboboxPanel.removeAll();
        comboboxPanel.setLayout(new GridLayout(selectedSize, 1));

        // Loops to create combomboxes based on the selected size of the user
        for (int i = 0; i < selectedSize; i++) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel label = new JLabel("Currency " + labels[i] + ":");
            label.setFont(new Font("Segoe UI", Font.BOLD, 15));
            panel.add(label);

            JComboBox<String> comboBox = new JComboBox<>();

            // Adds all the currencies pulled by the exchange rates api and adds
            // them to each combobox
            for (Map.Entry<String, String> entry : sortedCurrencyNames) {
                comboBox.addItem(entry.getKey());
            }

            cbList[i] = comboBox;

            comboBox.setSelectedIndex(-1);
            Dimension comboBoxSize = new Dimension(80, 30);
            comboBox.setPreferredSize(comboBoxSize);
            panel.add(comboBox);
            comboboxPanel.add(panel);

            // Action listener to update the currency table/matrix based on the
            // currencies the user selects
            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int size = selectedSize + 1;
                    createMatrix(size);
                }
            });
        }
    }

    // Function to create/update the panel where the negative logarithm graph is
    // drawn based on the selected size of the user
    private void createNegativeGraph() {
        if (negativeGraph != null) {
            negativeGraphPanel.remove(negativeGraph);
        }

        // Creates a panel to draw the negative logarithm graph based on the 
        // selected size of the user
        switch (selectedSize) {
            case 3:
                SizeThree three = new SizeThree();
                negativeGraph = new NegativeGraph(three, this);

                break;
            case 4:
                SizeFour four = new SizeFour();
                negativeGraph = new NegativeGraph(four, this);

                break;
            case 5:
                SizeFive five = new SizeFive();
                negativeGraph = new NegativeGraph(five, this);

                break;
        }

        negativeGraph.setBounds(0, 0, 450, 450);
        negativeGraphPanel.add(negativeGraph);
        negativeGraphPanel.revalidate();
        negativeGraphPanel.repaint();
    }

    // Function to create/update the panel where the currency exchange graph is
    // drawn based on the selected size of the user
    private void createCurrencyGraph() {
        if (currencyGraph != null) {
            currencyGraphPanel.remove(currencyGraph);
        }

        // Creates a panel to draw the currency exchange graph based on the
        // selected size of the user
        switch (selectedSize) {
            case 3:
                SizeThree three = new SizeThree();
                currencyGraph = new CurrencyGraph(three, this);

                break;
            case 4:
                SizeFour four = new SizeFour();
                currencyGraph = new CurrencyGraph(four, this);

                break;
            case 5:
                SizeFive five = new SizeFive();
                currencyGraph = new CurrencyGraph(five, this);

                break;
        }

        currencyGraph.setBounds(0, 0, 450, 450);
        currencyGraphPanel.add(currencyGraph);
        currencyGraphPanel.revalidate();
        currencyGraphPanel.repaint();
    }
}
