package dustin.examples.toomanyquestions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

/**
 * Demonstrate access of prepared statement with too many placeholders ('?'s).
 */
public class Main
{
   /**
    * Oracle JDBC URL for HR schema.
    *
    * This should be adjusted appropriately for the target environment.
    */
   private final String ORACLE_XE_HR_URL = "jdbc:oracle:thin:hr/hr@localhost:1521/XE";

   /**
    * Provide access to database connection.
    *
    * @return Database connection.
    */
   public Connection getDatabaseConnection()
   {
      Connection connection;
      try
      {
         connection = DriverManager.getConnection(ORACLE_XE_HR_URL);
      }
      catch (SQLException sqlEx)
      {
         connection = null;
         out.println("ERROR: Unable to get database connection - " + sqlEx);
      }
      return connection;
   }

   /**
    * Constructs a query using '?' for placeholders and using
    * as many of these as specified with the int parameter.
    *
    * @param numberPlaceholders Number of placeholders ('?')
    *    to include in WHERE clause of constructed query.
    * @return SQL Query that has provided number of '?" placeholders.
    */
   private String buildQuery(final int numberPlaceholders)
   {
      final StringBuilder builder = new StringBuilder();
      builder.append("SELECT region_id FROM countries WHERE ");
      for (int count=0; count < numberPlaceholders-1; count++)
      {
         builder.append("region_id = ? OR ");
      }
      builder.append("region_id = ?");
      return builder.toString();
   }

   /**
    * Execute the provided query and populate a PreparedStatement
    * wrapping this query with the number of integers provided
    * as the second method argument.
    *
    * @param query Query to be executed.
    * @param numberValues Number of placeholders to be set in the
    *    instance of {@code PreparedStatement} used to execute the
    *    provided query.
    */
   private void executeQuery(final String query, final int numberValues)
   {
      try (final Connection connection = getDatabaseConnection();
           final PreparedStatement statement = connection.prepareStatement(query))
      {
         for (int count = 0; count < numberValues; count++)
         {
            statement.setInt(count+1, count+1);
         }
         final ResultSet rs = statement.executeQuery();
         while (rs.next())
         {
            out.println("Region ID: " + rs.getLong(1));
         }
      }
      catch (SQLException sqlException)
      {
         out.println("ERROR: Unable to execute query - " + sqlException);
      }
   }

   private void runDemonstration(final int numberValues)
   {
      final String query = buildQuery(numberValues);
      executeQuery(query, numberValues);
   }

   public static void main(final String[] arguments)
   {
      int numberValues = 1500;
      if (arguments.length < 1)
      {
         out.println(
              "You did not enter a number of values, so default of "
            + numberValues + " will be used.");
      }
      else
      {
         numberValues = Integer.parseInt(arguments[0]);
      }
      out.println("Using " + numberValues + " values!");
      final Main instance = new Main();
      instance.runDemonstration(numberValues);
   }
}
