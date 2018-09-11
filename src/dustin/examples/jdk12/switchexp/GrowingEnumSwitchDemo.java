package dustin.examples.jdk12.switchexp;

import static java.lang.System.out;

/**
 * Demonstrates implicit handling of expanding enum
 * definition related to JEP 325 switch expressions and
 * switch statements.
 */
public class GrowingEnumSwitchDemo
{
   public static void printResponseStringFromStatement(final Response response)
   {
      out.println("Statement [" + response.name() + "]:");
      switch (response)
      {
         case YES:
            out.println("Si!");
            break;
         case NO:
            out.println("No!");
            break;
      }
   }

   public static void printResponseStringFromExpression(final Response response)
   {
      out.println("Expression [" + response.name() + "]:");
      out.println(
         switch (response)
         {
            case YES -> "Si!";
            case NO -> "No!";
         });
   }

   public static void main(final String[] arguments)
   {
      if (arguments.length < 1)
      {
         out.println("Provide an appropriate 'dustin.examples.jdk12.switchexp.Response' string as an argument.");
         System.exit(-1);
      }
      final String responseString = arguments[0];
      out.println("Processing string '" + responseString + "'.");
      final Response response = Response.valueOf(responseString);
      printResponseStringFromStatement(response);
      printResponseStringFromExpression(response);
   }
}
