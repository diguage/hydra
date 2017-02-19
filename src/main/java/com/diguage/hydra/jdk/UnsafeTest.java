package com.diguage.hydra.jdk;

/**
 * 参考 http://www.cnblogs.com/chenpi/p/5389254.html[sun.misc.Unsafe的理解 - 风一样的码农 - 博客园]
 *
 * @author diguage
 * @since 16/11/2016.
 */
import java.lang.reflect.Field;

import sun.misc.Unsafe;

class User {
  private String name = "";
  private int age = 0;
  private long id = 1;
  private double height = 1.72;

  public User() {
    this.name = "test";
    this.age = 22;
  }

  @Override
  public String toString() {
    return name + ":" + id + ": " + age + ": " + height;
  }
}

public class UnsafeTest {
  public static void main(String[] args)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException, InstantiationException {
    // 通过反射得到theUnsafe对应的Field对象
    Field field = Unsafe.class.getDeclaredField("theUnsafe");
    // 设置该Field为可访问
    field.setAccessible(true);
    // 通过Field得到该Field对应的具体对象，传入null是因为该Field为static的
    Unsafe unsafe = (Unsafe) field.get(null);

    User unsafeUser = (User) unsafe.allocateInstance(User.class);
    System.out.println(unsafeUser); //dont invoke constructor, print null: 0

    User user = new User();
    System.out.println(user); //print test: 22

    Class<? extends User> userClass = user.getClass();
    Field name = userClass.getDeclaredField("name");
    Field id = userClass.getDeclaredField("id");
    Field age = userClass.getDeclaredField("age");
    Field height = userClass.getDeclaredField("height");

    unsafe.putObject(user, unsafe.objectFieldOffset(name), "midified-name");
    unsafe.putLong(user, unsafe.objectFieldOffset(id), 100L);
    unsafe.putInt(user, unsafe.objectFieldOffset(age), 100);
    unsafe.putDouble(user, unsafe.objectFieldOffset(height), 100.1);
    System.out.println(user);
  }
}
