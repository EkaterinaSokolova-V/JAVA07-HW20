import java.util.ArrayList;
import java.util.List;

public class Utils {
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


    public static <T> List<T> flatten(List<T> list, Transformer<T> transformer) {
        List<T> result = new ArrayList<>();
        for (T el: list) {
            result.addAll(transformer.transform(el));
        }
        return result;
    }
}
