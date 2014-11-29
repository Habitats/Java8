import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Patrick on 30.11.2014.
 */
public class Streams {

  private Collection<String> getStrings() {
    List<String> stringCollection = new ArrayList<>();
    stringCollection.add("ddd2");
    stringCollection.add("aaa2");
    stringCollection.add("bbb1");
    stringCollection.add("aaa1");
    stringCollection.add("bbb3");
    stringCollection.add("ccc");
    stringCollection.add("bbb2");
    stringCollection.add("ddd1");

    return stringCollection;
  }

  public void filter() {
    Collection<String> stringCollection = getStrings();

    stringCollection //
        .stream() //
        .filter((s) -> s.startsWith("a")) //
        .forEach(System.out::println);

    // "aaa2", "aaa1"
  }

  public void sorted() {
    Collection<String> stringCollection = getStrings();

    stringCollection //
        .stream() //
        .sorted() //
        .filter((s) -> s.startsWith("a")) //
        .forEach(System.out::println);

    // "aaa1", "aaa2"
  }

  public void map() {
    Collection<String> stringCollection = getStrings();

    stringCollection //
        .stream() //
        .map(String::toUpperCase) //
        .sorted((a, b) -> b.compareTo(a)) //
        .forEach(System.out::println);

    // "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"
  }

  public void match() {
    Collection<String> stringCollection = getStrings();

    boolean anyStartsWithA = stringCollection //
        .stream() //
        .anyMatch((s) -> s.startsWith("a"));

    System.out.println(anyStartsWithA);      // true

    boolean allStartsWithA = stringCollection //
        .stream() //
        .allMatch((s) -> s.startsWith("a"));

    System.out.println(allStartsWithA);      // false

    boolean noneStartsWithZ = stringCollection //
        .stream() //
        .noneMatch((s) -> s.startsWith("z"));

    System.out.println(noneStartsWithZ);      // true
  }

  public void count() {
    Collection<String> stringCollection = getStrings();

    long startsWithB = stringCollection //
        .stream() //
        .filter((s) -> s.startsWith("b")) //
        .count();

    System.out.println(startsWithB);    // 3
  }

  public void reduce() {
    Collection<String> stringCollection = getStrings();

    Optional<String> reduced = stringCollection //
        .stream() //
        .sorted() //
        .reduce((s1, s2) -> s1 + "#" + s2);

    reduced.ifPresent(System.out::println);
    // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"
  }

  public Collection<String> getValues() {
    int max = 1000000;
    List<String> values = new ArrayList<>(max);
    for (int i = 0; i < max; i++) {
      UUID uuid = UUID.randomUUID();
      values.add(uuid.toString());
    }

    return values;
  }

  public void sequentialSort() {
    Collection<String> values = getValues();
    long t0 = System.nanoTime();

    long count = values.stream().sorted().count();
    System.out.println(count);

    long t1 = System.nanoTime();

    long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
    System.out.println(String.format("sequential sort took: %d ms", millis));

  }

  public void parallelSort() {
    Collection<String> values = getValues();
    long t0 = System.nanoTime();

    long count = values.parallelStream().sorted().count();
    System.out.println(count);

    long t1 = System.nanoTime();

    long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
    System.out.println(String.format("parallel sort took: %d ms", millis));

  }
}
