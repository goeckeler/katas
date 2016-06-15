package filer;

import static filer.TimeUtils.durationForHumans;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.EnumSet;

public class FilerUtil
{
  private Filer filer = null;
  private int depth = 1;

  public void copy(final String sourceDirectory, final String targetDirectory)
    throws IOException
  {
    final Path sourcePath = FileSystems.getDefault().getPath(sourceDirectory);
    final Path targetPath = FileSystems.getDefault().getPath(targetDirectory);

    final LocalDateTime startTime = LocalDateTime.now();

    Files.walkFileTree(sourcePath, EnumSet.noneOf(FileVisitOption.class), depth, new SimpleFileVisitor<Path>()
    {
      @Override
      public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs)
        throws IOException
      {
        final Path targetdir = targetPath.resolve(sourcePath.relativize(dir));

        if (Files.isDirectory(dir) && Files.notExists(targetdir)) {
          Files.createDirectory(targetdir);
        }

        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
        throws IOException
      {
        final Path target = targetPath.resolve(sourcePath.relativize(file));
        filer.copy(file, target);
        return FileVisitResult.CONTINUE;
      }
    });

    final LocalDateTime endTime = LocalDateTime.now();
    System.out.println("Copy took " + durationForHumans(Duration.between(startTime, endTime)));
  }

  public FilerUtil withAlgorithm(final Filer filer) {
    this.filer = filer;
    return this;
  }

  public FilerUtil includeFolderLevels(final Integer maximumDepth) {
    this.depth = (maximumDepth == null || maximumDepth <= 0 ? Integer.MAX_VALUE : maximumDepth);
    return this;
  }
}
