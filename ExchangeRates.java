/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Currency_Exchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author snipi
 */
public class ExchangeRates {

    // Variables for exchange rates api
    protected final String apiKey = "a7ab00cb947f9df98b98bbde56720152";
    protected final String backupKey = "117ac3049df8def7d50053fd5700515c";
    protected String apiSymbolsURL = "http://api.exchangeratesapi.io/v1/symbols?access_key=" + backupKey;
    protected String apiRatesURL = "http://api.exchangeratesapi.io/v1/latest?access_key=" + backupKey;
    protected URL url;
    protected BufferedReader br;
    protected Exchange ex;
    protected BellmanFord bf;
    protected String[] countryNames;

    // Constructor for initializing the variables needed for the exchange rates api
    public ExchangeRates(Exchange ex) {
        this.ex = ex;
        this.bf = new BellmanFord(this.ex);
        getCurrencyNames();
        getRates();
    }

    // Function to retrieve the 3-letter code for all the currencies from the
    // exchange rates api
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
                    ex.currencyNameMap.put(key, symbols.getString(key));
                }

                ex.sortedCurrencyNames = new ArrayList<>(ex.currencyNameMap.entrySet());
                Collections.sort(ex.sortedCurrencyNames, Map.Entry.comparingByKey());
            } else {
                System.err.println("API request failed with response code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    // Function to retrieve the exchange rates from EUD to each of the currencies
    // available with the exchange rates api
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
                    ex.currencyRatesMap.put(key, value);
                }

                ex.sortedCurrencyRates = new ArrayList<>(ex.currencyRatesMap.entrySet());
                Collections.sort(ex.sortedCurrencyRates, Map.Entry.comparingByKey());
            } else {
                System.err.println("API request failed with response code: " + responseCode);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    // Function to recalculate the exchange rates from EUD being the base to the
    // base currency that the user has selected
    public void calculateExchangeRates() {
        String keyBase = "";
        countryNames = new String[ex.selectedSize];
        for (int row = 0; row < ex.selectedSize; row++) {
            for (int col = 0; col < ex.selectedSize; col++) {
                Rate exRate;

                // Creates a Rate object based on the currencies the user has selected
                // if any otherwise sets the entry as null
                if (ex.cbList[row].getSelectedItem() != null && ex.cbList[col].getSelectedItem() != null) {
                    String currencyA = (String) ex.cbList[row].getSelectedItem();
                    keyBase = currencyA;
                    double fromEUDA = ex.currencyRatesMap.get(currencyA);

                    String currencyB = (String) ex.cbList[col].getSelectedItem();
                    double fromEUDB = ex.currencyRatesMap.get(currencyB);

                    double rate = Rounded((fromEUDB / fromEUDA));

                    exRate = new Rate(ex.labelIndex[row], ex.labelIndex[col], rate);

                } else {
                    exRate = null;
                }

                ex.ratesArray[row][col] = exRate;
            }

            ex.currencyLabelKey.put(ex.labels[row], keyBase);
        }
        
        // Adds selected country to an array
        for (int i = 0; i < ex.selectedSize; i++) {
            if (ex.cbList[i].getSelectedItem() != null) {
                countryNames[i] = (String) ex.cbList[i].getSelectedItem();
            }

        }
        
        // Boolean used to check if all currency selections are filled
        boolean fullMatrix = true;
        
        // Loop through the array of comboboxes and check all selections are filled
        for (JComboBox current : ex.cbList) {
            if (current.getSelectedItem() == null) {
                fullMatrix = false;
            }
        }
        
        if (fullMatrix) {
            getNegativeLogValues();
            
            // Reset the printed strings
            ex.path = "";
            ex.cycle = "";

            // Finds the path from the BellmanFord algorithm and adds to string label
            List<Integer> path = bf.FindPath(ex.ratesArray);
            if (path != null) {
                for (int i = 0; i < path.size(); i++) {
                    if (i == path.size() - 1) {
                        ex.path += countryNames[path.get(i)] + " ";
                    } else {
                        ex.path += countryNames[path.get(i)] + "-> ";
                    }
                }
            }

            ex.setAnswerLabel();
        }
    }

    // Calculates the negative logarithm values of each exchange rate and saves
    // the values to a Rate array 
    private void getNegativeLogValues() {
        if (ex.ratesArray[(ex.selectedSize - 1)][(ex.selectedSize - 1)] != null) {
            for (int i = 0; i < ex.selectedSize; i++) {
                for (int j = 0; j < ex.selectedSize; j++) {
                    double rounded = Rounded(-(Math.log10(ex.ratesArray[i][j].rate)));
                    ex.negativeValues[i][j] = new Rate(ex.ratesArray[i][j].fromIndex, ex.ratesArray[i][j].toIndex, rounded);
                }
            }
        }
    }
    
    // Rounds the rate values to 3DP
    public double Rounded(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        return Double.parseDouble(decimalFormat.format(value));
    }
}
