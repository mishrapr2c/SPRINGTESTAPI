package com.cognizant.SpringTestAPI.Service;

import com.cognizant.SpringTestAPI.Model.Candidate;
import com.cognizant.SpringTestAPI.Util.VectorUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final List<Candidate> candidates = Arrays.asList(
        new Candidate("John Doe", "Backend Developer", "Java, Spring Boot, AWS, Microservices, REST APIs", "5 years", new double[]{0.9, 0.8, 0.7, 0.1, 0.1}),
        new Candidate("Jane Smith", "Software Engineer", "Java, Python, GCP, Kubernetes, SQL", "6 years", new double[]{0.8, 0.9, 0.8, 0.2, 0.5}),
        new Candidate("Peter Jones", "Frontend Developer", "JavaScript, React, HTML, CSS, Node.js", "4 years", new double[]{0.2, 0.1, 0.5, 0.9, 0.1}),
        new Candidate("Alice Williams", "Data Scientist", "Python, Pandas, Scikit-learn, SQL, AWS S3", "3 years", new double[]{0.1, 0.4, 0.4, 0.1, 0.9}),
        new Candidate("Sam Brown", "Cloud Engineer", "AWS, Azure, Terraform, CI/CD, Python", "7 years", new double[]{0.4, 0.95, 0.9, 0.1, 0.3}),
        new Candidate("Emily White", "Junior Java Developer", "Java, Spring, SQL", "1 year", new double[]{0.7, 0.1, 0.2, 0.1, 0.2})
    );

    public List<Candidate> search(String query) {
        double[] queryVector = getQueryVector(query);

        return candidates.stream()
            .map(c -> new AbstractMap.SimpleEntry<>(c, VectorUtils.cosineSimilarity(queryVector, c.getVector())))
            .filter(entry -> entry.getValue() >= 0.3)
            .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }

    private double[] getQueryVector(String query) {
        double[] vector = new double[5];
        String q = query.toLowerCase();

        if (q.contains("java") || q.contains("backend") || q.contains("software engineer")) vector[0] += 0.9;
        if (q.contains("spring")) vector[0] += 0.5;

        if (q.contains("cloud") || q.contains("aws") || q.contains("gcp") || q.contains("azure")) vector[1] += 0.9;

        if (q.contains("senior") || q.contains("lead") || q.contains("expert")) vector[2] += 0.8;
        if (q.contains("5 year") || q.contains("6 year") || q.contains("7 year")) vector[2] += 0.7;
        if (q.contains("experience")) vector[2] += 0.3;

        if (q.contains("frontend") || q.contains("javascript") || q.contains("react")) vector[3] += 0.9;

        if (q.contains("data") || q.contains("python") || q.contains("sql")) vector[4] += 0.9;

        return VectorUtils.normalize(vector);
    }
}