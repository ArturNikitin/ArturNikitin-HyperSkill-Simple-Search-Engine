package search.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AnySearchingStrategy implements SearchingStrategy {
    @Override
    public void findPeople(List<String> data, Map<String, List<Integer>> invertedIndex, Scanner scanner) {
        System.out.println("\nEnter a name or email to search all suitable people.");

        String[] query = scanner.nextLine().toLowerCase().trim().split("\\s");
        List<Integer> lines = new ArrayList<>();
        boolean first = true;

        for (String s : query) {
            for (Map.Entry<String, List<Integer>> pair : invertedIndex.entrySet()) {
                if (pair.getKey().toLowerCase().trim().equals(s.toLowerCase().trim())) {
                    if (first) {
                        lines.addAll(pair.getValue());
                        first = false;
                    } else {
                        for (Integer i :
                                pair.getValue()) {
                            if (!lines.contains(i)) {
                                lines.add(i);
                            }
                        }
                    }
                }
            }
        }

        for (Integer line : lines) {
            System.out.println(data.get(line));
        }

        if (lines.isEmpty()) {
            System.out.println("Not found");
        }
    }
}

