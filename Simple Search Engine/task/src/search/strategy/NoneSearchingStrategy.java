package search.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class NoneSearchingStrategy implements SearchingStrategy {
    @Override
    public void findPeople(List<String> data, Map<String, List<Integer>> invertedIndex, Scanner scanner) {
        System.out.println("\nEnter a name or email to search all suitable people.");

        String[] query = scanner.nextLine().toLowerCase().trim().split("\\s");
        List<Integer> linesToPrint = new ArrayList<>();
        List<Integer> linesNotToPrint = new ArrayList<>();

        for (String s : query) {

            for (Map.Entry<String, List<Integer>> pair : invertedIndex.entrySet()) {

                if (!pair.getKey().toLowerCase().trim().equals(s)) {
                    if (linesToPrint.isEmpty()) {
                        linesToPrint.addAll(pair.getValue());
                    } else {
                        for (Integer i :
                                pair.getValue()) {
                            if (!linesToPrint.contains(i)) {
                                linesToPrint.add(i);
                            }
                        }
                    }
                } else {
                    if (linesNotToPrint.isEmpty()) {
                        linesNotToPrint.addAll(pair.getValue());
                    } else {
                        for (Integer i :
                                pair.getValue()) {
                            if (!linesNotToPrint.contains(i)) {
                                linesNotToPrint.add(i);
                            }
                        }
                    }
                }
            }
        }
        linesToPrint.removeAll(linesNotToPrint);
        for (Integer line : linesToPrint) {
            System.out.println(data.get(line));
        }

        if (linesToPrint.isEmpty()) {
            System.out.println("Not found");
        }
    }
}
