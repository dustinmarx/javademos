package dustin.examples.jdk11.strings;

import java.util.Objects;

import static java.lang.System.out;

/**
 * Demonstrates construction of portions of SQL
 * {@link java.sql.PreparedStatement}s with varying
 * number of parameters' placeholders.
 *
 * Demonstrates building the portion of the SQL statement
 * with multiple placeholders (?) using the {@code IN} and
 * {@code OR} approaches. Examples include "traditional"
 * implementations and implementations taking advantage of
 * {@link String}'s {@link String#repeat(int)} method.
 */
public class DynamicPreparedStatementParameters
{
   /**
    * Demonstrates "traditional" approach for building up the
    * "IN" portion of a SQL statement with multiple parameters
    * that uses a conditional within a loop on the number of
    * parameters to determine how to best handle each.
    *
    * @param columnName Name of database column to be referenced
    *    in the "IN" clause.
    * @param numberPlaceholders Number of parameters for which
    *    placeholder question marks ("?") need to be added.
    * @return The "IN" portion of a SQL statement with the
    *    appropriate number of placeholder question marks.
    */
   public String generateInClauseTraditionallyOne(
      final String columnName, final int numberPlaceholders)
   {
      final StringBuilder inClause = new StringBuilder();
      inClause.append(columnName + " IN (");
      for (int placeholderIndex = 0; placeholderIndex < numberPlaceholders; placeholderIndex++)
      {
         if (placeholderIndex != numberPlaceholders-1)
         {
            inClause.append("?, ");
         }
         else
         {
            inClause.append("?");
         }
      }
      inClause.append(")");
      return inClause.toString();
   }

   /**
    * Demonstrates "traditional" approach for building up the
    * "IN" portion of a SQL statement with multiple parameters
    * that treats each looped-over parameter index the same and
    * the removes the extraneous syntax from the end of the
    * generated string.
    *
    * @param columnName Name of database column to be referenced
    *    in the "IN" clause.
    * @param numberPlaceholders Number of parameters for which
    *    placeholder question marks ("?") need to be added.
    * @return The "IN" portion of a SQL statement with the
    *    appropriate number of placeholder question marks.
    */
   public String generateInClauseTraditionallyTwo(
      final String columnName, final int numberPlaceholders)
   {
      final StringBuilder inClause = new StringBuilder();
      inClause.append(columnName + " IN (");
      for (int placeholderIndex = 0; placeholderIndex < numberPlaceholders; placeholderIndex++)
      {
         inClause.append("?, ");
      }
      inClause.delete(inClause.length()-2, inClause.length());
      inClause.append(")");
      return inClause.toString();
   }

   /**
    * Demonstrates JDK 11 {@link String#repeat(int)} approach
    * for building up the "IN" portion of a SQL statement with
    * multiple parameters.
    *
    * @param columnName Name of database column to be referenced
    *    in the "IN" clause.
    * @param numberPlaceholders Number of parameters for which
    *    placeholder question marks ("?") need to be added.
    * @return The "IN" portion of a SQL statement with the
    *    appropriate number of placeholder question marks.
    */
   public String generateInClauseWithStringRepeat(
      final String columnName, final int numberPlaceholders)
   {
      return columnName + " IN (" + "?, ".repeat(numberPlaceholders-1) + "?)";
   }

   /**
    * Demonstrates "traditional" approach for building up the
    * "OR" portions of a SQL statement with multiple parameters
    * that uses a conditional within a loop on the number of
    * parameters to determine how to best handle each.
    *
    * @param columnName Name of database column to be referenced
    *    in the "OR" clauses.
    * @param numberPlaceholders Number of parameters for which
    *    placeholder question marks ("?") need to be added.
    * @return The "OR" portions of a SQL statement with the
    *    appropriate number of placeholder question marks.
    */
   public String generateOrClausesTraditionallyOne(
      final String columnName, final int numberPlaceholders)
   {
      final StringBuilder orClauses = new StringBuilder();
      for (int placeholderIndex = 0; placeholderIndex < numberPlaceholders; placeholderIndex++)
      {
         if (placeholderIndex != numberPlaceholders-1)
         {
            orClauses.append(columnName).append(" = ? OR ");
         }
         else
         {
            orClauses.append(columnName).append(" = ?");
         }
      }
      return orClauses.toString();
   }

   /**
    * Demonstrates "traditional" approach for building up the
    * "OR" portions of a SQL statement with multiple parameters
    * that treats each looped-over parameter index the same and
    * the removes the extraneous syntax from the end of the
    * generated string.
    *
    * @param columnName Name of database column to be referenced
    *    in the "OR" clauses.
    * @param numberPlaceholders Number of parameters for which
    *    placeholder question marks ("?") need to be added.
    * @return The "OR" portions of a SQL statement with the
    *    appropriate number of placeholder question marks.
    */
   public String generateOrClausesTraditionallyTwo(
      final String columnName, final int numberPlaceholders)
   {
      final StringBuilder orClauses = new StringBuilder();
      for (int placeholderIndex = 0; placeholderIndex < numberPlaceholders; placeholderIndex++)
      {
         orClauses.append(columnName + " = ? OR ");
      }
      orClauses.delete(orClauses.length()-4, orClauses.length());
      return orClauses.toString();
   }

