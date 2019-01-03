package dustin.examples.jdk12.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.System.out;

/**
 * Demonstrate {@code Files.mismatch(Path,Path)} introduced with JDK 12
 * and useful for determining if two files have the same content even
 * if they're not the same files.
 */
public class FilesDemo
{
   /**
    * Demonstrates use of {@code Files.isSameFile} to determine
    * whether two files are the same.
    * 
    * @param file1Path Path of first file.
    * @param file2Path Path of second file.
    */
   private static void demonstrateIsSameFile(
      final Path file1Path, final Path file2Path)
   {
      try
      {
         out.println("\nFiles '" + file1Path.getFileName() + "' and '"
            + file2Path.getFileName() + "' are "
            + (Files.isSameFile(file1Path, file2Path) ? "the" : "NOT the")
            + " same.\n\n");
      }
      catch (IOException ioException)
      {
         ioException.printStackTrace();
      }
   }

   /**
    * Demonstrates use of {@code Files.mismatch} method added
    * to JDK 12 to determine whether two files are the same.
    * 
    * @param file1Path Path of first file.
    * @param file2Path Path of second file.
    */
   private static void demonstrateMismatch(
      final Path file1Path, final Path file2Path)
   {
      try
      {
         out.println("\nFiles '" + file1Path.getFileName() + "' and '"
            + file2Path.getFileName() + "' are "
            + (Files.mismatch(file1Path, file2Path) == -1 ? "the" : "NOT the")
            + " same content.\n\n");
      }
      catch (IOException ioException)
      {
         ioException.printStackTrace();
      }
   }

   /**
    * Main executable function to be demonstrate determining if two
    * files are the same via {@code Files.isSameFile} and JDK 12's
    * new {@code Files.mismatch} methods.
    * 
    * @param arguments Command-line arguments: the paths of the two
    *    files to be compared as part of this demonstration are
    *    expected.
    */
   public static void main(final String[] arguments)
   {
      if (arguments.length < 2)
      {
         out.println("USAGE: FilesDemo <file1Name> <file2Name>");
         return;
      }

      final String file1Name = arguments[0];
      final Path file1Path = Path.of(file1Name);
      final String file2Name = arguments[1];
      final Path file2Path = Path.of(file2Name);
      demonstrateIsSameFile(file1Path, file2Path);
      demonstrateMismatch(file1Path, file2Path);
   }
}
