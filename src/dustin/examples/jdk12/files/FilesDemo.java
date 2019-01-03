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
