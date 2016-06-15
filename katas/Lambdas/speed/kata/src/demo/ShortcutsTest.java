package demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
import org.junit.Test;

public class ShortcutsTest
{

  @Test
  public void testFindFirstSeq()
    throws Exception
  {
    assertEquals("Niklas Schuster", new Shortcuts().findFirstSeq("niklas schuster"));
    assertNull(new Shortcuts().findFirstSeq("topalo"));
  }

  @Test
  @Ignore
  public void testFindFirstPar()
    throws Exception
  {
    assertEquals("Niklas Schuster", new Shortcuts().findFirstPar("niklas schuster"));
    assertNull(new Shortcuts().findFirstPar("topalo"));
  }

  @Test
  @Ignore
  public void testFindFirstFor()
    throws Exception
  {
    assertEquals("Niklas Schuster", new Shortcuts().findFirstFor("niklas schuster"));
    assertNull(new Shortcuts().findFirstFor("topalo"));
  }

}
