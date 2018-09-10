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
      out.println("\t" + integer + " ==> " + numericString);
   }

   /**
    * Demonstrate enhanced switch statement used to assign
    * a local variable.
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
      out.println("\t" + integer + " ==> " + numericString);
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
      out.println("\t" + integer + " ==> " + numericString);
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
      out.println("\t" + integer + " ==> " + numericString);
   }

   /**
    * Demonstrate that multiple constants can be associated with
    * a single {@code case} and used in conjunction with a
    * {@code switch} expression that uses the "arrow" syntax.
    */
   public static void demonstrateLabelRulesWithSharedCases()
   {
      final int integer = 7;
      out.println("Multiple Case Labels:");
      final String numericString =
         switch (integer)
         {
            case 0 -> "zero";
            case 1, 3, 5, 7, 9 -> "odd";
            case 2, 4, 6, 8, 10 -> "even";
            default -> "N/A";
         };
      out.println("\t" + integer + " ==> " + numericString);
   }

   /**
    * Demonstrate that multiple constants can be associated with
    * a single {@code case} and used in conjunction with a
    * {@code switch} statement that uses the traditional colon and
    * {@code break} syntax.
    */
   public static void demonstrateBlockedStatementsWithSharedCases()
   {
      final int integer = 6;
      out.println("Multiple Case Labels:");
      String numericString;
      switch (integer)
      {
         case 0:
            numericString = "zero";
            break;
         case 1, 3, 5, 7, 9:
            numericString = "odd";
            break;
         case 2, 4, 6, 8, 10:
            numericString = "even";
            break;
         default:
            numericString = "N/A";
      };
      out.println("\t" + integer + " ==> " + numericString);
   }

//   /**
//    * WARNING - This does NOT compile, even with JDK 12 Early
//    * Access Builds and --enable-preview because it is
//    * nonsensical to have a "statement" return a value; that
//    * is what an expression should be used for.
//    */
//   public static void demonstrateSwitchStatementReturnedLabel()
//   {
//      final int integer = 4;
//      switch (integer)
//      {
//         case 1:
//            break "one";
//         case 2:
//            break "two";
//         case 3:
//            break "three";
//         default:
//            break "N/A";
//      };
//   }

   /**
    * This demonstrates that a {@code switch} "expression" is
    * able to (and expected to) provide the "return" value for
    * a given {@code case} and {@code default} instead of being
    * a compiler error as it was for the "statement" example
    * demonstrated in method
    * {@link #demonstrateSwitchStatementReturnedLabel()}.
    */
   public static void demonstrateSwitchExpressReturnedLabel()
   {
      final int integer = 4;
      final String numericString =
      switch (integer)
      {
         case 1:
            break "one";
         case 2:
            break "two";
         case 3:
            break "three";
         default:
            break "N/A";
      };
   }

//   /**
//    * WARNING - This does not compile, even with JDK 12 Early
//    * Access Builds and --enable-preview and reports error message
//    * "error: not a statement" because it is expecting a
//    * {@code switch} "statement" but what is being provided to each
//    * {@code case} is NOT a statement.
//    */
//   public static void demonstrateSwitchStatementReturnedValueViaLabelRule()
//   {
//      final int integer = 5;
//      switch (integer)
//      {
//         case 1 -> "one";
//         case 2 -> "two";
//      };
//      out.println(numericString);
//   }

//   /**
//    * WARNING - This does NOT compile, even with JDK 12 Early
//    * Access Builds and --enable-preview because JEP 325 does
//    * not allow the "arrow" syntax to be mixed with the
//    * traditional colon/break syntax.
//    */
//   public static void demonstrateMixed()
//   {
//      final int integer = 3;
//      String numericString;
//      switch(integer)
//      {
//         case 1 :
//            numericString = "one";
//            break;
//         case 2 -> numericString = "two";
//         default -> numericString = "N/A";
//
//      }
//      return numericString;
//   }

//   /**
//    * WARNING - This method will not compile even with JDK 12
//    * Early Access Build 10 with --enable-preview because of
//    * error; "the switch expression does not cover all possible
//    * input values".
//    */
//   public static void demonstrateLackingCaseInSwitchExpression()
//   {
//      final int integer = 5;
//      String numericString =
//         switch (integer)
//         {
//            case 1 -> "one";
//            case 2 -> "two";
//         };
//      out.println(numericString);
//   }

   /**
    * Demonstrates calling a method in a switch statement.
    */
   public static void demonstrateCallingMethodInSwitchStatementCases()
   {
      final int integer = 4;
      switch (integer)
      {
         case 1 -> doItToIt(1);
         case 2 -> doItToIt(2);
         case 3 -> doItToIt(3);
         case 4 -> doItToIt(4);
      }
   }

   /**
    * Demonstrate calling a method in a switch expression.
    */
   public static void demonstrateCallingMethodInSwitchExpressionCases()
   {
      final int integer = 4;
      out.println("Expression called with: " + switch (integer)
      {
         case 1 -> doItAndReturn(1);
         case 2 -> doItAndReturn(2);
         case 3 -> doItAndReturn(3);
         case 4 -> doItAndReturn(4);
         default -> doItAndReturn(-1);
      });
   }

   private static void doItToIt(final int integer)
   {
      out.println("Called with '" + integer + "'.");
   }

   private static int doItAndReturn(final int integer)
   {
      return integer;
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
      demonstrateLabelRulesWithSharedCases();
      demonstrateBlockedStatementsWithSharedCases();
      demonstrateCallingMethodInSwitchStatementCases();
      demonstrateCallingMethodInSwitchExpressionCases();
   }
}
