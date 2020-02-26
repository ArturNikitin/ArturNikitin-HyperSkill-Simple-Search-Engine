package search;


import search.strategy.AllSearchingStrategy;
import search.strategy.AnySearchingStrategy;
import search.strategy.NoneSearchingStrategy;
import search.strategy.SearchingContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
   public static List<String> data = new ArrayList<>();
   public static Map<String, List<Integer>> invertedIndex = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String fileName = null;

        if (args.length > 0 && args[0].equals("--data")) {
            fileName = args[1];
        } else
            fileName = "E:\\one.txt";

        try(Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNext()) {
                String newLine = sc.nextLine().trim();
                data.add(newLine);
                addToIndex(newLine);
            }
        }


        boolean exit = true;
        while (exit) {
            System.out.println("\n=== Menu ===" +
                    "\n1. Find a person" +
                    "\n2. Print all people" +
                    "\n0. Exit");

            String input = scanner.nextLine();
            switch (input.trim()) {
                case ("1"):
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    switch (scanner.nextLine()) {
                            case ("ALL"): new SearchingContext(new AllSearchingStrategy()).findPeople(data, invertedIndex, scanner);
                            break;
                            case ("ANY"): new SearchingContext(new AnySearchingStrategy()).findPeople(data, invertedIndex, scanner);
                            break;
                            case ("NONE"): new SearchingContext(new NoneSearchingStrategy()).findPeople(data, invertedIndex, scanner);
                            break;
                            default:
                                System.out.println("\nIncorrect option! Try again.");
                        }
                    break;
                case("2"): printAllPeople(data);
                    break;
                case("0"): exit = false;
                    System.out.println("\nBye!");
                    break;
                default:
                    System.out.println("\nIncorrect option! Try again.");
            }
        }
        scanner.close();
    }


    public static void findAPerson(List<String> data, Scanner sc) {
        System.out.println("\nEnter a name or email to search all suitable people.");
        String query = sc.nextLine().toLowerCase().trim();

        boolean found = false;

        for (Map.Entry<String, List<Integer>> pair : invertedIndex.entrySet()) {

            if (pair.getKey().toLowerCase().trim().equals(query)) {

                for (int i = 0; i < pair.getValue().size(); i++) {
                    System.out.println(data.get(pair.getValue().get(i)));
                }
                found = true;
            }
        }

        if (!found) {
            System.out.println("Not found");
        }

    }

    public static void printAllPeople(List<String> data) {
        System.out.println();
        data.forEach(System.out::println);
    }

    public static void addToIndex(String inputLine) {
        List<String> words = Arrays.asList(inputLine.split("\\s"));
        int line = data.size()-1;
        for (String word :
                words) {
            if (invertedIndex.containsKey(word.toLowerCase())) {
                List<Integer> lines = invertedIndex.get(word.toLowerCase());
                lines.add(line);
                invertedIndex.replace(word.toLowerCase(), lines);
            } else {
                List<Integer> lines = new ArrayList<>();
                lines.add(line);
                invertedIndex.put(word.toLowerCase(), lines);
            }
        }
    }
}