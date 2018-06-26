package dustin.examples.jdk8;

import static java.lang.System.out;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Demonstrates use of {@code Consumer} via JDK.
 */
public class ConsumerDemo
{
   private final static Logger ourLogger
      = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

   /**
    * Demonstrate use of {@code Consumer} with {@code Stream.peek}.
    */
   public void demonstratePeek()
   {
      Stream.of("one", "two", "three", "four")
         .filter(e -> e.length() > 3)
         .peek(e -> out.println("Filtered value: " + e))
         .map(String::toUpperCase)
         .peek(e -> out.println("Mapped value: " + e))
         .collect(Collectors.toList());
   }

   /**
    * Demonstrate use of {@code Consumer} with {@code Stream.forEach}.
    */
   public void demonstrateForEach()
   {
      Set.of("one", "two", "three", "four").stream()
         .forEach(i -> out.println(i.toUpperCase()));
   }

   /**
    * Demonstrate use of {@code Consumer} with {@code Stream.forEachOrdered}.
    */
   public void demonstrateForEachOrdered()
   {
      List.of("one", "two", "three", "four").stream()
         .forEachOrdered(i -> out.println(i.toUpperCase()));
   }

   public void demonstrateForEachAndForEachOrderedOnStreamDirectly()
   {
      Stream.of("one", "two", "three", "four")
         .forEach(i -> out.println(i.toUpperCase()));

      Stream.of("one", "two", "three", "four")
         .forEachOrdered(i -> out.println(i.toUpperCase()));
   }

   public void demonstrateForEachOnCollectionsWithImplicitStream()
   {
      Set.of("one", "two", "three", "four")
         .forEach(i -> out.println(i.toUpperCase()));
      List.of("one", "two", "three", "four")
         .forEach(i -> out.println(i.toUpperCase()));
   }

   public void demonstrateMapWithBiConsumer()
   {
      Map.of("Denver", "Colorado",
         "Cheyenne", "Wyoming",
         "Salt Lake City", "Utah",
         "Boise", "Idaho")
         .forEach((c, s) -> out.println(c + " is the capital of " + s));
   }

   public void demonstrateOptionalIfPresent()
   {
      getMiddleName(true).ifPresent(n -> out.println("Middle Name: " + n));
   }

   public void demonstrateOptionalIfPresentOrElse()
   {
      getMiddleName(false).ifPresentOrElse(
         n -> out.println("Middle Name: " + n),
         () -> displayMissingMiddleName());
   }

   private Optional<String> getMiddleName(final boolean present)
   {
      return present ? Optional.of("Wayne") : Optional.empty();
   }

   private void displayMissingMiddleName()
   {
      out.println("No middle name provided!");
   }

   public void demonstrateStackWalkerForEach()
   {
      StackWalker.getInstance().forEach(out::println);
   }

   public static void main(final String[] arguments)
   {
      final ConsumerDemo instance = new ConsumerDemo();
      instance.demonstratePeek();
      instance.demonstrateForEach();
      instance.demonstrateForEachOrdered();
      instance.demonstrateForEachAndForEachOrderedOnStreamDirectly();
      instance.demonstrateForEachOnCollectionsWithImplicitStream();
      instance.demonstrateStackWalkerForEach();
      instance.demonstrateMapWithBiConsumer();
      instance.demonstrateOptionalIfPresentOrElse();
   }
}
