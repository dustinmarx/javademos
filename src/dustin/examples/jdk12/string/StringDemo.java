package dustin.examples.jdk12.string;

import java.io.IOException;

import static java.lang.System.out;

/**
 * Examples of raw string literals (very slightly) adapted from
 * JEP 326 (https://openjdk.java.net/jeps/326) and
 * JDK-8196004 (https://bugs.openjdk.java.net/browse/JDK-8196004).
 */
public class StringDemo
{
   private static void demonstrateFilePaths()
   {
      try
      {
         out.println("Running: " + Runtime.getRuntime().exec(`"C:\ls.bat"`));
      }
      catch (IOException ioEx)
      {
         out.println("ERROR: " + ioEx);
      }
   }

   private static void demonstrateHtml()
   {
      String html = `
            <html>
               <body>
                  <p>Hello World.</p>
               </body>
            </html>
         `;
      out.println(html.align());
   }

   private static void demonstrateSql()
   {
      final String query = `
          SELECT EMP_ID, LAST_NAME
            FROM EMPLOYEE_TB
           WHERE CITY = INDIANAPOLIS
           ORDER BY EMP_ID, LAST_NAME;
         `;
      out.println(query);
   }

   private static void demonstratePolyglot()
   {
      String script = `
      function hello() {
         print('"Hello World"');
      }
      
      hello();
      `;
      out.println(script);
   }

   /**
    * Run demonstration of new raw string literals and new
    * {@code String} methods.
    *
    * @param arguments Command-line arguments: none expected.
    */
   public static void main(final String[] arguments)
   {
      demonstrateFilePaths();
      demonstrateHtml();
      demonstrateSql();
      demonstratePolyglot();
   }
}
