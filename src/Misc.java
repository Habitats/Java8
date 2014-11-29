import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import help.Converter;
import help.Formula;
import help.Person;
import help.PersonFactory;
import help.Something;

/**
 *
 * Created by Patrick on 29.11.2014.
 */
public class Misc {

  private void defaultMethods() {
    Formula formula = new Formula() {
      @Override
      public double calculate(int a) {
        return sqrt(a * 100);
      }
    };

    formula.calculate(100);     // 100.0
    formula.sqrt(16);           // 4.0
  }

  private void sortingWithLambda() {

    // old way
    List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

    Collections.sort(names, new Comparator<String>() {
      @Override
      public int compare(String a, String b) {
        return b.compareTo(a);
      }
    });

    // new way 1
    Collections.sort(names, (String a, String b) -> {
      return b.compareTo(a);
    });

    // shorter
    Collections.sort(names, (String a, String b) -> b.compareTo(a));

    // even shorter
    Collections.sort(names, (a, b) -> b.compareTo(a));
  }


  private void functionalInterface() {
    Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
    Integer converted = converter.convert("123");
    System.out.println(converted);    // 123
  }


  private void methodReferences1() {
    Converter<String, Integer> converter = Integer::valueOf;
    Integer converted = converter.convert("123");
    System.out.println(converted);   // 123
  }

  private void methodReferences2() {
    Something something = new Something();
    Converter<String, String> converter = something::startsWith;
    String converted = converter.convert("Java");
    System.out.println(converted);    // "J"
  }

  private void crazyFactoryExample() {
    PersonFactory<Person> personFactory = Person::new;
    Person person = personFactory.create("Peter", "Parker");
  }


  private void predicates() {
    Predicate<String> predicate = (s) -> s.length() > 0;

    predicate.test("foo");              // true
    predicate.negate().test("foo");     // false

    Predicate<Boolean> nonNull = Objects::nonNull;
    Predicate<Boolean> isNull = Objects::isNull;

    Predicate<String> isEmpty = String::isEmpty;
    Predicate<String> isNotEmpty = isEmpty.negate();
  }


  private void functions() {
    Function<String, Integer> toInteger = Integer::valueOf;
    Function<String, String> backToString = toInteger.andThen(String::valueOf);

    backToString.apply("123");     // "123"
  }

  private void suppliers() {
    Supplier<Person> personSupplier = Person::new;
    personSupplier.get();   // new help.Person
  }

  // Consumers represents operations to be performed on a single input argument
  private void consumers() {
    Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
    greeter.accept(new Person("Luke", "Skywalker"));
  }

  private void comparators() {
    Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

    Person p1 = new Person("John", "Doe");
    Person p2 = new Person("Alice", "Wonderland");

    comparator.compare(p1, p2);             // > 0
    comparator.reversed().compare(p1, p2);  // < 0
  }

  private void optionals() {
    Optional<String> optional = Optional.of("bam");

    optional.isPresent();           // true
    optional.get();                 // "bam"
    optional.orElse("fallback");    // "bam"

    optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
  }


}
