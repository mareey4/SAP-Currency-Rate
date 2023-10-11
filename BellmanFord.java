/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Currency_Exchange;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author m4ria
 */
// Reference code for Bellman Ford Algorithm in Java
public class BellmanFord{


    // Representation of the infinity distance
    private static final double INF = Double.MAX_VALUE;

    public double[] bellmanFordWithCycles(Rate[][] rate, int s) {
        int N = rate.length;
        double[] d = new double[N];
        for (int i = 0; i < N; i++) {
            d[i] = INF;
        }
    
        d[s] = 0.0;
    
        // Relax edges repeatedly, allowing negative cycles
        for (int i = 0; i < N; i++) {
            for (int u = 0; u < N; u++) {
                for (int v = 0; v < N; v++) {
                    if (rate[v][u].rate != INF && d[v] != INF && d[v] + rate[v][u].rate < d[u]) {
                        d[u] = d[v] + rate[v][u].rate;
                    }
                }
            }
        }
    
        // Check for negative-weight cycles and mark nodes in such cycles with -INF
        for (int i = 0; i < N; i++) {
            for (int u = 0; u < N; u++) {
                for (int v = 0; v < N; v++) {
                    if (rate[v][u].rate != INF && d[v] != INF && d[v] + rate[v][u].rate < d[u]) {
                        d[u] = Double.NEGATIVE_INFINITY;
                    }
                }
            }
        }
    
        return d;
    }
    
    public static List<Rate> findNegativeCycle(Rate[][] rates) {
    int numCountries = rates.length;
    
    // Initialize the distance matrix with exchange rates
    double[][] distances = new double[numCountries][numCountries];
    int[][] next = new int[numCountries][numCountries];

    for (int i = 0; i < numCountries; i++) {
        for (int j = 0; j < numCountries; j++) {
            distances[i][j] = -Math.log(rates[i][j].rate);
            next[i][j] = j; // Initialize "next" array for reconstructing the path
        }
    }

    List<Rate> negativeCycle = new ArrayList<>();

    // Floyd-Warshall algorithm to detect negative cycles and record the path
    for (int k = 0; k < numCountries; k++) {
        for (int i = 0; i < numCountries; i++) {
            for (int j = 0; j < numCountries; j++) {
                if (distances[i][k] * distances[k][j] < distances[i][j]) {
                    distances[i][j] = distances[i][k] * distances[k][j];
                    next[i][j] = next[i][k]; // Update "next" array
                }
            }
        }
    }

    // Check for negative cycles
    for (int i = 0; i < numCountries; i++) {
        for (int j = 0; j < numCountries; j++) {
            if (i != j) {
                int start = i;
                Set<String> visited = new HashSet<>();
                for (int k = 0; k < numCountries; k++) {
                    start = next[start][i];
                    visited.add(rates[i][j].from);
                    negativeCycle.add(rates[start][i]); // Add country to the cycle
                    if (start == i && visited.size() >= 3) {
                        negativeCycle.add(negativeCycle.get(0)); // Add the starting node to indicate a cycle
                        return negativeCycle;
                    }
                }
                negativeCycle.clear();
            }
        }
    }

    return null; // No negative cycles found
}

