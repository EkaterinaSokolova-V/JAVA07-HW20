import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Utils {
    public static List<Integer> flattenInts(List<Integer> list){
        Transformer<Integer> tr = new Transformer<Integer>() {
            @Override
            public List<Integer> transform(Integer original) {
                List<Integer> result = new ArrayList<>();
                if (original > 0) {
                    for (int i = 1; i <= original; i++) {
                        result.add(i);
                    }
                } else if (original < 0) {
                    for (int i = original; i <= -1; i++) {
                        result.add(i);
                    }
                }
                return result;
            }
        };
        return flatten(list, tr);
    }
    public static List<String> flattenStrings(List<String> list) {
        Transformer<String> tr = new Transformer<String>() {
            @Override
            public List<String> transform(String original) {
                List<String> result = new ArrayList<>();
                for (int i = 0; i < original.length(); i++) {
                    result.add(String.valueOf(original.charAt(i)));
                }
                return result;
            }
        };
        return flatten(list, tr);
    }

    public static <T> List<T> flatten(List<T> list, Transformer<T> transformer) {
        List<T> result = new ArrayList<>();
        for (T el: list) {
            result.addAll(transformer.transform(el));
        }
        return result;
    }

    ///////////////////////////////////////////////////////////////////////

    public static List<Integer> flattenIntsDmitriy(List<Integer> source) {
        return flattenDmitriy(source, new OneToMany<Integer, Integer> () {
            @Override
            public List<Integer> oneToMany(Integer source) {
                return allIntsUpTo(source);
            }
        });
//        or Lambda (can be used if there is only one method in interface):
//        return flattenDmitriy(source, i -> allIntsUpTo(i));
    }

    private static List<Integer> allIntsUpTo(Integer x) {
        List<Integer> result = new ArrayList<>();
        if (x > 0) {
            for (int i = 1; i <= x; i++) {
                result.add(i);
            }
        } else if (x < 0) {
            for (int i = x; i <= -1; i++) {
                result.add(i);
            }
        }
        return result;
    }

    public static List<Character> flattenStringsDmitriy(List<String> source) {
        OneToMany<String, Character> oTM = source1 -> {
            List<Character> result = new ArrayList<>();
            for (char c: source1.toCharArray()) {
                result.add(c);
            }
            return result;
        };

        return flattenDmitriy(source, oTM);
    }

    public static List<Integer> codes(List<String> strings) {
        return flattenDmitriy(strings, string -> {
            List<Integer> result = new ArrayList<>();
            for (char c: string.toCharArray()) {
                result.add((int)c);
            }
            return result;
        });
    }

    ///////////////////////////////////////////////////////////////////////

    public static <T, U> List<U> flattenDmitriy(List<T> source, OneToMany<T, U> oneToMany) {
        List<U> result = new ArrayList<>();
        for (T el: source) {
            result.addAll(oneToMany.oneToMany(el));
        }
        return result;
    }

//we can use a predefined Function rather than OneToMany
//    public static <T, U> List<U> flattenDmitriy(List<T> source, Function<T, List<U>> oneToMany) {
//        List<U> result = new ArrayList<>();
//        for (T el: source) {
//            result.addAll(oneToMany.apply(el));
//        }
//        return result;
//    }

    public static <T extends Comparable<T>> T getMax(List<T> source) {
        if (source.size() == 0) {
            throw new IllegalArgumentException("Cannot find max of an empty list");
        }
        T currentMax = source.get(0);
        for (T element: source) {
            if (element.compareTo(currentMax) > 0) {
                currentMax = element;
            }
        }
        return currentMax;
    }

    public static <T extends Comparable<T>> T max(List<List<T>> source) {
        List<T> allTs = flattenDmitriy(source, (List<T> list) -> list);
        return getMax(allTs);
    }

//    public static String maxStrings(List<List<String>> source) {
//        return getMax(flattenDmitriy(source, (List<String> str) -> str));
//    }

    ///////////////////////////////////////////////////////////////////////

    public static <T> List<T> filter(List<T> source, Predicate<T> yesOrNo) {
        List<T> result = new ArrayList<>();
        for (T el: source) {
            if (yesOrNo.test(el)) {
                result.add(el);
            }
        }
        return result;
    }

    public static <T> Splitted<T> split(List<T> source, Predicate<T> yesOrNo) {
        List<T> passed = new ArrayList<>();
        List<T> rejected = new ArrayList<>();
        for (T el: source) {
            if (yesOrNo.test(el)) {
                passed.add(el);
            } else {
                rejected.add(el);
            }
        }
        return new Splitted<>(passed, rejected);
    }

    static class Splitted<T> {
        private final List<T> passed;
        private final List<T> rejected;

        public Splitted(List<T> passed, List<T> rejected) {
            this.passed = passed;
            this.rejected = rejected;
        }

        public String toString() {
            return "Passed: " + passed + ", Rejected: " + rejected;
        }
    }

    ///////////////////////////////////////////////////////////////////////

    public static <T> void foreach(List<T> source, Consumer<T> consumer) {
        for (T el: source) {
            consumer.accept(el);
        }
    }
}
