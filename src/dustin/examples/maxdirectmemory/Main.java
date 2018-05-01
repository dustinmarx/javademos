package dustin.examples.maxdirectmemory;

import static java.lang.System.out;

import sun.misc.SharedSecrets;
import sun.misc.VM;

public class Main
{
   /**
    * Write amount of direct memory used to standard output
    * using SharedSecrets, JavaNetAccess, the direct Buffer Pool,
    * and methods getMemoryUsed() and getTotalCapacity().
    */
   public static void writeUsedDirectMemoryToStdOut()
   {
      final double sharedSecretsMemoryUsed =
         MemoryUnit.BYTES.toMegaBytes(
            SharedSecrets.getJavaNioAccess().getDirectBufferPool().getMemoryUsed());
      out.println(
         "sun.misc.SharedSecrets.getJavaNioAccess().getDirectBufferPool().getMemoryUsed(): "
            + sharedSecretsMemoryUsed + " MB");
      final double sharedSecretsTotalCapacity =
         MemoryUnit.BYTES.toMegaBytes(SharedSecrets.getJavaNioAccess().getDirectBufferPool().getTotalCapacity());
      out.println("sun.misc.SharedSecrets.getJavaNioAccess().getDirectBufferPool().getTotalCapacity(): "
         + sharedSecretsTotalCapacity + " MB");
   }

   /**
    * Write maximum direct memory size set (explicitly or
    * implicitly) for this VM instance using VM's
    * method maxDirectMemory().
    */
   public static void writeMaximumDirectMemorySizeToStdOut()
   {
      final double vmSize =
         MemoryUnit.BYTES.toMegaBytes(VM.maxDirectMemory());
      out.println(
         "sun.misc.VM.maxDirectMemory(): " + vmSize + " MB");
   }

   public static void main(final String[] arguments)
   {
      writeUsedDirectMemoryToStdOut();
      writeMaximumDirectMemorySizeToStdOut();
   }
}
