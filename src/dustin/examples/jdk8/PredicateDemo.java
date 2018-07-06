package dustin.examples.jdk8;

import static java.lang.System.out;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Demonstrate JDK standard functional interface {@code Predicate}
 * through use of standard Java APIs that use it.
 */
public class PredicateDemo
{
   /** United States Telephone Number Pattern. */
   private final static Pattern PATTERN = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");

   /**
    * Demonstrate use of {@code Optional.filter(Predicate)} on an
    * {@code Optional<Boolean>}.
    */
   public static void demonstrateOptionalFilterOnBoolean()
   {
      out.print("\nfalse: ");
      getOptionalBoolean(false).filter(b -> b).ifPresent(out::print);
      out.print("\ntrue:  ");
      getOptionalBoolean(true).filter(b -> b).ifPresent(out::print);
      out.print("\nnull:  ");
      getOptionalBoolean(null).filter(b -> b).ifPresent(out::print);
   }

   /**
    * Demonstrate use of {@code Optional.filter(Predicate)} on an
    * {@code Optional<Float>}.
    */
   public static void demonstrateOptionalFilterOnFloat()
   {
      out.print("\n3.14: ");
      getOptionalFloat(3.14f).filter(f -> f > 0.0).ifPresent(out::print);
      out.print("\n-2.5: ");
      getOptionalFloat(-2.5f).filter(f -> f > 0.0).ifPresent(out::print);
      out.print("\nnull: ");
      getOptionalFloat(null).filter(f -> f > 0.0).ifPresent(out::print);
   }

   /**
    * Demonstrates use of {@code Stream.filter(Predicate}}.
    */
   public static void demonstrateStreamFilter()
   {
      final int maximum = 100;
      out.println("\nThe probable prime numbers between 1 and " + maximum + " are: ");
      final Stream<BigInteger> bigIntegers = getConsecutiveBigIntegers(maximum);
      bigIntegers.filter(bi -> bi.isProbablePrime(100)).forEach(pp -> out.println(pp + ","));
   }

   /**
    * Demonstrates use of {@code Pattern.asPredicate()} to provide
    * a {@code Predicate} that can be used with {@code Stream.filter()}.
    */
   public static void demonstratePatternAsPredicateInFilter()
   {
      final long count
         = getPotentialTelephoneNumbers().stream()
            .filter(PATTERN.asPredicate())
            .peek(out::println)
            .count();
      out.println(count + " valid telephone numbers.");
   }

   /**
    * Demonstrates use of {@code Collection.removeIf(Predicate)}
    * in conjunction with {@code Predicate.negate()}.
    */
   public static void demonstrateCollectionRemoveIf()
   {
      final Set<String> telephoneNumbers = new HashSet<>(getPotentialTelephoneNumbers());
      telephoneNumbers.removeIf(PATTERN.asPredicate().negate());
      out.println(telephoneNumbers);
   }

   /**
    * Demonstrate use of {@code Stream.allMatch(Predicate)}.
    */
   public static void demonstrateStreamAllMatch()
   {
      final Set<String> names = getNames();
      final boolean allNamesSixDigits = names.stream()
         .allMatch(name -> name.length() == 6);
      out.println("Are all names " + names + " six digits? " + allNamesSixDigits);
   }

   /**
    * Demonstrate use of {@code Stream.anyMatch(Predicate)}.
    */
   public static void demonstrateStreamAnyMatch()
   {
      final Set<String> names = getNames();
      final boolean anyNamesSixDigits = names.stream()
         .anyMatch(name -> name.length() == 6);
      out.println("Are any names " + names + " six digits? " + anyNamesSixDigits);
   }

   /**
    * Demonstrate use of {@code Stream.noneMatch(Predicate)}.
    */
   public static void demonstrateStreamNoneMatch()
   {
      final Set<String> names = getNames();
      final boolean noNamesSixDigits = names.stream()
         .noneMatch(name -> name.length() == 6);
      out.println("Are no names " + names + " six digits? " + noNamesSixDigits);
      final boolean noNamesFourDigits = names.stream()
         .noneMatch(name -> name.length() == 4);
      out.println("Are no names " + names + " four digits? " + noNamesFourDigits);
   }

