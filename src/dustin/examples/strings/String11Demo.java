package dustin.examples.strings;

import static java.lang.System.out;

import java.util.stream.Stream;

/**
 * Demonstrates methods added to {@code java.lang.String} for <b>JDK 11</b>:
 * <ul>
 * <li>{@code String.repeat(int)}</li>
 * <li>{@code String.lines()}</li>
 * <li>{@code String.strip()}</li>
 * <li>{@code String.stripLeading()}</li>
 * <li>{@code String.stripTrailing()}</li>
 * <li>{@code String.isBlank()}</li>
 * </ul>
 */
public class String11Demo
{
   /**
    * Demonstrate method {@code String.lines()} added with JDK 11.
    */
   public static void demonstrateStringLines()
   {
      final String originalString = prepareStringWithLineTerminators();
      final String stringWithoutLineSeparators
         = originalString.replaceAll("\\n", "\\\\n");
      writeHeader("String.lines() on '"  + stringWithoutLineSeparators  + "'");
      final Stream<String> strings = originalString.lines();
      strings.forEach(out::println);
   }

   /**
    * Demonstrate method {@code String.strip()} added with JDK 11.
    */
   public static void demonstrateStringStrip()
   {
      final String originalString = prepareStringSurroundedBySpaces();
      writeHeader("String.strip() on '" + originalString + "'");
      out.println("'" + originalString.strip() + "'");
   }

   /**
    * Demonstrate method {@code String.stripLeading()} added with JDK 11.
    */
   public static void demonstrateStringStripLeading()
   {
      final String originalString = prepareStringSurroundedBySpaces();
      writeHeader("String.stripLeading() on '" + originalString + "'");
      out.println("'" + originalString.stripLeading() + "'");
   }

   /**
    * Demonstrate method {@code String.stripTrailing()} added with JDK 11.
    */
   public static void demonstrateStringStripTrailing()
   {
      final String originalString = prepareStringSurroundedBySpaces();
      writeHeader("String.stripTrailing() on '" + originalString + "'");
      out.println("'" + originalString.stripTrailing() + "'");
   }

   /**
    * Demonstrate method {@code String.isBlank()} added with JDK 11.
    */
   public static void demonstrateStringIsBlank()
   {
      writeHeader("String.isBlank()");
      final String emptyString = "";
      out.println("Empty String -> " + emptyString.isBlank());
      final String onlyLineSeparator = System.getProperty("line.separator");
      out.println("Line Separator Only -> " + onlyLineSeparator.isBlank());
      final String tabOnly = "\t";
      out.println("Tab Only -> " + tabOnly.isBlank());
      final String spacesOnly = "   ";
      out.println("Spaces Only -> " + spacesOnly.isBlank());
   }

   /**
    * Provide a {@code String} that includes line terminators.
    *
    * @return {@code String} with line terminators.
    */
   private static String prepareStringWithLineTerminators()
   {
      return "Inspired\nby\nActual\nEvents";
   }

   /**
    * Provide a string surrounded by spaces.
    *
    * @return String with spaces on front and end.
    */
   private static String prepareStringSurroundedBySpaces()
   {
      return "     Inspired by Actual Events    ";
   }

   /**
    * Write provided {@code String} in header. Note that this
    * implementation uses {@code String.repeat(int)}.
    *
    * @param headerText Title of header.
    */
   private static void writeHeader(final String headerText)
   {
      final String headerSeparator = "=".repeat(headerText.length()+4);
      out.println("\n" + headerSeparator);
      out.println("= " + headerText + " =");
      out.println(headerSeparator);
   }

   /**
    * Executable function that executes all demonstrations.
    *
    * @param arguments Command-line arguments: none expected.
    */
   public static void main(final String[] arguments)
   {
      demonstrateStringLines();
      demonstrateStringStrip();
      demonstrateStringStripLeading();
      demonstrateStringStripTrailing();
      demonstrateStringIsBlank();
   }
}
