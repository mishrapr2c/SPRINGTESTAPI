package com.cognizant.SpringTestAPI.Util;

import java.util.Arrays;

public class VectorUtils {

    public static double dotProduct(double[] vecA, double[] vecB) {
        double product = 0;
        for (int i = 0; i < vecA.length; i++) {
            product += vecA[i] * vecB[i];
        }
        return product;
    }

    public static double magnitude(double[] vec) {
        double sum = 0;
        for (double v : vec) {
            sum += v * v;
        }
        return Math.sqrt(sum);
    }

    public static double[] normalize(double[] vec) {
        double mag = magnitude(vec);
        if (mag == 0) return vec;
        return Arrays.stream(vec).map(v -> v / mag).toArray();
    }

    public static double cosineSimilarity(double[] vecA, double[] vecB) {
        return dotProduct(normalize(vecA), normalize(vecB));
    }
}