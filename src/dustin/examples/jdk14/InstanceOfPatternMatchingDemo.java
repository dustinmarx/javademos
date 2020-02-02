package dustin.examples.jdk14;

import static java.lang.System.out;

/**
 * This class demonstrates the JDK 14-introduced preview feature (JEP 305)
 * {@code instanceof} using pattern matching (see https://openjdk.java.net/jeps/305).
 */
public class InstanceOfPatternMatchingDemo
{
   static void makeAnimalNoises(final Object animal)
   {
      if (animal instanceof Dog dog)
      {
         out.println(dog.bark());
      }
      else if (animal instanceof Cat cat)
      {
         out.println(cat.meow());
      }
      else if (animal instanceof Duck duck)
      {
         out.println(duck.quack());
      }
      else if (animal instanceof Horse horse)
      {
         out.println(horse.neigh());
      }
      else if (animal instanceof Cow cow)
      {
         out.println(cow.moo());
      }
      else if (animal instanceof Lion lion)
      {
         out.println(lion.roar());
      }
      else
      {
         out.println("ERROR: Unexpected animal: " + animal);
      }
   }

   public static void main(final String[] arguments)
   {
      final Horse horse = new Horse();
      makeAnimalNoises(horse);
   }

   public static class Dog
   {
      public String bark()
      {
         return "bark";
      }
   }

   public static class Cat
   {
      public String meow()
      {
         return "meow";
      }
   }

   public static class Duck
   {
      public String quack()
      {
         return "quack";
      }
   }

   public static class Horse
   {
      public String neigh()
      {
         return "neigh";
      }
   }

   public static class Cow
   {
      public String moo()
      {
         return "moo";
      }
   }

   public static class Lion
   {
      public String roar()
      {
         return "roar";
      }
   }
}