    public void displayAllDistances(Rate[][] rate) {
        int N = rate.length;
        System.out.println("Distances between nodes:");
        for (int u = 0; u < N; u++) {
            for (int v = 0; v < N; v++) {
                if (rate[u][v].rate == INF) {
                    System.out.println("Node " + u + " to Node " + v + ": INF");
                } else {
                    System.out.println("Node " + u + " to Node " + v + ": " + rate[u][v].rate);
                }
            }
        }
    }
    
//    public static List<Rate> findArbitrageOpportunity(Rate[][] rates) {
//        int numCountries = rates.length;
//
//    
//        // Initialize the distance matrix with exchange rates
//        double[][] distances = new double[numCountries][numCountries];
//        int[][] next = new int[numCountries][numCountries];
//    
//        for (int i = 0; i < numCountries; i++) {
//            for (int j = 0; j < numCountries; j++) {
//                distances[i][j] = rates[i][j].rate;
//                next[i][j] = j; // Initialize "next" array for reconstructing the path
//            }
//        }
//    
//        List<Rate> arbitrageOpportunity = new ArrayList<>();
//    
//        // Floyd-Warshall algorithm to detect arbitrage opportunities and record the path
//        for (int k = 0; k < numCountries; k++) {
//            for (int i = 0; i < numCountries; i++) {
//                for (int j = 0; j < numCountries; j++) {
//                    if (distances[i][k] * distances[k][j] > distances[i][j]) {
//                        distances[i][j] = distances[i][k] * distances[k][j];
//                        next[i][j] = next[i][k]; // Update "next" array
//                    }
//                }
//            }
//        }
//    
//        // Check for arbitrage opportunities
//        for (int i = 0; i < numCountries; i++) {
//            if (distances[i][i] > 1.0) {
//                // Arbitrage opportunity detected, reconstruct the cycle
//                Set<String> visited = new HashSet<>();
//                int start = i;
//                for (int j = 0; j < numCountries; j++) {
//                    start = next[start][i];
//                    visited.add(rates[start][i].from);
//                    arbitrageOpportunity.add(rates[start][i]); // Add country to the cycle
//                    if (start == i && visited.size() >= 3) {
//                        arbitrageOpportunity.add(arbitrageOpportunity.get(0)); // Add the starting node to indicate a cycle
//                        return arbitrageOpportunity;
//                    }
//                }
//                arbitrageOpportunity.clear();
//            }
//        }
//    
//        return null; // No arbitrage opportunities found
//    }

    public List<Rate> findArbitrageOpportunity(Rate[][] rates) {
        int numCountries = rates.length;
    
        // Initialize the distance matrix with exchange rates
        double[][] distances = new double[numCountries][numCountries];
        int[][] next = new int[numCountries][numCountries];
    
        for (int i = 0; i < numCountries; i++) {
            for (int j = 0; j < numCountries; j++) {
                distances[i][j] = rates[i][j].rate;
                next[i][j] = j; // Initialize "next" array for reconstructing the path
            }
        }
    
        List<Rate> arbitrageOpportunity = new ArrayList<>();
    
        // Floyd-Warshall algorithm to detect arbitrage opportunities and record the path
        for (int k = 0; k < numCountries; k++) {
            for (int i = 0; i < numCountries; i++) {
                for (int j = 0; j < numCountries; j++) {
                    if (distances[i][k] * distances[k][j] > distances[i][j]) {
                        distances[i][j] = distances[i][k] * distances[k][j];
                        next[i][j] = next[i][k]; // Update "next" array
                    }
                }
            }
        }
    
        // Check for arbitrage opportunities
        for (int i = 0; i < numCountries; i++) {
            if (distances[i][i] > 1.0) {
                // Arbitrage opportunity detected, reconstruct the cycle
                Set<Integer> visited = new HashSet<>();
                int start = i;
                for (int j = 0; j < numCountries; j++) {
                    start = next[start][i];
                    visited.add(start);
                    arbitrageOpportunity.add(rates[start][i]); // Add country to the cycle
                    if (start == i && visited.size() >= 3) {
                        arbitrageOpportunity.add(arbitrageOpportunity.get(0)); // Add the starting node to indicate a cycle
                        return arbitrageOpportunity;
                    }
                }
                arbitrageOpportunity.clear();
            }
        }
    
        return null; // No arbitrage opportunities found
    }
    
public void printDistance(Rate[][] rateArray, BellmanFord bf)
{
     int sourceVertex = 0;

     displayAllDistances(rateArray);

     double[] distances = bf.bellmanFordWithCycles(rateArray, sourceVertex);

     // Output distances
     for (int i = 0; i < distances.length; i++) {
         System.out.println("Distance from source to vertex " + i + ": " + distances[i]);        
    }
}
}
