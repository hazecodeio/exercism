import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Observations:
 *      - Maintain the abstract type List<T> as much as possible
 *      - Not in favor of creating an ArrayList(). What if List was actually a LinkedList
 */
class ListOps {

    private ListOps() {
        // No instances.
    }

    static <T> List<T> append(List<T> list1, List<T> list2) {
        if(list1.isEmpty())
            return list2;
        /*for(T t : list2)
            list1.add(t);*/ // Arrays.asList() supplies Unmodifiable List in TestCase
        List<T> appendedList = new ArrayList<>(list1); // not in favor of creating an ArrayList(). What if List was actually a LinkedList
        appendedList.addAll(list2);
        return appendedList;
//        return Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList()); // via Streams
    }

    static <T> List<T> concat(List<List<T>> listOfLists) {
        List<T> flattenedList = new ArrayList<>();// not in favor of creating an ArrayList(). What if List was actually a LinkedList
        for(List<T> l : listOfLists)
            flattenedList.addAll(l);

        return flattenedList;
//        return listOfLists.stream().flatMap(l -> l.stream()).collect(Collectors.toList());
    }

    static <T> List<T> filter(List<T> list, Predicate<T> predicate) {

        List<T> filteredList = new ArrayList<>();
        for(T t : list)
            if(predicate.test(t))
                filteredList.add(t);

        return filteredList;
//        return list.stream().filter(predicate::test).collect(Collectors.toList());
    }

    static <T> int size(List<T> list) {
        int size = 0;
        for(T t : list)
            size++;

        return size;
//        return list.size();
    }

    static <T, U> List<U> map(List<T> list, Function<T, U> transform) {
        List<U> mappedList = new ArrayList<>();
        for(T t : list)
            mappedList.add(transform.apply(t));
        return mappedList;
//        return list.stream().map(transform::apply).collect(Collectors.toList());
    }

    static <T> List<T> reverse(List<T> list) {
        List<T> reversedList = new ArrayList<>();
        for(int i = list.size()-1; i >= 0; i--)
            reversedList.add(list.get(i));
        return reversedList;
        /*return IntStream.range(0, list.size())
                .map(i -> list.size() - 1 - i)
                .mapToObj(i -> list.get(i))
                .collect(Collectors.toList());*/
    }

    static <T, U> U foldLeft(List<T> list, U initial, BiFunction<U, T, U> f) {
        var acc = initial;
        for(T nxt : list){
            acc = f.apply(acc, nxt);
        }
        return acc;
    }

    static <T, U> U foldRight(List<T> list, U initial, BiFunction<T, U, U> f) {
        var acc = initial;
        for(int i = list.size() - 1; i >= 0 ; i--){
            var nxt = list.get(i);
            acc = f.apply(nxt, acc);
        }
        return acc;
    }
}