   /**
    * Demonstrate use of {@code Collectors.partitioningBy(Predicate)}.
    */
   public static void demonstrateCollectorsPartitioningBy()
   {
      final Map<Boolean, List<Integer>> evensAndOdds
         = getConsecutiveIntegers(100)
            .collect(Collectors.partitioningBy(integer -> integer % 2 == 0));
      out.println("Evens: " + evensAndOdds.get(Boolean.TRUE));
      out.println("Odds:  " + evensAndOdds.get(Boolean.FALSE));
   }

   /**
    * Provides {@code Stream} of consecutive integers beginning with 1
    * and ending with the provided integer.
    *
    * @param maximumInteger Maximum integer for range to be returned. If this
    *    value is less than 2, 2 will be used.
    * @return Consecutive integers beginning with 1 and ending with the
    *    provided maximum (or 2 if 2 is greater than the provided maximum).
    */
   public static Stream<Integer> getConsecutiveIntegers(final int maximumInteger)
   {
      return IntStream.rangeClosed(1, Math.max(2, maximumInteger)).boxed();
   }

   /**
    * Provides {@code Stream} of consecutive {@code BigInteger}s beginning with
    * {@code BigInteger.ONE} and ending with the {@code BigInteger} equivalent
    * of the provided integer.
    *
    * @param maximumInteger Maximum integer for range to be returned. If this
    *    value is less than 2, 2 will be used.
    * @return Consecutive instances of {@code BigInteger} beginning with
    *    {@code BigInteger.ONE} and ending with the {@code BigInteger}
    *    equivalent of the provided maximum (or of 2 if 2 is greater than
    *    the provided maximum).
    */
   public static Stream<BigInteger> getConsecutiveBigIntegers(final int maximumInteger)
   {
      return IntStream.rangeClosed(1, Math.max(2, maximumInteger)).mapToObj(BigInteger::valueOf);
   }

   /**
    * Provides {@code Set} of potential telephone numbers.
    *
    * @return Potential telephone numbers.
    */
   private static Set<String> getPotentialTelephoneNumbers()
   {
      return Set.of(
         "555555-77-77", "555-555-7777", "555-55-57777", "5-55-5557777");
   }

   /**
    * Provide names (strings).
    *
    * @return Names (strings).
    */
   private static Set<String> getNames()
   {
      return Set.of("Dustin", "Inspired", "Actual", "Events");
   }

   /**
    * Provides {@code Optional<Boolean>} that wraps the provided
    * {@code Boolean}.
    *
    * @param bool {@code Boolean} to be wrapped by the return {@code Optional}.
    * @return {@code Optional}-wrapped {@code Boolean}.
    */
   private static Optional<Boolean> getOptionalBoolean(final Boolean bool)
   {
      if (bool == null)
      {
         return Optional.empty();
      }
      return Optional.of(bool);
   }

   /**
    * Provides {@code Optional<Float>} that wraps the provided
    * {@code Float}.
    *
    * @param floating {@code Float} to be wrapped by the return {@code Optional}.
    * @return {@code Optional}-wrapped {@code Float}.
    */
   private static Optional<Float> getOptionalFloat(final Float floating)
   {
      if (floating == null)
      {
         return Optional.empty();
      }
      return Optional.of(floating);
   }

   /**
    * Main executable function that executes all demonstration functions.
    *
    * @param arguments Command-line arguments: none expected.
    */
   public static void main(final String[] arguments)
   {
      demonstrateOptionalFilterOnBoolean();
      demonstrateOptionalFilterOnFloat();
      demonstrateStreamFilter();
      demonstratePatternAsPredicateInFilter();
      demonstrateCollectionRemoveIf();
      demonstrateStreamAllMatch();
      demonstrateStreamAnyMatch();
      demonstrateStreamNoneMatch();
      demonstrateCollectorsPartitioningBy();
   }
}
