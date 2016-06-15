package filer;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import filer.files.FilesFiler;

public class FilerTest
{
  private final Filer filer = new FilesFiler();

  @Rule
  public TemporaryFolder targetFolder = new TemporaryFolder();

  @Test
  public void shouldCopyFile()
    throws IOException
  {
    final File sourceFile = new File(getClass().getResource("/dummy.md").getFile());
    assertTrue("Source file does not exist before copy", sourceFile.exists());

    final File targetFile = new File(targetFolder.getRoot().getAbsolutePath(), sourceFile.getName());

    filer.copy(sourceFile.toPath(), targetFile.toPath());

    assertTrue("Source file does not exist after copy", sourceFile.exists());
    assertTrue("Target file does not exist after copy", targetFile.exists());
  }
}
