package search.strategy;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public interface SearchingStrategy {

    void findPeople(List<String> data, Map<String, List<Integer>> invertedIndex, Scanner scanner);
}
