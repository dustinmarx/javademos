package dustin.examples.nullsafe.tactics;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Demonstrate {@code null}-safe tactics.
 *
 * Featured in "Inspired by Actual Events" blog post
 * "Java NullPointerException Avoidance and Enhancement Tactics"
 * (https://marxsoftware.blogspot.com/2021/02/java-nullpointerexception-avoidance-and.html)
 */
public class NullSafeTactics
{
   /**
    * Handle to logger named after this class.
    */
   private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

   /**
    * This is always {@code null} and is used in demonstrations of {@code null}-safe tactics.
    */
   private static final Object NULL_OBJECT = null;

   /**
    * This is always {@code null} and is used in demonstrations of {@code null}-safe tactics.
    */
   private static final String NULL_STRING = null;

   /**
    * This is always {@code null} and is used in demonstration of {@code null}-safe tactics.
    */
   private static final TimeUnit NULL_TIME_UNIT = null;

   /**
    * Demonstrates that Java string conversion avoids {@link NullPointerException}.
    */
   public void demonstrateNullSafeStringConversion()
   {
      executeOperation(
         "Implicit Java String Conversion",
         () -> "The value of the 'null' object is '" + NULL_OBJECT + "'.");
   }

   /**
    * Demonstrates that explicit {@link Object#toString()} on {@code null} leads to
    * {@link NullPointerException}.
    */
   public void demonstrateNullUnsafeExplicitToString()
   {
      executeOperation(
         "Unsafe Explicit toString() Invocation on null",
         () -> "The value of the 'null' object is '" + NULL_OBJECT.toString() + "'.");
   }

   /**
    * Demonstrates that {@link String#valueOf(Object)} will render {@code null} safely
    * as "null" string.
    *
    * In many cases, use of {@link String#valueOf(Object)} is unnecessary because Java's
    * string conversion will perform the same effect. {@link String#valueOf(Object)} is
    * necessary when Java is not able to implicitly convert to a {@link String}.
    *
    * See also https://marxsoftware.blogspot.com/2009/04/value-of-stringvalueof.html.
    */
   public void demonstrateNullSafeStringValueOf()
   {
      executeOperation(
         "Null-safe String Representation with String.valueOf(Object)",
         () -> "The value of the 'null' object is '" + String.valueOf(NULL_OBJECT) + "'.");
   }

   /**
    * Demonstrates that {@link Objects#toString(Object)} will render {@code null} safely
    * as "null" string.
    *
    * In many cases, use of {@link Objects#toString(Object)} is unnecessary because Java's
    * string conversion will perform the same effect. {@link Objects#toString(Object)} is
    * necessary when Java is not able to implicitly convert to a {@link String}.
    */
   public void demonstrateObjectsToString()
   {
      executeOperation(
         "Null-safe String Representation with Objects.toString(Object)",
         () -> "The value of the 'null' object is '" + Objects.toString(NULL_OBJECT) + "'.");
   }

   /**
    * Demonstrates that {@link Objects#toString(Object, String)} will render {@code null}
    * potentially safely as the "default" string specified as the second argument.
    *
    * In many cases, use of {@link Objects#toString(Object, String)} is unnecessary because
    * Java's string conversion will perform the same effect. {@link Objects#toString(Object)}
    * is necessary when Java is not able to implicitly convert to a {@link String} or when
    * it is desired that the string representation of the {@code null} be something other
    * than the "null" string.
    */
   public void demonstrateObjectsToStringWithDefault()
   {
      executeOperation(
         "Null-safe String Representation with Objects.toString(Object,String) Using Empty String Default",
         () -> "The value of the 'null' object is '" + Objects.toString(NULL_OBJECT, "") + "'.");
   }

   /**
    * Demonstrates that {@link Objects#requireNonNullElse(Object, Object)} will render
    * {@code null} safely for any potential {@code null} passed to it by returning the
    * supplied default instead when the object in question is {@code null}. Two
    * examples are included in this method's demonstration:
    * <ol>
    *    <li>{@code null} {@link Object} safely rendered as custom supplied default "null" string</li>
    *    <li>{@code null} {@link TimeUnit} safely rendered as custom supplied default {@link TimeUnit#SECONDS}</li>
    * </ol>
    *
    * In many cases, use of {@link Objects#requireNonNullElse(Object, Object)} is not
    * necessary because Java's string conversion will perform the same effect.
    * {@link Objects#requireNonNullElse(Object, Object)} is necessary when Java is not
    * able to implicitly convert to a {@link String} or when the potentially {@code null}
    * object is not a {@link String} or when the object to have a default returned
    * when it is {@code null} is of class other than {@link String}.
    */
   public void demonstrateNullSafeObjectsRequireNonNullElse()
   {
      executeOperation(
         "Null-safe String Representation with Objects.requireNonNullElse(Object, Object)",
         () -> "The value of the 'null' object is '"
            + Objects.requireNonNullElse(NULL_OBJECT, "null") + "'");

      executeOperation("Null-safe TimeUnit access with Objects.requireNonNullElse(Object, Object)",
         () -> "The value used instead of 'null' TimeUnit is '"
            + Objects.requireNonNullElse(NULL_TIME_UNIT, TimeUnit.SECONDS) + "'");
   }

   /**
    * Demonstrates that comparing a potentially {@code null} enum is
    * {@code null}-safe when the {@code ==} operator (or {@code !=}
    * operator) is used, but that potentially comparing a {@code null}
    * enum using {@link Enum#equals(Object)} results in a
    * {@link NullPointerException}.
    *
    * See also https://marxsoftware.blogspot.com/2011/07/use-to-compare-java-enums.html.
    */
   public void demonstrateEnumComparisons()
   {
      executeOperation(
         "Using == with enums is null Safe",
         () -> NULL_TIME_UNIT == TimeUnit.MINUTES);
      executeOperation(
         "Using .equals On null Enum is NOT null Safe",
         () -> NULL_TIME_UNIT.equals(TimeUnit.MINUTES));
   }

   /**
    * Demonstrates that comparisons against known non-{@code null} strings can be
    * {@code null}-safe as long as the known non-{@code null} string is on the left
    * side of the {@link Object#equals(Object)} method ({@link Object#equals(Object)})
    * is called on the known non-{@code null} string rather than on the unknown
    * and potential {@code null}.
    */
   public void demonstrateLiteralComparisons()
   {
      executeOperation(
         "Using known non-null literal on left side of .equals",
         () -> "Inspired by Actual Events".equals(NULL_STRING));
      executeOperation(
         "Using potential null variable on left side of .equals can result in NullPointerExeption",
         () -> NULL_STRING.equals("Inspired by Actual Events"));
   }

   /**
    * Demonstrates that case-insensitive comparisons against known non-{@code null}
    * strings can be {@code null}-safe as long as the known non-{@code null} string
    * is on the left side of the {@link Object#equals(Object)} method
    * ({@link Object#equals(Object)}) is called on the known non-{@code null} String
    * rather than on the unknown potential {@code null}).
    */
   public void demonstrateLiteralStringEqualsIgnoreCase()
   {
      executeOperation(
         "String.equalsIgnoreCase(String) is null-safe with literal string on left side of method",
         () -> "Inspired by Actual Events".equalsIgnoreCase(NULL_STRING));
      executeOperation(
         "Using potential null variable of left side of .equalsIgnoreCase can result in NPE",
         () -> NULL_STRING.equalsIgnoreCase("Inspired by Actual Events"));
   }

   /**
    * Demonstrates that comparisons of even potential {@code null}s is safe
    * when {@link Objects#equals(Object, Object)} is used.
    */
   public void demonstrateObjectsEquals()
   {
      executeOperation(
         "Using Objects.equals(Object, Object) is null-safe",
         () -> Objects.equals(NULL_OBJECT, LocalDateTime.now()));
   }

   /**
    * Demonstrates that {@link Objects#hashCode(Object)} is {@code null}-safe.
    */
   public void demonstrateObjectsHashCode()
   {
      executeOperation(
         "Using Objects.hashCode(Object) is null-safe",
         () -> Objects.hashCode(NULL_OBJECT));
   }

   /**
    * Demonstrates that {@link Objects#hash(Object...)} is {@code null}-safe.
    */
   public void demonstrateObjectsHash()
   {
      executeOperation(
         "Using Objects.hash(Object...) is null-safe",
         () -> Objects.hash(NULL_OBJECT, NULL_STRING, NULL_TIME_UNIT));
   }

   /**
    * Demonstrates using {@link Objects#requireNonNull(Object)} and
    * {@link Objects#requireNonNull(Object, String)} to take control of
    * when an {@link NullPointerException} is thrown. The method accepting
    * a {@link String} also allows control of the context that is provided
    * in the exception message.
    *
    * It is not demonstrated here, but a similar method is
    * {@link Objects#requireNonNull(Object, Supplier)} that allows a
    * {@link Supplier} to be used to provide the message for when an
    * unexpected {@code null} is encountered.
    */
   public void demonstrateObjectsRequiresNonNullMethods()
   {
      executeOperation(
         "Using Objects.requireNonNull(T)",
         () -> Objects.requireNonNull(NULL_OBJECT));

      executeOperation(
         "Using Objects.requireNonNull(T, String)",
         () -> Objects.requireNonNull(NULL_OBJECT, "Cannot perform logic on supplied null object."));
   }

   /**
    * Executes supplied {@link Supplier} and catches any {@link Exception}
    * encountered when that {@link Supplier} is invoked.
    *
    * @param demonstrationName Name of the demonstration associated with
    *    the supplied {@link Supplier}.
    * @param supplier The operation to be executed.
    * @param <T> Type associated with the supplier.
    */
   public <T> void executeOperation(
      final String demonstrationName, final Supplier<T> supplier)
   {
      try
      {
         supplier.get();
         LOGGER.info("Demonstration '" + demonstrationName + "' completed without exception!");
      }
      catch (Exception exception)
      {
         LOGGER.severe( "Exception encountered while trying to run operation for demonstration '"
            + demonstrationName + "': " + exception);
      }
   }

   /**
    * Main executable function to run the demonstrations related to
    * {@code null} avoidance tactics.
    *
    * @param arguments Command-line arguments; none expected.
    */
   public static void main(final String[] arguments)
   {
      final NullSafeTactics demo = new NullSafeTactics();

      // Demonstrate avoidance of unnecessary NullPointerExceptions
      demo.demonstrateNullSafeStringConversion();
      demo.demonstrateNullUnsafeExplicitToString();
      demo.demonstrateNullSafeStringValueOf();
      demo.demonstrateObjectsToString();
      demo.demonstrateObjectsToStringWithDefault();
      demo.demonstrateNullSafeObjectsRequireNonNullElse();
      demo.demonstrateEnumComparisons();
      demo.demonstrateLiteralComparisons();
      demo.demonstrateLiteralStringEqualsIgnoreCase();
      demo.demonstrateObjectsEquals();
      demo.demonstrateObjectsHashCode();
      demo.demonstrateObjectsHash();

      // Demonstrate useful handling of necessary NullPointerExceptions
      demo.demonstrateObjectsRequiresNonNullMethods();
   }
}
