package dustin.examples.jdk12.switchexp;

import static java.lang.System.out;

/**
 * Demonstrate JDK 12 "preview language feature"
 * switch expressions (JEP 325).
 */
public class SwitchExpressionsDemo
{
   /**
    * This example is adopted from the JEP 325 text
    * (http://openjdk.java.net/jeps/325).
    *
    * @param k Value to be switched upon.
    */
   static void howMany(final int k)
   {
      switch (k)
      {
         case 1 -> out.println("\tone");
         case 2 -> out.println("\ttwo");
         case 3 -> out.println("\tmany");
      }
   }

   /**
    * Execute demonstration discussed in JEP 325 using custom
    * method {@link #howMany(int)}.
    */
   public static void demonstrateHowMany()
   {
      out.println("JEP 325 Example:");
      howMany(1);
      howMany(2);
      howMany(3);
   }

   /**
    * Demonstrate traditional switch statement assigned to
    * local variable.
    */
   public static void demonstrateTraditionalSwitchStatement()
   {
      out.println("Traditional Switch Statement:");
      final int integer = 3;
      String numericString;
      switch (integer)
      {
         case 1 :
            numericString = "one";
            break;
         case 2 :
            numericString = "two";
            break;
         case 3:
            numericString = "three";
            break;
         default:
            numericString = "N/A";
      }
      out.println("\tNumeric String is: " + numericString);
   }

   /**
    * Demonstrate enhanced switch statement assigned to
    * local variable.
    */
   public static void demonstrateEnhancedSwitchStatement()
   {
      out.println("Enhanced Switch Statement:");
      final int integer = 2;
      String numericString;
      switch (integer)
      {
         case 1 -> numericString = "one";
         case 2 -> numericString = "two";
         case 3 -> numericString = "three";
         default -> numericString = "N/A";
      }
      out.println("\tNumeric String is: " + numericString);
   }

   /**
    * Demonstrate switch expression using colons and breaks.
    */
   public static void demonstrateSwitchExpressionWithBreaks()
   {
      final int integer = 1;
      out.println("Switch Expression with Colons/Breaks:");
      final String numericString =
         switch (integer)
         {
            case 1 :
               break "uno";
            case 2 :
               break "dos";
            case 3 :
               break "tres";
            default :
               break "N/A";
         };
      out.println("\t" + numericString);
   }

   /**
    * Demonstrate switch expressions using "arrow" syntax.
    */
   public static void demonstrateSwitchExpressionWithArrows()
   {
      final int integer = 4;
      out.println("Switch Expression with Arrows:");
      final String numericString =
      switch (integer)
      {
         case 1 -> "uno";
         case 2 -> "dos";
         case 3 -> "tres";
         case 4 -> "quatro";
         default -> "N/A";
      };
      out.println("\t" + numericString);
   }

   /**
    * Main executable function to run demonstrations.
    *
    * @param arguments Command-line arguments: none expected.
    */
   public static void main(final String[] arguments)
   {
      demonstrateHowMany();
      demonstrateTraditionalSwitchStatement();
      demonstrateEnhancedSwitchStatement();
      demonstrateSwitchExpressionWithBreaks();
      demonstrateSwitchExpressionWithArrows();
   }
}
