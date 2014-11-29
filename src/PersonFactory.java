/**
 * Created by Patrick on 29.11.2014.
 */
interface PersonFactory<P extends Person> {

  P create(String firstName, String lastName);
}
