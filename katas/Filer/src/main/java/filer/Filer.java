package filer;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Just to compare different file copy strategies.
 */
public interface Filer
{
  void copy(Path source, Path target)
    throws IOException;
}
