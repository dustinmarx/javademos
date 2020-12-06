package dustin.examples.jdk16.streams;

import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * Demonstrates {@link Stream#toList()} introduced
 * via JDK 16 Early Access Build #27.
 */
public class StreamToListDemo
{
   /**
    * Generates sample {@link Stream} of TimeZone IDs from
    * {@link TimeZone#getAvailableIDs()} to be used in these
    * demonstrations.
    *
    * @return Stream of TimeZone IDs used in these demonstrations.
    */
   private static Stream<String> generateTimeZoneIdsStream()
   {
      return Stream.of(TimeZone.getAvailableIDs());
   }

   /**
    * Analyzes the supplied {@code List} and writes to standard output
    * some key characteristics of the supplied {@code List}.
    *
    * @param listDescription Description of {@code List} to be analyzed.
    * @param listUnderAnalysis {@code List} to be analyzed.
    */
   private static void analyzeList(
      final String listDescription, final List<String> listUnderAnalysis)
   {
      out.println(listDescription + ": ");
      out.println("\tClass Type: " + listUnderAnalysis.getClass().getCanonicalName());
      out.println("\tAble to add to List? " + isListAddCapable(listUnderAnalysis));
      out.println("\tAble to sort List?   " + isListSortable(listUnderAnalysis));
   }

   /**
    * Indicates whether new elements can be added to the supplied {@code List}.
    *
    * @param listToBeAddedTo {@code List} to which addition of a new
    *    element will be attempted.
    * @return {@code true} if I can add an element to the supplied
    *    {@code List} or {@code false} if I cannot add an element to it.
    */
   private static boolean isListAddCapable(final List<String> listToBeAddedTo)
   {
      final String stringToAdd = "Test";
      boolean valueAdded;
      try
      {
         valueAdded = listToBeAddedTo.add(stringToAdd);
      }
      catch (UnsupportedOperationException uoEx)
      {
         valueAdded = false;
      }
      return valueAdded;
   }

   /**
    * Indicates whether the supplied {@code List} can be sorted.
    *
    * @param listToBeSorted {@code List} against which sorting will be
    *    attempted.
    * @return {@code true} if supplied {@code List} can be sorted or
    *    {@code false} if supplied {@code List} cannot be sorted.
    */
   private static boolean isListSortable(final List<String> listToBeSorted)
   {
      boolean sorted;
      try
      {
         listToBeSorted.sort(String::compareToIgnoreCase);
         sorted = true;
      }
      catch (UnsupportedOperationException uoEx)
      {
         sorted = false;
      }
      return sorted;
   }

   /**
    * Main executable for demonstrating contrasts between a {@code List}
    * extracted from a {@code Stream} using {@link Collectors#toList()}
    * and a {@code List} extracted from a {@code Stream} using the JDK 16
    * introduced {@link Stream#toList()}.
    *
    * @param arguments Command-line arguments; none expected.
    */
   public static void main(final String[] arguments)
   {
      final List<String> timeZoneIdsCollectorsToList
         = generateTimeZoneIdsStream().collect(Collectors.toList());
      analyzeList("Stream.collect(Collectors.toList())", timeZoneIdsCollectorsToList);

      final List<String> timeZoneIdsStreamToList
         = generateTimeZoneIdsStream().toList();
      analyzeList("Stream.toList()", timeZoneIdsStreamToList);

      // This List is NOT Stream-generated, but is included here to demonstrate the
      // characteristics of a List generated with List.of().
      analyzeList("[NOT Stream] List.of()", List.of(TimeZone.getAvailableIDs()));

      out.println("Equal Lists?: " + timeZoneIdsCollectorsToList.equals(timeZoneIdsStreamToList));
   }
}
