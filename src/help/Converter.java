package help;

/**
 * Created by Patrick on 29.11.2014.
 */
@FunctionalInterface
public interface Converter<F, T> {

  T convert(F from);

}
