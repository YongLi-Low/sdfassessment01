package sdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        File file = new File(args[0]);
        String[] words = null;
        Integer wordCount = 0;

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String str;

        Map<String, Double> eachWordCount = new HashMap<>();

        while ((str = br.readLine()) != null) {
            words = str.replaceAll("\\W", " ").replaceAll("\\s{2,}", " ").
            trim().split(" ");
            if (words[0].equals("") || words[0].equals("\n")) {
                continue;
            }
            else if (!words[0].equals("\n")) {
                wordCount += words.length;
            }
            for (String s : words) {
                s = s.toLowerCase();
                Double count = eachWordCount.get(s);
                // System.out.print(s + " ");
                if (count == null) {
                    eachWordCount.put(s, 1.0);
                }
                else {
                    eachWordCount.put(s, count + 1);
                }
            }
            // System.out.println();
        }
        // System.out.println(wordCount);
        // System.out.println(eachWordCount);

        Map<String, Double> result = new HashMap<>();

        boolean exit = false;
        Integer count = 0;

        while (!exit) {
            Double maxValueInMap = (Collections.max(eachWordCount.values()));
            // System.out.println(maxValueInMap);
            for (Entry<String, Double> entry : eachWordCount.entrySet()) {
                if (entry.getValue() == maxValueInMap) {
                    result.put(entry.getKey(), entry.getValue());
                    count += 1;
                    if (count == 10) {
                        exit = true;
                        break;
                    }
                }
            }
            for (Entry<String, Double> entry: result.entrySet()) {
                if (eachWordCount.containsKey(entry.getKey())) {
                    eachWordCount.remove(entry.getKey());
                }
            }
        }
        
        for (Entry<String, Double> entry : result.entrySet()) {
            double value = entry.getValue() / wordCount;
            result.put(entry.getKey(), value);
        }

        // System.out.println(result);

        String[] resultString = new String[10];

        NumberFormat formatter = new DecimalFormat("#0.000");  // format value to 3 DP
        int i = 0;
        for (Entry<String, Double> entry : result.entrySet()) {
            resultString[i] = "Word: " + entry.getKey() + " -> Term Frequency: " + formatter.format(entry.getValue());
            i++;
        }

        for (String st : resultString) {
            System.out.println(st);
        }
    }
}