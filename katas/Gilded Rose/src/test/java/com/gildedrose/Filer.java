package com.gildedrose;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;

public class Filer
{
  private static final Charset UTF8 = Charset.forName("UTF-8");

  public static boolean exists(final String directory, final String file) {
    final String root = System.getProperty("user.dir");

    return Files.exists(FileSystems.getDefault().getPath(root, directory, file));
  }

  public static List<String> readFromFile(final String directory, final String file)
    throws IOException
  {
    return Files.readAllLines(getPath(directory, file), UTF8);
  }

  public static String readAsStringFromFile(final String directory, final String file)
    throws IOException
  {
    final String newLine = System.getProperty("line.separator");
    final StringBuilder string = new StringBuilder();
    for (final String text : readFromFile(directory, file)) {
      string.append(text).append(newLine);
    }

    return string.toString();
  }

  public static void writeAsString(final String directory, final String file, final String text)
    throws IOException
  {
    try (BufferedWriter writer = Files.newBufferedWriter(getPath(directory, file), UTF8)) {
      writer.write(text);
    }
  }

  public static Path getPath(final String directory, final String file) {
    final String root = System.getProperty("user.dir");
    return FileSystems.getDefault().getPath(root, directory, file);
  }
}
