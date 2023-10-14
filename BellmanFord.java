/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Currency_Exchange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Rauen
 */
// Reference code for Bellman Ford Algorithm in Java
public class BellmanFord {

    private static final double INF = Double.MAX_VALUE;
    protected Exchange ex;

    public BellmanFord(Exchange ex) {
        this.ex = ex;
    }

    public List<Integer> FindPath(Rate[][] rate) {
        int N = rate.length;
        double[] d = new double[N];
        int[] parent = new int[N];
        List<Integer> shortestPath = new ArrayList<>();

        // Step 1: Initialize distances
        for (int i = 0; i < N; i++) {
            d[i] = INF;
            parent[i] = -1;
        }

        d[0] = 1.0;

        // Step 2: Find shortest edges between rates
        for (int i = 0; i < N; i++) {
            for (int u = 1; u < N; u++) {
                for (int v = 0; v < N; v++) {
                    if (rate[v][u].rate != INF && d[v] != INF && d[v] * rate[v][u].rate < d[u]) {
                        d[u] = d[v] * rate[v][u].rate;
                        //set parent list to print shortest path later
                        parent[u] = v;
                    }
                }
            }
        }

        // Step 3: Check for negative-weight cycles
        boolean hasNegativeCycle = false;
        int negativeCycleStart = -1;
        for (int u = 1; u < N; u++) {
            for (int v = 0; v < N; v++) {
                if (rate[v][u].rate != INF && d[v] != INF && d[v] * rate[v][u].rate < d[u]) {
                    d[u] = Double.NEGATIVE_INFINITY;
                    hasNegativeCycle = true;
                    negativeCycleStart = u;
                }
            }
        }

        // Cycle back through the shortest path
        if (!hasNegativeCycle) {
            // Start from the last node
            ex.cycle = "Best path: ";
            int current = N - 1;
            while (current != -1) {
                shortestPath.add(0, current);
                current = parent[current];
            }
        }

        // If a negative cycle exists cycle through negative cycle
        if (hasNegativeCycle) {
            // There is a negative cycle in the graph
            ex.cycle = "Negative Cycle exist.\n\nArbitrage cycle: ";

            // Retrieve and print the negative cycle
            List<Integer> negativeCycle = new ArrayList<>();
            int current = negativeCycleStart;
            do {
                negativeCycle.add(current);
                current = parent[current];
            } while (current != negativeCycleStart);
            negativeCycle.add(negativeCycle.get(0));
            Collections.reverse(negativeCycle);
            return negativeCycle;
        }

        return shortestPath;
    }
}
