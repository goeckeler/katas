package filer.files;

import java.io.IOException;
import java.nio.file.*;

import filer.Filer;

public class FilesFiler
  implements Filer
{
  @Override
  public void copy(final Path source, final Path target)
    throws IOException
  {
    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
  }
}
