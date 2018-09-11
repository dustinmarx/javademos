package dustin.examples.hashcodes;

import java.util.Arrays;
import java.util.Objects;

import static java.lang.System.out;

public class HashesComparedDemo
{
   public static void main(final String[] arguments)
   {
      final int[] integers = ArraysCreator.createArrayOfInts();
      out.println("Arrays.hashCode(Object[]) for int[]: " + Arrays.hashCode(integers));
      out.println("Objects.hash(Object...) for int[]:   " + Objects.hash(integers));
      out.println("Objects.hashCode(Object) for int[]:  " + Objects.hashCode(integers));

      final Integer[] refIntegers = ArraysCreator.createArrayOfIntegers();
      out.println("Arrays.hashCode(Object[]) for Integer[]: " + Arrays.hashCode(refIntegers));
      out.println("Objects.hash(Object...) for Integer[]:   " + Objects.hash(refIntegers));
      out.println("Objects.hashCode(Object) for Integer[]:  " + Objects.hashCode(refIntegers));

      final String[] strings = ArraysCreator.createArrayOfStrings();
      out.println("Arrays.hashCode(Object[]) for String[]: " + Arrays.hashCode(strings));
      out.println("Objects.hash(Object...) for String[]:   " + Objects.hash(strings));
      out.println("Objects.hashCode(Object) for String[]:  " + Objects.hashCode(strings));
   }
}