   /**
    * Demonstrates JDK 11 {@link String#repeat(int)} approach
    * for building up the "OR" portions of a SQL statement with
    * multiple parameters.
    *
    * @param columnName Name of database column to be referenced
    *    in the "OR" clauses.
    * @param numberPlaceholders Number of parameters for which
    *    placeholder question marks ("?") need to be added.
    * @return The "OR" portions of a SQL statement with the
    *    appropriate number of placeholder question marks.
    */
   public String generateOrClausesWithStringRepeat(
      final String columnName, final int numberPlaceholders)
   {
      final String orPiece = columnName + " = ? OR ";
      return orPiece.repeat(numberPlaceholders-1) + columnName + " = ?";
   }

   /**
    * Main function for demonstrating dynamic generation of the
    * "IN" or "OR" clauses of a {@link java.sql.PreparedStatement}
    * based on a dynamic number of parameters.
    *
    * @param arguments Command-line arguments (none expected).
    */
   public static void main(final String[] arguments)
   {
      final DynamicPreparedStatementParameters instance = new DynamicPreparedStatementParameters();

      final String testColumnId = "id";

      final String traditionalInOne1 = instance.generateInClauseTraditionallyOne(testColumnId, 1);
      final String traditionalInTwo1 = instance.generateInClauseTraditionallyTwo(testColumnId, 1);
      out.println("Traditional 'IN' #1 (one element): \"" + traditionalInOne1 + "\"");
      out.println("Traditional 'IN' #2 (one element): \"" + traditionalInTwo1 + "\"");

      final String traditionalInOne4 = instance.generateInClauseTraditionallyOne(testColumnId,4);
      final String traditionalInTwo4 = instance.generateInClauseTraditionallyTwo(testColumnId,4);
      out.println("Traditional 'IN' #1 (four elements): \"" + traditionalInOne4 + "\"");
      out.println("Traditional 'IN' #2 (four elements): \"" + traditionalInTwo4 + "\"");

      final String newFangledIn1 = instance.generateInClauseWithStringRepeat(testColumnId, 1);
      final String newFangledIn4 = instance.generateInClauseWithStringRepeat(testColumnId, 4);
      out.println("New Fangled 'IN' (one element): \"" + newFangledIn1 + "\"");
      out.println("New Fangled 'IN' (four elements): \"" + newFangledIn4 + "\"");

      instance.assertEquality(traditionalInOne1, traditionalInTwo1);
      instance.assertEquality(traditionalInOne1, newFangledIn1);

      instance.assertEquality(traditionalInOne4, traditionalInTwo4);
      instance.assertEquality(traditionalInOne4, newFangledIn4);

      final String traditionalOrOne1 = instance.generateOrClausesTraditionallyOne(testColumnId, 1);
      final String traditionalOrTwo1 = instance.generateOrClausesTraditionallyTwo(testColumnId, 1);
      out.println("Traditional 'OR' #1 (one element): \"" + traditionalOrOne1 + "\"");
      out.println("Traditional 'OR' #2 (one element): \"" + traditionalOrTwo1 + "\"");

      final String traditionalOrOne4 = instance.generateOrClausesTraditionallyOne(testColumnId, 4);
      final String traditionalOrTwo4 = instance.generateOrClausesTraditionallyTwo(testColumnId, 4);
      out.println("Traditional 'OR' #1 (four elements): \"" + traditionalOrOne4 + "\"");
      out.println("Traditional 'OR' #2 (four elements): \"" + traditionalOrTwo4 + "\"");

      final String newFangledOr1 = instance.generateOrClausesWithStringRepeat(testColumnId, 1);
      final String newFangledOr4 = instance.generateOrClausesWithStringRepeat(testColumnId, 4);
      out.println("New Fangled 'OR' (one element): \"" + newFangledOr1 + "\"");
      out.println("New Fangled 'OR' (four elements): \"" + newFangledOr4 + "\"");

      instance.assertEquality(traditionalOrOne1, traditionalOrTwo1);
      instance.assertEquality(traditionalOrOne1, newFangledOr1);
   }

   /**
    * Compares the two provided objects and throws {@link RuntimeException}
    * if they are not considered equal. No exception is thrown when the
    * supplied objects are considered equal.
    *
    * @param first First object to be compared for equality; may be {@code null}.
    * @param second Second object to be compared for equality; may be {@code null}.
    * @throws RuntimeException Thrown if the the supplied parameters are considered
    *    NOT equal.
    */
   private void assertEquality(final Object first, final Object second)
   {
      if (!Objects.equals(first, second))
      {
         throw new RuntimeException("Supplied objects are NOT equal!\nfirst="
            + first + "\nsecond=" + second);
      }
   }
}
