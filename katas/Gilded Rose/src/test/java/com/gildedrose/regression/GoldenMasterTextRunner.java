package com.gildedrose.regression;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.*;
import java.nio.file.Path;

import org.junit.Test;

import com.gildedrose.Filer;

public abstract class GoldenMasterTextRunner
{
  private static final String FOLDER = "src/test/resources/regression";

  @Test
  public void shouldMatchGoldenMaster()
    throws IOException
  {
    if (!Filer.exists(FOLDER, getName())) {
      final Path path = Filer.getPath(FOLDER, getName());
      System.err.println(String.format("Recording golden master '%s' in '%s'.", getName(), path));
      capture();
    }

    final String master = Filer.readAsStringFromFile(FOLDER, getName());
    assertThat(captureText(), equalTo(master));
  }

  public void capture()
    throws IOException
  {
    Filer.writeAsString(FOLDER, getName(), captureText());
  }

  protected String getName() {
    return this.getClass().getSimpleName() + ".txt";
  }

  private String captureText() {
    final PrintStream console = System.out;
    PrintStream string = null;
    String text = "(n/a)";

    try {
      final ByteArrayOutputStream output = new ByteArrayOutputStream(10240);
      string = new PrintStream(output, true);
      System.setOut(string);

      this.execute();

      text = output.toString();
    } finally {
      System.setOut(console);
    }

    return text;
  }

  protected abstract void execute();
}
