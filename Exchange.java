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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author snipi
 */
public class ExchangeRates {

    // Variables for exchange rates api
    protected final String apiKey = "a7ab00cb947f9df98b98bbde56720152";
    protected String apiSymbolsURL = "http://api.exchangeratesapi.io/v1/symbols?access_key=" + apiKey;
    protected String apiRatesURL = "http://api.exchangeratesapi.io/v1/latest?access_key=" + apiKey;
    protected URL url;
    protected BufferedReader br;
    protected Exchange ex;
    protected BellmanFord bf;

    // Constructor for initializing the variables needed for the exchange rates api
    public ExchangeRates(Exchange ex) {
        this.ex = ex;
        this.bf = new BellmanFord();
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

//                for (Map.Entry<String, Double> entry : sortedCurrencyRates) {
//                    System.out.println(entry.getKey() + ": " + entry.getValue());
//                }
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

                    double rate = (fromEUDB / fromEUDA);

                    exRate = new Rate(ex.labelIndex[row], ex.labelIndex[col], rate);

                } else {
                    exRate = null;
                }

                ex.ratesArray[row][col] = exRate;
            }
            ex.currencyLabelKey.put(ex.labels[row], keyBase);
        }

        if (ex.ratesArray[(ex.selectedSize - 1)][ex.selectedSize - 1] != null) {
            List<Integer> path = bf.FindPath(ex.ratesArray);
            getNegativeLogValues();
            if (path != null) {
                for (int i = 0; i < path.size(); i++) {
                    System.out.print(path.get(i) + " ");
                }
            }
        }
    }

    // Calculates the negative logarithm values of each exchange rate and saves
    // the values to a Rate array 
    private void getNegativeLogValues() {
        if (ex.ratesArray[(ex.selectedSize - 1)][(ex.selectedSize - 1)] != null) {
            for (int i = 0; i < ex.selectedSize; i++) {
                for (int j = 0; j < ex.selectedSize; j++) {
                    double rounded = bf.Rounded(-(Math.log10(ex.ratesArray[i][j].rate)));
                    ex.negativeValues[i][j] = new Rate(ex.ratesArray[i][j].fromIndex, ex.ratesArray[i][j].toIndex, rounded);
                }
            }
        }
    }
}
