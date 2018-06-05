package dustin.examples.jdk8;

import static java.lang.System.out;

import java.lang.invoke.MethodHandles;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Demonstrates use of {@code Supplier} via JDK. This class's examples
 * include the following;
 *
 * <ul>
 * <li>Log Guarding
 *   <ul>
 *   <li>Traditional API Guarding: {@link #demonstrateTraditionalLogGuards()}</li>
 *   <li>Supplier-based Deferral: {@link #demonstrateDeferredLogging()}</li>
 *   </ul>
 * </li>
 * <li>{@code Optional.or(Supplier)}
 *    <ul>
 *    <li>Without {@code Optional.or}: {@link #demonstrateWithoutOptionalOr()}</li>
 *    <li>Supplier-based {@code Optional.or}: {@link #demonstrateOptionalOr()}</li>
 *    </ul>
 * </li>
 * <li>{@code Optional.orElseGet(Supplier)}
 *   <ul>
 *   <li>Without {@code Optional.orElseGet}: {@link #demonstrateWithoutOptionalOrElseGet()}</li>
 *   <li>Supplier-based {@code Optional.orElseGet): {@link #demonstrateOptionalOrElseGet()}}</li>
 *   </ul>
 * </li>
 * <li>{@code Optional.orElseThrow(Supplier)}
 *   <ul>
 *   <li>Without {@code Optional.orElseThrow}: {@link #demonstrateWithoutOptionalOrElseThrow()}</li>
 *   <li>Supplier-based {@code Optional.orElseThrow): {@link #demonstrateOptionalOrElseThrow()}}</li>
 *   </ul>
 * </li>
 * <li>{@code Objects.requireNonNullElseGet(T, Supplier)}
 *   <ul>
 *   <li>Without {@code Objects.requireNonNullElseGet}: {@link #demonstrateWithoutObjectsRequireNonNullElseGet(Object)}</li>
 *   <li>Supplier-based {@code Objects.requireNonNullElseGet}: {@link #demonstrateObjectsRequireNonNullElseGet(Object)}</li>
 *   </ul>
 * </li>
 * <li>{@code Objects.requireNonNull(T, Supplier)}
 *   <ul>
 *   <li>Without {@code Objects.requireNonNull}: {@link #demonstrateWithoutObjectsRequireNonNull(Object)}</li>
 *   <li>Supplier-based {@code Objects.requireNonNull}: {@link #demonstrateObjectsRequireNonNull(Object)}</li>
 *   </ul>
 * </li>
 * </ul>
 */
public class SupplierDemo
{
   private final static Logger ourLogger
      = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

   /**
    * Demonstrate traditional API guarding of log message generation
    * until it is known that the message will be logged.
    */
   public void demonstrateTraditionalLogGuards()
   {
      if (ourLogger.isLoggable(Level.FINE))
      {
         ourLogger.fine("The result is: " + expensiveGeneration());
      }
   }

   /**
    * Demonstrate deferred execution of log message generation
    * until it is known that the message will be logged.
    */
   public void demonstrateDeferredLogging()
   {
      ourLogger.fine( () -> "The result is: " + expensiveGeneration());
   }

   /**
    * Demonstrate approach without {@code Optional.or(Supplier)}.
    */
   public void demonstrateWithoutOptionalOr()
   {
      final Optional<String> potentialString = extractPotentialString();
      out.println(potentialString.isPresent() ? potentialString.get() : extractExpensiveOptionalStringGeneration().get());
   }

   /**
    * Demonstrate {@code Optional.or(Supplier)} and its ability to "supply"
    * an alternative when the original Optional is empty.
    */
   public void demonstrateOptionalOr()
   {
      final Optional<String> potentialString = extractPotentialString();
      out.println(potentialString.or( () -> extractExpensiveOptionalStringGeneration()).get());
   }

   /**
    * Demonstrate approach without {@code Optional.orElseGet(Supplier)}.
    */
   public void demonstrateWithoutOptionalOrElseGet()
   {
      final Optional<String> potentialString = extractPotentialString();
      out.println(potentialString.isPresent() ? potentialString.get() : extractExpensiveStringGeneration());
   }

   /**
    * Demonstrate {@code Optional.orElseGet(Supplier)} and its ability to "supply"
    * an alternative when the original Optional is empty.
    */
   public void demonstrateOptionalOrElseGet()
   {
      final Optional<String> potentialString = extractPotentialString();
      out.println(potentialString.orElseGet( () -> extractExpensiveStringGeneration()));
   }

   /**
    * Demonstrate approach without {@code Optional.orElseThrow(Supplier)}.
    */
   public void demonstrateWithoutOptionalOrElseThrow()
   {
      final Optional<String> potentialString = extractPotentialString();
      if (potentialString.isPresent())
      {
         out.println(potentialString.get());
      }
      else
      {
         throw new IllegalArgumentException("No String!!!");
      }
   }

   /**
    * Demonstrate {@code Optional.orElseThrow(Supplier)} and its ability
    * to "supply" an exception only when the exceptional condition exists
    * (empty Optional).
    */
   public void demonstrateOptionalOrElseThrow()
   {
      final Optional<String> potentialString = extractPotentialString();
      out.println(potentialString.orElseThrow( () -> new IllegalArgumentException("No String!!!")));
   }

   /**
    * Demonstrate approach without {@code Objects.requireNonNull(T, Supplier)}.
    *
    * @param object Pass this as {@code null} to demonstrate alternate functionality.
    */
   public void demonstrateWithoutObjectsRequireNonNull(final Object object)
   {
      if (object != null)
      {
         out.println(object);
      }
      else
      {
         throw new NullPointerException(generateExpensiveNullMessage());
      }
   }

   /**
    * Demonstrate use of {@code Objects.requireNonNull(T, Supplier)} to only
    * generate expensive message for {@code NullPointerException} if needed
    * (passed-in object is {@code null} and {@code NullPointerException} will be thrown).
    *
    * @param object Pass this as {@code null} to demonstrate alternate functionality.
    */
   public void demonstrateObjectsRequireNonNull(final Object object)
   {
      out.println(Objects.requireNonNull(object, () -> generateExpensiveNullMessage()));
   }

   /**
    * Demonstrate alternative to {@code Objects.requireNonNullElseGet}.
    *
    * @param object Pass this as {@code null} to demonstrate alternate functionality.
    */
   public void demonstrateWithoutObjectsRequireNonNullElseGet(final Object object)
   {
      out.println(object != null ? object : generateExpensiveNullMessage());
   }

   /**
    * Demonstrate use of {@code Objects.requireNonNullElseGet(T, Supplier)} to only
    * generate expensive message if needed (passed-in object is {@code null}).
    *
    * @param object Pass this as {@code null} to demonstrate alternate functionality.
    */
   public void demonstrateObjectsRequireNonNullElseGet(final Object object)
   {
      out.println(Objects.requireNonNullElseGet(object, () -> generateExpensiveNullMessage()));
   }

   /**
    * Method representing expensive operation to be logged.
    * @return Result of expensive operation.
    */
   private String expensiveGeneration()
   {
      return "A Costly String";
   }

   /**
    * Method that intentionally returns an {@code Optional.empty()}
    * so that methods invoking this method can demonstrate use of
    * {@code Supplier}-based deferred execution of alternatives to
    * this returned {@code Optional.empty()}.
    *
    * @return Always returns {@code Optional.empty()}.
    */
   private Optional<String> extractPotentialString()
   {
      return Optional.empty();
   }

   /**
    * Represents a method that generates an {@code Optional} holding a
    * {@code String}, but is a costly operation to generate that
    * potential string.
    *
    * @return Always an instance of {@code Optional} containing the
    *    {@code String} "Non-empty!".
    */
   private Optional<String> extractExpensiveOptionalStringGeneration()
   {
      return Optional.of("Non-empty!");
   }

   /**
    * Represents a method that generates a {@code String}, but is a
    * costly operation to generate that string.
    *
    * @return Always a {@code String} "Non-empty!".
    */
   private String extractExpensiveStringGeneration()
   {
      return "Non-empty!";
   }

   /**
    * Represents a method that generates a {@code String} containing
    * a message about an encountered {@code null}, but is a costly
    * operation to generate that string.
    *
    * @return Always a {@code String} "Null Encountered!!!".
    */
   private String generateExpensiveNullMessage()
   {
      return "Null Encountered!!!";
   }

   /**
    * Main executable function.
    *
    * @param arguments Command-line arguments (none expected).
    */
   public static void main(final String[] arguments)
   {
      final SupplierDemo instance = new SupplierDemo();
      instance.demonstrateTraditionalLogGuards();
      instance.demonstrateDeferredLogging();
      instance.demonstrateWithoutOptionalOr();
      instance.demonstrateOptionalOr();
      instance.demonstrateWithoutOptionalOrElseGet();
      instance.demonstrateOptionalOrElseGet();

      try
      {
         instance.demonstrateWithoutOptionalOrElseThrow();
      }
      catch (Exception ex)
      {
         out.println("ERROR: " + ex);
      }

      try
      {
         instance.demonstrateOptionalOrElseThrow();
      }
      catch (Exception ex)
      {
         out.println("ERROR: " + ex);
      }

      try
      {
         instance.demonstrateWithoutObjectsRequireNonNull(null);
      }
      catch (Exception ex)
      {
         out.println("ERROR: " + ex);
      }

      try
      {
         instance.demonstrateObjectsRequireNonNull(null);
      }
      catch (Exception ex)
      {
         out.println("ERROR: " + ex);
      }

      instance.demonstrateWithoutObjectsRequireNonNullElseGet(null);
      instance.demonstrateObjectsRequireNonNullElseGet(null);
   }
}
