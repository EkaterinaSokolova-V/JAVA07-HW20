import java.util.List;

public interface Transformer<T> {
    List<T> transform(T original);
}
