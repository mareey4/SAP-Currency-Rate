///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Currency_Exchange;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.GridLayout;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import javax.swing.JFrame;
//import org.json.JSONObject;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.swing.BorderFactory;
//import javax.swing.JComboBox;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.WindowConstants;
//import org.json.JSONException;
//
///**
// *
// * @author snipi
// */
//public class Exchange extends JFrame {
//
//    private JLabel sizeLabel;
//    private JComboBox<Integer> matrixSizeCB;
//    private JPanel inputsPanel;
//    private JPanel matrix;
//    private JPanel matrixPanel;
//    private JPanel comboboxPanel;
//    private JPanel currencyGraphPanel;
//    private JLabel cGraphLabel;
//    private JPanel currencyGraph;
//    private JPanel negativeGraphPanel;
//    private JLabel nGraphLabel;
//    private JPanel negativeGraph;
//    private JPanel mainPanel;
//
//    protected final int screenWidth;
//    protected final int screenHeight;
//    protected final int width = 955;
//    protected final int height = 950;
//    private final Toolkit toolKit;
//    private final Dimension screenDim;
//    protected String apiKey = "a7ab00cb947f9df98b98bbde56720152";
//    protected String apiSymbolsURL = "http://api.exchangeratesapi.io/v1/symbols?access_key=" + apiKey;
//    protected String apiRatesURL = "http://api.exchangeratesapi.io/v1/latest?access_key=" + apiKey;
//    protected URL url;
//    protected BufferedReader br;
//    protected Map<String, String> currencyNameMap = new HashMap<>();
//    protected List<Map.Entry<String, String>> sortedCurrencyNames;
//    protected Map<String, Double> currencyRatesMap = new HashMap<>();
//    protected List<Map.Entry<String, Double>> sortedCurrencyRates;
//    protected ArrayList<JComboBox<String>> currencyCBList;
//    protected JComboBox<String>[] cbList;
//    protected final int[] matrixSize = {3, 4, 5, 6, 7, 8};
//    protected final String[] labels = {"A", "B", "C", "D", "E", "F", "G", "H"};
//    protected JLabel[][] matrixArray;
//    protected Rate[][] ratesArray;
//    protected int selectedSize;
//    protected String baseCurrencyOne = "";
//    protected String baseCurrencyTwo = "";
//    protected String[] selectedCurrency;
//
//    public Exchange() {
//        this.toolKit = Toolkit.getDefaultToolkit();
//        this.screenDim = toolKit.getScreenSize();
//        this.screenWidth = screenDim.width;
//        this.screenHeight = screenDim.height;
//        getCurrencyNames(); // Comment for testing
//        getRates(); // Comment for testing
//        setComponents();
//    }
//
//    private void setComponents() {
//        sizeLabel = new JLabel();
//        matrixSizeCB = new JComboBox<>();
//        inputsPanel = new JPanel();
//        matrix = new JPanel(); // Comment for testing
//        matrixPanel = new JPanel(); // Comment for testing
//        comboboxPanel = new JPanel(); // Comment for testing
//        currencyGraphPanel = new JPanel();
//        cGraphLabel = new JLabel();
//        negativeGraphPanel = new JPanel();
//        nGraphLabel = new JLabel();
//        mainPanel = new JPanel();
//
//        setSizeLabel();
//        setMatrixSizeCB();
//        setInputsPanel();
//        setMatrixPanel(); // Comment for testing
//        setComboBoxPanel(); // Comment for testing
//        setCurrencyGraphPanel();
//        setCGraphLabel();
//        setNegativeGraphPanel();
//        setNGraphLabel();
//        setMainPanel();
//        setFrame();
//    }
//
//    private void setSizeLabel() {
//        sizeLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
//        sizeLabel.setText("No. of Currencies:");
//    }
//
//    private void setMatrixSizeCB() {
//        for (int i = 0; i < matrixSize.length; i++) {
//            matrixSizeCB.addItem(matrixSize[i]);
//        }
//
//        selectedSize = (int) matrixSizeCB.getSelectedItem();
//        int size = selectedSize + 1; // Comment for testing
//        createComboBoxes(); // Comment for testing
//        createMatrix(size); // Comment for testing
//        createCurrencyGraph();
//        createNegativeGraph();
//
//        matrixSizeCB.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                selectedSize = (int) matrixSizeCB.getSelectedItem();
//                int size = selectedSize + 1; // Comment for testing
//                createComboBoxes(); // Comment for testing
//                createMatrix(size); // Comment for testing
//                createCurrencyGraph();
//                createNegativeGraph();
//            }
//        });
//    }
//
//    private void setInputsPanel() {
//        inputsPanel.setBounds(0, 10, 200, 30);
//        inputsPanel.setLayout(new FlowLayout());
//
//        inputsPanel.add(sizeLabel);
//        inputsPanel.add(matrixSizeCB);
//    }
//
//    private void setMatrixPanel() {
//        matrixPanel.setBounds(220, 10, 720, 280);
//        matrixPanel.setLayout(new FlowLayout());
//
//        matrixPanel.add(matrix);
//    }
//
//    private void setComboBoxPanel() {
//        comboboxPanel.setBounds(10, 50, 200, 280);
//    }
//
//    private void setCurrencyGraphPanel() {
//        currencyGraphPanel.setBounds(0, 370, 450, 450);
//        currencyGraphPanel.setLayout(null);
//    }
//    
//    private void setCGraphLabel() {
//        cGraphLabel.setBounds(130, 350, 300, 17);
//        cGraphLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
//        cGraphLabel.setText("Currency Exchange Graph");
//    }
//    
//    private void setNegativeGraphPanel() {
//        negativeGraphPanel.setBounds(450, 370, 450, 450);
//        negativeGraphPanel.setLayout(null);
//    }
//    
//    private void setNGraphLabel() {
//        nGraphLabel.setBounds(580, 350, 300, 17);
//        nGraphLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
//        nGraphLabel.setText("Negative Logarithm Graph");
//    }
//
//    private void setMainPanel() {
//        mainPanel.setPreferredSize(new Dimension(width, height));
//        mainPanel.setLayout(null);
//
//        mainPanel.add(inputsPanel);
//        mainPanel.add(comboboxPanel); // Comment for testing
//        mainPanel.add(matrixPanel); // Comment for testing
//        mainPanel.add(currencyGraphPanel);
//        mainPanel.add(cGraphLabel);
//        mainPanel.add(negativeGraphPanel);
//        mainPanel.add(nGraphLabel);
//    }
//
//    private void setFrame() {
//        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//        setTitle("Currency Exchange");
//        setBackground(Color.WHITE);
//        setLocation(((screenWidth / 2) - (width / 2)), ((screenHeight / 2) - (height / 2)));
//        setResizable(false);
//        getContentPane().add(mainPanel);
//        pack();
//
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                dispose();
//
//                System.exit(0);
//            }
//        });
//
//        setVisible(true);
//    }
//
//    private void createMatrix(int size) {
//        if (matrixArray != null) {
//            matrixArray = null;
//        }
//
//        if (ratesArray != null) {
//            ratesArray = null;
//        }
//
//        matrixArray = new JLabel[selectedSize][selectedSize];
//        ratesArray = new Rate[selectedSize][selectedSize];
//        matrix.removeAll();
//        matrix.setLayout(new GridLayout(size, size));
//        String matrixKey = "From/To";
//        calculateExchangeRates();
//
//        for (int row = 0; row < size; row++) {
//            for (int col = 0; col < size; col++) {
//                JPanel cellPanel = new JPanel();
//                cellPanel.setPreferredSize(new Dimension(80, 30));
//                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//
//                if (row == 0 && col == 0) {
//                    JLabel label = new JLabel(matrixKey);
//                    label.setFont(new Font("Segoe UI", Font.BOLD, 12));
//                    cellPanel.add(label);
//                } else if (row == 0 && col != 0) {
//                    JLabel label = new JLabel(labels[col - 1]);
//                    label.setFont(new Font("Segoe UI", Font.BOLD, 15));
//                    cellPanel.add(label);
//                } else if (row != 0 && col == 0) {
//                    JLabel label = new JLabel(labels[row - 1]);
//                    label.setFont(new Font("Segoe UI", Font.BOLD, 15));
//                    cellPanel.add(label);
//                } else {
//                    JLabel label = new JLabel();
//                    String text = "";
//
//                    if ((row - 1) < selectedSize && (col - 1) < selectedSize
//                            && ratesArray[(row - 1)][(col - 1)] != null && ratesArray[(row - 1)][(col - 1)].rate != 0.0) {
//                        text += String.format("%.3f", ratesArray[(row - 1)][(col - 1)].rate);
//                    }
//
//                    label.setText(text);
//                    label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
//                    cellPanel.add(label);
//
//                    if (row < selectedSize && col < selectedSize) {
//                        matrixArray[row][col] = label;
//                    }
//                }
//
//                matrix.add(cellPanel);
//            }
//        }
//
//        matrix.revalidate();
//    }
//
//    private void createComboBoxes() {
//            if (cbList != null) {
//            cbList = null;
//        }
//
//        cbList = new JComboBox[selectedSize];
//        comboboxPanel.removeAll();
//        comboboxPanel.setLayout(new GridLayout(selectedSize, 1));
//
//        for (int i = 0; i < selectedSize; i++) {
//            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//            JLabel label = new JLabel("Currency " + labels[i] + ":");
//            label.setFont(new Font("Segoe UI", Font.BOLD, 15));
//            panel.add(label);
//
//            JComboBox<String> comboBox = new JComboBox<>();
//
//            for (Map.Entry<String, String> entry : sortedCurrencyNames) {
//                comboBox.addItem(entry.getKey());
//            }
//
//            cbList[i] = comboBox;
//
//            comboBox.setSelectedIndex(-1);
//            Dimension comboBoxSize = new Dimension(80, 30);
//            comboBox.setPreferredSize(comboBoxSize);
//            panel.add(comboBox);
//            comboboxPanel.add(panel);
//
//            comboBox.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    int size = selectedSize + 1;
//                    createMatrix(size);
//                }
//            });
//        }
//    }
//
//    private void getCurrencyNames() {
//        try {
//            url = new URL(apiSymbolsURL);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            int responseCode = connection.getResponseCode();
//
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                StringBuilder response = new StringBuilder();
//                String line;
//
//                while ((line = br.readLine()) != null) {
//                    response.append(line);
//                }
//
//                br.close();
//
//                JSONObject jsonResponse = new JSONObject(response.toString());
//                JSONObject symbols = jsonResponse.getJSONObject("symbols");
//
//                for (String key : symbols.keySet()) {
//                    currencyNameMap.put(key, symbols.getString(key));
//                }
//
//                sortedCurrencyNames = new ArrayList<>(currencyNameMap.entrySet());
//                Collections.sort(sortedCurrencyNames, Map.Entry.comparingByKey());
//            } else {
//                System.err.println("API request failed with response code: " + responseCode);
//            }
//
//            connection.disconnect();
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void getRates() {
//        try {
//            url = new URL(apiRatesURL);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            int responseCode = connection.getResponseCode();
//
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                StringBuilder response = new StringBuilder();
//                String line;
//
//                while ((line = br.readLine()) != null) {
//                    response.append(line);
//                }
//
//                br.close();
//
//                JSONObject jsonResponse = new JSONObject(response.toString());
//                JSONObject symbols = jsonResponse.getJSONObject("rates");
//
//                for (String key : symbols.keySet()) {
//                    double value = symbols.getDouble(key);
//                    currencyRatesMap.put(key, value);
//                }
//
//                sortedCurrencyRates = new ArrayList<>(currencyRatesMap.entrySet());
//                Collections.sort(sortedCurrencyRates, Map.Entry.comparingByKey());
//
//                for (Map.Entry<String, Double> entry : sortedCurrencyRates) {
//                    System.out.println(entry.getKey() + ": " + entry.getValue());
//                }
//            } else {
//                System.err.println("API request failed with response code: " + responseCode);
//            }
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void calculateExchangeRates() {
//        for (int row = 0; row < selectedSize; row++) {
//            for (int col = 0; col < selectedSize; col++) {
//                Rate exRate;
//
//                if (cbList[row].getSelectedItem() != null && cbList[col].getSelectedItem() != null) {
//                    String currencyA = (String) cbList[row].getSelectedItem();
//                    double fromEUDA = currencyRatesMap.get(currencyA);
//
//                    String currencyB = (String) cbList[col].getSelectedItem();
//                    double fromEUDB = currencyRatesMap.get(currencyB);
//
//                    double rate = (fromEUDB / fromEUDA);
//
//                    exRate = new Rate(labels[row], labels[col], rate);
//                } else {
//                    double rate = 0.0;
//
//                    if (row == col) {
//                        rate = 1.0;
//                    }
//
//                    exRate = new Rate(labels[row], labels[col], rate);
//                }
//
//                ratesArray[row][col] = exRate;
//            }
//        }
//    }
//    
//    private void createNegativeGraph() {
//    if (negativeGraph != null) {
//        negativeGraphPanel.remove(negativeGraph);
//    }
//
//    switch (selectedSize) {
//        case 3:
//            SizeThree three = new SizeThree();
//            negativeGraph = new Graph(three, ratesArray); // Pass 'ratesArray'
//
//            break;
//        case 4:
//            SizeFour four = new SizeFour();
//            negativeGraph = new Graph(four, ratesArray); // Pass 'ratesArray'
//
//            break;
//        case 5:
//            SizeFive five = new SizeFive();
//            negativeGraph = new Graph(five, ratesArray); // Pass 'ratesArray'
//
//            break;
//        case 6:
//            SizeSix six = new SizeSix();
//            negativeGraph = new Graph(six, ratesArray); // Pass 'ratesArray'
//
//            break;
//        case 7:
//            SizeSeven seven = new SizeSeven();
//            negativeGraph = new Graph(seven, ratesArray); // Pass 'ratesArray'
//
//            break;
//        case 8:
//            SizeEight eight = new SizeEight();
//            negativeGraph = new Graph(eight, ratesArray); // Pass 'ratesArray'
//
//            break;
//    }
//
//    negativeGraph.setBounds(0, 0, 450, 450);
//    negativeGraphPanel.add(negativeGraph);
//    negativeGraphPanel.revalidate();
//    negativeGraphPanel.repaint();
//}
//
//    private void createCurrencyGraph() {
//        if (currencyGraph != null) {
//            currencyGraphPanel.remove(currencyGraph);
//        }
//
//        switch (selectedSize) {
//            case 3:
//                SizeThree three = new SizeThree();
//                currencyGraph = new Graph(three, ratesArray); // Pass 'ratesArray'
//
//                break;
//            case 4:
//                SizeFour four = new SizeFour();
//                currencyGraph = new Graph(four, ratesArray); // Pass 'ratesArray'
//
//                break;
//            case 5:
//                SizeFive five = new SizeFive();
//                currencyGraph = new Graph(five, ratesArray); // Pass 'ratesArray'
//
//                break;
//            case 6:
//                SizeSix six = new SizeSix();
//                currencyGraph = new Graph(six, ratesArray); // Pass 'ratesArray'
//
//                break;
//            case 7:
//                SizeSeven seven = new SizeSeven();
//                currencyGraph = new Graph(seven, ratesArray); // Pass 'ratesArray'
//
//                break;
//            case 8:
//                SizeEight eight = new SizeEight();
//                currencyGraph = new Graph(eight, ratesArray); // Pass 'ratesArray'
//
//                break;
//        }
//
//        currencyGraph.setBounds(0, 0, 450, 450);
//        currencyGraphPanel.add(currencyGraph);
//        currencyGraphPanel.revalidate();
//        currencyGraphPanel.repaint();
//    }
//
//}
//package Currency_Exchange;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.GridLayout;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import javax.swing.BorderFactory;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.WindowConstants;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class Exchange extends JFrame {
//
//    private JLabel sizeLabel;
//    private JComboBox<Integer> matrixSizeCB;
//    private JPanel inputsPanel;
//    private JPanel matrix;
//    private JPanel matrixPanel;
//    private JPanel comboboxPanel;
//    private JPanel currencyGraphPanel;
//    private JLabel cGraphLabel;
//    private JPanel currencyGraph;
//    private JPanel negativeGraphPanel;
//    private JLabel nGraphLabel;
//    private JPanel negativeGraph;
//    private JPanel mainPanel;
//
//    protected final int screenWidth;
//    protected final int screenHeight;
//    protected final int width = 955;
//    protected final int height = 950;
//    private final Toolkit toolKit;
//    private final Dimension screenDim;
//    protected String apiKey = "a7ab00cb947f9df98b98bbde56720152";
//    protected String apiSymbolsURL = "http://api.exchangeratesapi.io/v1/symbols?access_key=" + apiKey;
//    protected String apiRatesURL = "http://api.exchangeratesapi.io/v1/latest?access_key=" + apiKey;
//    protected URL url;
//    protected BufferedReader br;
//    protected Map<String, String> currencyNameMap = new HashMap<>();
//    protected List<Map.Entry<String, String>> sortedCurrencyNames;
//    protected Map<String, Double> currencyRatesMap = new HashMap<>();
//    protected List<Map.Entry<String, Double>> sortedCurrencyRates;
//    protected ArrayList<JComboBox<String>> currencyCBList;
//    protected JComboBox<String>[] cbList;
//    protected final int[] matrixSize = {3, 4, 5, 6, 7, 8};
//    protected final String[] labels = {"A", "B", "C", "D", "E", "F", "G", "H"};
//    protected JLabel[][] matrixArray;
//    protected Rate[][] ratesArray;
//    protected int selectedSize;
//    protected String baseCurrencyOne = "";
//    protected String baseCurrencyTwo = "";
//    protected String[] selectedCurrency;
//    // Add this field to store selected currencies
//    private String[][] selectedCurrencies;
//
//    public Exchange() {
//        this.toolKit = Toolkit.getDefaultToolkit();
//        this.screenDim = toolKit.getScreenSize();
//        this.screenWidth = screenDim.width;
//        this.screenHeight = screenDim.height;
//        getCurrencyNames(); // Comment for testing
//        getRates(); // Comment for testing
//        setComponents();
//    }
//
//    private void setComponents() {
//        sizeLabel = new JLabel();
//        matrixSizeCB = new JComboBox<>();
//        inputsPanel = new JPanel();
//        matrix = new JPanel(); // Comment for testing
//        matrixPanel = new JPanel(); // Comment for testing
//        comboboxPanel = new JPanel(); // Comment for testing
//        currencyGraphPanel = new JPanel();
//        cGraphLabel = new JLabel();
//        negativeGraphPanel = new JPanel();
//        nGraphLabel = new JLabel();
//        mainPanel = new JPanel();
//
//        setSizeLabel();
//        setMatrixSizeCB();
//        setInputsPanel();
//        setMatrixPanel(); // Comment for testing
//        setComboBoxPanel(); // Comment for testing
//        setCurrencyGraphPanel();
//        setCGraphLabel();
//        setNegativeGraphPanel();
//        setNGraphLabel();
//        setMainPanel();
//        setFrame();
//    }
//
//    private void setSizeLabel() {
//        sizeLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
//        sizeLabel.setText("No. of Currencies:");
//    }
//
//    private void setMatrixSizeCB() {
//        for (int i = 0; i < matrixSize.length; i++) {
//            matrixSizeCB.addItem(matrixSize[i]);
//        }
//
//        selectedSize = (int) matrixSizeCB.getSelectedItem();
//        int size = selectedSize + 1; // Comment for testing
//        createComboBoxes(); // Comment for testing
//        createMatrix(size); // Comment for testing
//        createCurrencyGraph();
//        createNegativeGraph();
//
//        matrixSizeCB.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                selectedSize = (int) matrixSizeCB.getSelectedItem();
//                int size = selectedSize + 1; // Comment for testing
//                createComboBoxes(); // Comment for testing
//                createMatrix(size); // Comment for testing
//                createCurrencyGraph();
//                createNegativeGraph();
//            }
//        });
//    }
//
//    private void setInputsPanel() {
//        inputsPanel.setBounds(0, 10, 200, 30);
//        inputsPanel.setLayout(new FlowLayout());
//
//        inputsPanel.add(sizeLabel);
//        inputsPanel.add(matrixSizeCB);
//    }
//
//    private void setMatrixPanel() {
//        matrixPanel.setBounds(220, 10, 720, 280);
//        matrixPanel.setLayout(new FlowLayout());
//
//        matrixPanel.add(matrix);
//    }
//
// private void setComboBoxPanel() {
//        comboboxPanel.setBounds(10, 50, 200, 280);
//    }
// 
//    private void setCurrencyGraphPanel() {
//        currencyGraphPanel.setBounds(0, 475, 955, 200);
//        currencyGraphPanel.setLayout(new FlowLayout());
//    }
//
//    private void setCGraphLabel() {
//        cGraphLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
//        cGraphLabel.setText("Currency Exchange Graph:");
//    }
//
//    private void setNegativeGraphPanel() {
//        negativeGraphPanel.setBounds(0, 700, 955, 200);
//        negativeGraphPanel.setLayout(new FlowLayout());
//    }
//
//    private void setNGraphLabel() {
//        nGraphLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
//        nGraphLabel.setText("Negative Graph:");
//    }
//
//    private void setMainPanel() {
//        mainPanel.setLayout(null);
//        mainPanel.add(inputsPanel);
//        mainPanel.add(matrixPanel);
//        mainPanel.add(comboboxPanel);
//        mainPanel.add(currencyGraphPanel);
//        mainPanel.add(cGraphLabel);
//        mainPanel.add(negativeGraphPanel);
//        mainPanel.add(nGraphLabel);
//
//        add(mainPanel);
//    }
//
//    private void setFrame() {
//        setTitle("Currency Exchange Rate Graph");
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
//        setResizable(false);
//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
//        setVisible(true);
//    }
//
//  private void createComboBoxes() {
//        if (cbList != null) {
//            cbList = null;
//        }
//
//        cbList = new JComboBox[selectedSize];
//        comboboxPanel.removeAll();
//        comboboxPanel.setLayout(new GridLayout(selectedSize, 1));
//
//        for (int i = 0; i < selectedSize; i++) {
//            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//            JLabel label = new JLabel("Currency " + labels[i] + ":");
//            label.setFont(new Font("Segoe UI", Font.BOLD, 15));
//            panel.add(label);
//
//            JComboBox<String> comboBox = new JComboBox<>();
//
//            for (Map.Entry<String, String> entry : sortedCurrencyNames) {
//                comboBox.addItem(entry.getKey());
//            }
//
//            cbList[i] = comboBox;
//
//            comboBox.setSelectedIndex(-1);
//            Dimension comboBoxSize = new Dimension(80, 30);
//            comboBox.setPreferredSize(comboBoxSize);
//            panel.add(comboBox);
//            comboboxPanel.add(panel);
//
//            comboBox.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    int size = selectedSize + 1;
//                    createMatrix(size);
//                }
//            });
//        }
//    }
//  
//   private void getCurrencyNames() {
//        try {
//            url = new URL(apiSymbolsURL);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            int responseCode = connection.getResponseCode();
//
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                StringBuilder response = new StringBuilder();
//                String line;
//
//                while ((line = br.readLine()) != null) {
//                    response.append(line);
//                }
//
//                br.close();
//
//                JSONObject jsonResponse = new JSONObject(response.toString());
//                JSONObject symbols = jsonResponse.getJSONObject("symbols");
//
//                for (String key : symbols.keySet()) {
//                    currencyNameMap.put(key, symbols.getString(key));
//                }
//
//                sortedCurrencyNames = new ArrayList<>(currencyNameMap.entrySet());
//                Collections.sort(sortedCurrencyNames, Map.Entry.comparingByKey());
//            } else {
//                System.err.println("API request failed with response code: " + responseCode);
//            }
//
//            connection.disconnect();
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void getRates() {
//        try {
//            url = new URL(apiRatesURL);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            int responseCode = connection.getResponseCode();
//
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                StringBuilder response = new StringBuilder();
//                String line;
//
//                while ((line = br.readLine()) != null) {
//                    response.append(line);
//                }
//
//                br.close();
//
//                JSONObject jsonResponse = new JSONObject(response.toString());
//                JSONObject symbols = jsonResponse.getJSONObject("rates");
//
//                for (String key : symbols.keySet()) {
//                    double value = symbols.getDouble(key);
//                    currencyRatesMap.put(key, value);
//                }
//
//                sortedCurrencyRates = new ArrayList<>(currencyRatesMap.entrySet());
//                Collections.sort(sortedCurrencyRates, Map.Entry.comparingByKey());
//
//                for (Map.Entry<String, Double> entry : sortedCurrencyRates) {
//                    System.out.println(entry.getKey() + ": " + entry.getValue());
//                }
//            } else {
//                System.err.println("API request failed with response code: " + responseCode);
//            }
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void createMatrix(int size) {
//        if (matrixArray != null) {
//            matrixArray = null;
//        }
//
//        if (ratesArray != null) {
//            ratesArray = null;
//        }
//
//        matrixArray = new JLabel[size][size];
//        ratesArray = new Rate[size][size];
//        matrix.removeAll();
//        matrix.setLayout(new GridLayout(size, size));
//        String matrixKey = "From/To";
//
//        for (int row = 0; row < size; row++) {
//            for (int col = 0; col < size; col++) {
//                JPanel cellPanel = new JPanel();
//                cellPanel.setPreferredSize(new Dimension(80, 30));
//                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//
//                if (row == 0 && col == 0) {
//                    JLabel label = new JLabel(matrixKey);
//                    label.setFont(new Font("Segoe UI", Font.BOLD, 15));
//                    cellPanel.add(label);
//                } else if (row == 0 && col != 0) {
//                    JLabel label = new JLabel(labels[col - 1]);
//                    label.setFont(new Font("Segoe UI", Font.BOLD, 15));
//                    cellPanel.add(label);
//                } else if (row != 0 && col == 0) {
//                    JLabel label = new JLabel(labels[row - 1]);
//                    label.setFont(new Font("Segoe UI", Font.BOLD, 15));
//                    cellPanel.add(label);
//                } else {
//                    JLabel label = new JLabel();
//                    String text = "";
//
//                    if ((row - 1) < selectedSize && (col - 1) < selectedSize
//                            && ratesArray[(row - 1)][(col - 1)] != null && ratesArray[(row - 1)][(col - 1)].rate != 0.0) {
//                        // Update selectedCurrencies based on ComboBox selections
//                        selectedCurrencies[row - 1][col - 1] = String.format("%.3f", ratesArray[(row - 1)][(col - 1)].rate);
//                        selectedCurrencies[col - 1][row - 1] = String.format("%.3f", ratesArray[(col - 1)][(row - 1)].rate);
//                        text += String.format("%.3f", ratesArray[(row - 1)][(col - 1)].rate);
//
//                    }
//
//                    label.setText(text);
//                    label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
//                    cellPanel.add(label);
//
//                    if (row < size && col < size) {
//                        matrixArray[row][col] = label;
//                    }
//                }
//
//                matrix.add(cellPanel);
//            }
//        }
//
//        matrix.revalidate();
//
//        // Update the ratesArray based on selectedCurrencies
//        calculateExchangeRates();
//    }
//
//    private void calculateExchangeRates() {
//        for (int row = 0; row < selectedSize; row++) {
//            for (int col = 0; col < selectedSize; col++) {
//                Rate exRate;
//
//                if (cbList[row].getSelectedItem() != null && cbList[col].getSelectedItem() != null) {
//                    String currencyA = (String) cbList[row].getSelectedItem();
//                    double fromEUDA = currencyRatesMap.get(currencyA);
//
//                    String currencyB = (String) cbList[col].getSelectedItem();
//                    double fromEUDB = currencyRatesMap.get(currencyB);
//
//                    double rate = (fromEUDB / fromEUDA);
//
//                    exRate = new Rate(labels[row], labels[col], rate);
//                } else {
//                    exRate = null;
//                }
//
//                ratesArray[row][col] = exRate;
//            }
//        }
//    }
//
//    private void createCurrencyGraph() {
//        if (currencyGraph != null) {
//            currencyGraphPanel.remove(currencyGraph);
//            currencyGraph = null;
//        }
//        
//        switch (selectedSize) {
//            case 3:
//                SizeThree three = new SizeThree();
//                currencyGraph = new Graph(three, ratesArray); // Pass 'ratesArray'
//
//                break;
//            case 4:
//                SizeFour four = new SizeFour();
//                currencyGraph = new Graph(four, ratesArray); // Pass 'ratesArray'
//
//                break;
//            case 5:
//                SizeFive five = new SizeFive();
//                currencyGraph = new Graph(five, ratesArray); // Pass 'ratesArray'
//
//                break;
//            case 6:
//                SizeSix six = new SizeSix();
//                currencyGraph = new Graph(six, ratesArray); // Pass 'ratesArray'
//
//                break;
//            case 7:
//                SizeSeven seven = new SizeSeven();
//                currencyGraph = new Graph(seven, ratesArray); // Pass 'ratesArray'
//
//                break;
//            case 8:
//                SizeEight eight = new SizeEight();
//                currencyGraph = new Graph(eight, ratesArray); // Pass 'ratesArray'
//
//                break;
//        }
////        currencyGraph = new Graph(createShapeFromRatesArray(selectedSize), ratesArray); // Pass ratesArray
//        currencyGraphPanel.add(currencyGraph);
//        currencyGraphPanel.revalidate();
//    }
//
//    private void createNegativeGraph() {
//        if (negativeGraph != null) {
//            negativeGraphPanel.remove(negativeGraph);
//            negativeGraph = null;
//        }
//
//        negativeGraph = new Graph(createNegativeShapeFromRatesArray(selectedSize), ratesArray); // Pass ratesArray
//        negativeGraphPanel.add(negativeGraph);
//        negativeGraphPanel.revalidate();
//    }
//
//    private Shapes createShapeFromRatesArray(int size) {
//        Shapes shape = new Shapes(size);
//        shape.nodes = new Node[size];
//
//        for (int i = 0; i < size; i++) {
//            Node node = new Node(labels[i], i * 100, i * 100);
//            shape.nodes[i] = node;
//        }
//
//        return shape;
//    }
//
//    private Shapes createNegativeShapeFromRatesArray(int size) {
//        Shapes shape = new Shapes(size);
//        shape.nodes = new Node[size];
//
//        for (int i = 0; i < size; i++) {
//            Node node = new Node(labels[i], i * 100, i * 100);
//
//            shape.nodes[i] = node;
//        }
//
//        return shape;
//    }
//}

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
 * @author m4ria
 */
public class Exchange extends JFrame {

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
    private JPanel mainPanel;

    protected final int screenWidth;
    protected final int screenHeight;
    protected final int width = 955;
    protected final int height = 950;
    private final Toolkit toolKit;
    private final Dimension screenDim;
    protected final String apiKey = "a7ab00cb947f9df98b98bbde56720152";
    protected String apiSymbolsURL = "http://api.exchangeratesapi.io/v1/symbols?access_key=" + apiKey;
    protected String apiRatesURL = "http://api.exchangeratesapi.io/v1/latest?access_key=" + apiKey;
    protected URL url;
    protected BufferedReader br;
    protected Map<String, String> currencyNameMap = new HashMap<>();
    protected List<Map.Entry<String, String>> sortedCurrencyNames;
    protected Map<String, Double> currencyRatesMap = new HashMap<>();
    protected List<Map.Entry<String, Double>> sortedCurrencyRates;
    protected ArrayList<JComboBox<String>> currencyCBList;
    protected JComboBox<String>[] cbList;
    protected final int[] matrixSize = {3, 4, 5, 6, 7, 8};
    protected String matrixKey = "From/To";
    protected final String[] labels = {"A", "B", "C", "D", "E", "F", "G", "H"};
    protected JLabel[][] matrixArray;
    protected Rate[][] ratesArray;
    protected int selectedSize;
    protected Graph updatedGraph;
    protected Shapes shapes;

    public Exchange() {
        this.toolKit = Toolkit.getDefaultToolkit();
        this.screenDim = toolKit.getScreenSize();
        this.screenWidth = screenDim.width;
        this.screenHeight = screenDim.height;
        getCurrencyNames(); // Comment for testing
        getRates(); // Comment for testing
        setComponents();
    }

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
        mainPanel = new JPanel();
        updatedGraph = new Graph(shapes, ratesArray);

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

    private void setSizeLabel() {
        sizeLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        sizeLabel.setText("No. of Currencies:");
    }

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

        matrixSizeCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSize = (int) matrixSizeCB.getSelectedItem();
                int size = selectedSize + 1; // Comment for testing
                createComboBoxes(); // Comment for testing
                createMatrix(size); // Comment for testing
                createCurrencyGraph();
                createNegativeGraph();
            }
        });
    }

    private void setInputsPanel() {
        inputsPanel.setBounds(0, 10, 200, 30);
        inputsPanel.setLayout(new FlowLayout());

        inputsPanel.add(sizeLabel);
        inputsPanel.add(matrixSizeCB);
    }

    private void setMatrixPanel() {
        matrixPanel.setBounds(220, 10, 720, 280);
        matrixPanel.setLayout(new FlowLayout());

        matrixPanel.add(matrix);
    }

    private void setComboBoxPanel() {
        comboboxPanel.setBounds(10, 50, 200, 280);
    }

    private void setCurrencyGraphPanel() {
        currencyGraphPanel.setBounds(0, 370, 450, 450);
        currencyGraphPanel.setLayout(null);
    }

    private void setCGraphLabel() {
        cGraphLabel.setBounds(130, 350, 300, 17);
        cGraphLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        cGraphLabel.setText("Currency Exchange Graph");
    }

    private void setNegativeGraphPanel() {
        negativeGraphPanel.setBounds(450, 370, 450, 450);
        negativeGraphPanel.setLayout(null);
    }

    private void setNGraphLabel() {
        nGraphLabel.setBounds(580, 350, 300, 17);
        nGraphLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        nGraphLabel.setText("Negative Logarithm Graph");
    }

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
    }

    private void setFrame() {
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Currency Exchange");
        setBackground(Color.WHITE);
        setLocation(((screenWidth / 2) - (width / 2)), ((screenHeight / 2) - (height / 2)));
        setResizable(false);
        getContentPane().add(mainPanel);
        pack();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();

                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void createMatrix(int size) {
        if (matrixArray != null) {
            matrixArray = null;
        }

        if (ratesArray != null) {
            ratesArray = null;
        }

        matrixArray = new JLabel[selectedSize][selectedSize];
        ratesArray = new Rate[selectedSize][selectedSize];
        matrix.removeAll();
        matrix.setLayout(new GridLayout(size, size));
        calculateExchangeRates();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setPreferredSize(new Dimension(80, 30));
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                if (row == 0 && col == 0) {
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

                    if ((row - 1) < selectedSize && (col - 1) < selectedSize
                            && ratesArray[(row - 1)][(col - 1)] != null && ratesArray[(row - 1)][(col - 1)].rate != 0.0) {
                        text += String.format("%.3f", ratesArray[(row - 1)][(col - 1)].rate);
                    }

                    label.setText(text);
                    label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                    cellPanel.add(label);

                    if (row < selectedSize && col < selectedSize) {
                        matrixArray[row][col] = label;
                    }
                }

                matrix.add(cellPanel);
            }
        }

        matrix.revalidate();
    }

    private void createComboBoxes() {
        if (cbList != null) {
            cbList = null;
        }

        cbList = new JComboBox[selectedSize];
        comboboxPanel.removeAll();
        comboboxPanel.setLayout(new GridLayout(selectedSize, 1));

        for (int i = 0; i < selectedSize; i++) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel label = new JLabel("Currency " + labels[i] + ":");
            label.setFont(new Font("Segoe UI", Font.BOLD, 15));
            panel.add(label);

            JComboBox<String> comboBox = new JComboBox<>();

            for (Map.Entry<String, String> entry : sortedCurrencyNames) {
                comboBox.addItem(entry.getKey());
            }

            cbList[i] = comboBox;

            comboBox.setSelectedIndex(-1);
            Dimension comboBoxSize = new Dimension(80, 30);
            comboBox.setPreferredSize(comboBoxSize);
            panel.add(comboBox);
            comboboxPanel.add(panel);

            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int size = selectedSize + 1;
                    createMatrix(size);
                }
            });
        }
    }

    private void getCurrencyNames() {
        try {
            url = new URL(apiSymbolsURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    response.append(line);
                }

                br.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONObject symbols = jsonResponse.getJSONObject("symbols");

                for (String key : symbols.keySet()) {
                    currencyNameMap.put(key, symbols.getString(key));
                }

                sortedCurrencyNames = new ArrayList<>(currencyNameMap.entrySet());
                Collections.sort(sortedCurrencyNames, Map.Entry.comparingByKey());
            } else {
                System.err.println("API request failed with response code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void getRates() {
        try {
            url = new URL(apiRatesURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    response.append(line);
                }

                br.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONObject symbols = jsonResponse.getJSONObject("rates");

                for (String key : symbols.keySet()) {
                    double value = symbols.getDouble(key);
                    currencyRatesMap.put(key, value);
                }

                sortedCurrencyRates = new ArrayList<>(currencyRatesMap.entrySet());
                Collections.sort(sortedCurrencyRates, Map.Entry.comparingByKey());

                for (Map.Entry<String, Double> entry : sortedCurrencyRates) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            } else {
                System.err.println("API request failed with response code: " + responseCode);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void calculateExchangeRates() {
        for (int row = 0; row < selectedSize; row++) {
            for (int col = 0; col < selectedSize; col++) {
                Rate exRate;

                if (cbList[row].getSelectedItem() != null && cbList[col].getSelectedItem() != null) {
                    String currencyA = (String) cbList[row].getSelectedItem();
                    double fromEUDA = currencyRatesMap.get(currencyA);

                    String currencyB = (String) cbList[col].getSelectedItem();
                    double fromEUDB = currencyRatesMap.get(currencyB);

                    double rate = (fromEUDB / fromEUDA);

                    exRate = new Rate(labels[row], labels[col], rate);

                    if (row != col) {
                    System.out.println("Calculated rate for " + labels[row] + " to " + labels[col] + ": " + rate);

                    }
                } else {
                    exRate = null;
                }

                ratesArray[row][col] = exRate;
            }
        }
        updateGraphWithNewRates();

    }

    private void createNegativeGraph() {
        if (negativeGraph != null) {
            negativeGraphPanel.remove(negativeGraph);
        }

        switch (selectedSize) {
            case 3:
                SizeThree three = new SizeThree();
                negativeGraph = new Graph(three, ratesArray);

                if (ratesArray[(selectedSize - 1)][(selectedSize - 1)] != null) {
                    // Call bellmanford function here
                }

                break;
            case 4:
                SizeFour four = new SizeFour();
                negativeGraph = new Graph(four, ratesArray);

                if (ratesArray[(selectedSize - 1)][(selectedSize - 1)] != null) {
                    // Call bellmanford function here
                }

                break;
            case 5:
                SizeFive five = new SizeFive();
                negativeGraph = new Graph(five, ratesArray);

                if (ratesArray[(selectedSize - 1)][(selectedSize - 1)] != null) {
                    // Call bellmanford function here
                }

                break;
            case 6:
                SizeSix six = new SizeSix();
                negativeGraph = new Graph(six, ratesArray);

                if (ratesArray[(selectedSize - 1)][(selectedSize - 1)] != null) {
                    // Call bellmanford function here
                }

                break;
            case 7:
                SizeSeven seven = new SizeSeven();
                negativeGraph = new Graph(seven, ratesArray);

                if (ratesArray[(selectedSize - 1)][(selectedSize - 1)] != null) {
                    // Call bellmanford function here
                }

                break;
            case 8:
                SizeEight eight = new SizeEight();
                negativeGraph = new Graph(eight, ratesArray);

                if (ratesArray[(selectedSize - 1)][(selectedSize - 1)] != null) {
                    // Call bellmanford function here
                }

                break;
        }

        negativeGraph.setBounds(0, 0, 450, 450);
        negativeGraphPanel.add(negativeGraph);
        negativeGraphPanel.revalidate();
        negativeGraphPanel.repaint();
    }

    private void createCurrencyGraph() {
        if (currencyGraph != null) {
            currencyGraphPanel.remove(currencyGraph);
        }

        switch (selectedSize) {
            case 3:
                SizeThree three = new SizeThree();
                currencyGraph = new Graph(three, ratesArray);

                break;
            case 4:
                SizeFour four = new SizeFour();
                currencyGraph = new Graph(four, ratesArray);

                break;
            case 5:
                SizeFive five = new SizeFive();
                currencyGraph = new Graph(five, ratesArray);

                break;
            case 6:
                SizeSix six = new SizeSix();
                currencyGraph = new Graph(six, ratesArray);

                break;
            case 7:
                SizeSeven seven = new SizeSeven();
                currencyGraph = new Graph(seven, ratesArray);

                break;
            case 8:
                SizeEight eight = new SizeEight();
                currencyGraph = new Graph(eight, ratesArray);

                break;
        }

        currencyGraph.setBounds(0, 0, 450, 450);
        currencyGraphPanel.add(currencyGraph);
        currencyGraphPanel.revalidate();
        currencyGraphPanel.repaint();
    }

    public void updateGraphWithNewRates() {
        if (currencyGraph != null) {
            System.out.println("Being Called");
            updatedGraph.updateRatesArray(ratesArray);
        }
    }
}
