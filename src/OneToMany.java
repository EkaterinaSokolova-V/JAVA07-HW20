import java.util.List;

@FunctionalInterface
public interface OneToMany<T, U> {
    List<U> oneToMany(T source);
}
