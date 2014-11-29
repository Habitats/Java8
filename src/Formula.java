/**
 * Created by Patrick on 29.11.2014.
 */
public interface Formula {
  double calculate(int a);

  default double sqrt(int a){
    return Math.sqrt(a);
  }

}
