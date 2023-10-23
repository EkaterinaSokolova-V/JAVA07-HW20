import java.util.ArrayList;
import java.util.List;

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

    public static <T, U> List<U> flattenDmitriy(List<T> source, OneToMany<T, U> oneToMany) {
        List<U> result = new ArrayList<>();
        for (T el: source) {
            result.addAll(oneToMany.oneToMany(el));
        }
        return result;
    }
}
