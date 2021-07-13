import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        testCases();
    }

    private static void testCases() {
        List<String> inputs = Arrays.asList(
                "Charlie Jennifer Charlie Bob Charlie Charlie Bob Jennifer Alice Alice ",
                "        ", null, "Charlie");

        for (String s : inputs) {
            System.out.println("Input: \n" + s);

            System.out.println("Output:");
            solutionViaMapAndEntryBetter(s);
            System.out.println("***********************************************************************");
        }
    }

    private static void solutionViaMapAndEntryBetter(String input) {
        if (input == null) return;

        Arrays.stream(input.split(" "))
                .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum))
                .entrySet().stream()
                .sorted(Main::stringComparator)
                .forEach(System.out::println);
    }

    private static void solutionViaMapAndEntry(String input) {
        if (input == null) return;

        Map<String, Integer> map = new TreeMap<>();
        for (String str : Arrays.asList(input.split(" "))) {
            if (!map.containsKey(str)) {
                map.put(str, 1);
            } else {
                map.computeIfPresent(str, (k, v) -> v + 1);
            }
        }

        // n * log (n)
        Stream<Map.Entry<String, Integer>> entryStream = map.entrySet().stream()
                .sorted(Main::stringComparator);

        entryStream.forEach(System.out::println);
    }

    private static int stringComparator(Map.Entry<String, Integer> s1, Map.Entry<String, Integer> s2) {
        if (s1.getValue() > s2.getValue()) {
            return -1;
        } else if (s1.getValue() < s2.getValue()) {
            return 1;
        } else {
            return s1.getKey().compareTo(s2.getKey());
        }
    }

    private static void solutionFastAndWorks(String input) {
        if (input != null) {
            if (!input.replace(" ", "").isEmpty()) {
                List<String> list = Arrays.asList(input.split(" "));
                Set<Template> templateSet = new TreeSet<>();
                if (!input.isEmpty() && Arrays.asList(input.split("")).size() == 1) {
                    templateSet.add(new Template(input, 1));
                }

                for (String str : list) {
                    templateSet.add(new Template(str, (int) list.stream().filter(s -> s.equals(str)).count()));
                }

                templateSet.forEach(s -> System.out.println(s.getName() + " " + s.getVal()));
            }
        }
    }
}
