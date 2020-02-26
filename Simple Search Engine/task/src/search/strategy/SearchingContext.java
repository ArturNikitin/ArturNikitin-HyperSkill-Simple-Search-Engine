package search.strategy;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SearchingContext {
    private SearchingStrategy strategy;

    public SearchingContext(SearchingStrategy strategy) {
        this.strategy = strategy;
    }

    public void findPeople(List<String> data, Map<String, List<Integer>> invertedIndex, Scanner scanner) {
        strategy.findPeople(data, invertedIndex, scanner);
    }
}
