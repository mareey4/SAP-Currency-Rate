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

    private JLabel sizeLabel;
    private JComboBox<Integer> matrixSizeCB;
    private JPanel inputsPanel;
    private JPanel matrix;
    private JPanel matrixPanel;
    private JPanel comboboxPanel;
    private JPanel mainPanel;

    protected final int screenWidth;
    protected final int screenHeight;
    protected final int width = 1300;
    protected final int height = 900;
    private final Toolkit toolKit;
    private final Dimension screenDim;
    protected String apiKey = "a7ab00cb947f9df98b98bbde56720152";
    protected String apiSymbolsURL = "http://api.exchangeratesapi.io/v1/symbols?access_key=" + apiKey;
    protected String apiRatesURL = "http://api.exchangeratesapi.io/v1/latest?access_key=" + apiKey + "&base=";
    URL url;
    protected BufferedReader br;
    protected Map<String, String> currencyNameMap = new HashMap<>();
    protected List<Map.Entry<String, String>> sortedCurrencyNames;
    protected Map<String, Double> currencyRatesMap = new HashMap<>();
    protected List<Map.Entry<String, Double>> sortedCurrencyRates;
    protected ArrayList<JComboBox<String>> currencyCBList;
    protected Map<Integer, JComboBox<String>> cbList = new HashMap<>();
    protected int[] matrixSize = {3, 4, 5, 6, 7, 8};
    protected int selectedSize;
    protected String baseCurrency = "";
    protected String[] selectedCurrency;

    public Exchange() {
        this.toolKit = Toolkit.getDefaultToolkit();
        this.screenDim = toolKit.getScreenSize();
        this.screenWidth = screenDim.width;
        this.screenHeight = screenDim.height;
        getCurrencyNames();
        setComponents();
    }

    private void setComponents() {
        sizeLabel = new JLabel();
        matrixSizeCB = new JComboBox<>();
        inputsPanel = new JPanel();
        matrix = new JPanel();
        matrixPanel = new JPanel();
        comboboxPanel = new JPanel();
        mainPanel = new JPanel();

        setSizeLabel();
        setMatrixSizeCB();
        setInputsPanel();
        setMatrixPanel();
        setComboBoxPanel();
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
        int size = selectedSize + 1;
        createMatrix(size);
        createComboBoxes();

        matrixSizeCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSize = (int) matrixSizeCB.getSelectedItem();
                int size = selectedSize + 1;
                createMatrix(size);
                createComboBoxes();
            }
        });
    }

    private void setInputsPanel() {
        inputsPanel.setBounds(10, 10, 200, 30);
        inputsPanel.setLayout(new FlowLayout());

        inputsPanel.add(sizeLabel);
        inputsPanel.add(matrixSizeCB);
    }

    private void setMatrixPanel() {
        matrixPanel.setBounds(220, 10, 450, 280);
        matrixPanel.setLayout(new FlowLayout());

        matrixPanel.add(matrix);
    }

    private void setComboBoxPanel() {
        comboboxPanel.setBounds(10, 50, 200, 250);
    }

    private void setMainPanel() {
        mainPanel.setPreferredSize(new Dimension(width, height));
        mainPanel.setLayout(null);

        mainPanel.add(inputsPanel);
        mainPanel.add(comboboxPanel);
        mainPanel.add(matrixPanel);
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
        matrix.removeAll();
        matrix.setLayout(new GridLayout(size, size));
        String matrixKey = "From/To";
        String[] labels = {"A", "B", "C", "D", "E", "F", "G", "H"};

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setPreferredSize(new Dimension(50, 30));
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
                } else if (row != 0 && col != 0 && row == col) {
                    JLabel label = new JLabel("1");
                    label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                    cellPanel.add(label);
                } else {
                    JLabel label = new JLabel();
                    String text = "";

                    // Working on logic to display exchange rates here

                    label.setText(text);
                    label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                    cellPanel.add(label);
                }

                matrix.add(cellPanel);
            }
        }

        matrix.revalidate();
    }

    private void createComboBoxes() {
        comboboxPanel.removeAll();
        comboboxPanel.setLayout(new GridLayout(selectedSize, 1));
        String[] labels = {"A", "B", "C", "D", "E", "F", "G", "H"};

        for (int i = 0; i < selectedSize; i++) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel label = new JLabel("Currency " + labels[i] + ":");
            label.setFont(new Font("Segoe UI", Font.BOLD, 15));
            panel.add(label);

            JComboBox<String> comboBox = new JComboBox<>();

            for (Map.Entry<String, String> entry : sortedCurrencyNames) {
                comboBox.addItem(entry.getKey());
            }

            cbList.put(i, comboBox);

            comboBox.setSelectedIndex(-1);
            Dimension comboBoxSize = new Dimension(80, 30);
            comboBox.setPreferredSize(comboBoxSize);
            panel.add(comboBox);
            comboboxPanel.add(panel);

            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox<String> selectedComboBox = (JComboBox<String>) e.getSource();
                    String selectedCurrency = (String) selectedComboBox.getSelectedItem();

                    if (selectedComboBox.equals(cbList.get(0))) {
                        baseCurrency = selectedCurrency;
                        getRates(baseCurrency);
                    }

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

    private void getRates(String currencyCode) {
        try {
            url = new URL(apiRatesURL + currencyCode);
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
            } else {
                System.err.println("API request failed with response code: " + responseCode);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
