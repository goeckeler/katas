package filer;

import static org.apache.commons.lang3.StringUtils.defaultString;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * File copy test driver
 */
public class Application
{
  public static void main(final String[] args)
    throws IOException
  {
    Options options = Options.parse(args);
    FilerUtil fileUtils = new FilerUtil().includeFolderLevels(options.getLevels());
    fileUtils.withAlgorithm(Algorithms.lookUp(options.getAlgorithm()));

    fileUtils.copy(options.getSource(), options.getTarget());
  }
}

class Algorithms
{
  private static Map<String, Filer> algorithms = new HashMap<>();

  static {
    algorithms.put("apache", null);
    algorithms.put("channel", null);
    algorithms.put("files", null);
    algorithms.put("reader", null);
    algorithms.put("shell", null);
    algorithms.put("stream", null);
  }

  /**
   * Looks up an appropriate file copy algorithm if specified above.
   * 
   * @param name the short name as passed on the argument line
   * @return the matching algorithm or <code>null</code> if there is no match
   */
  public static Filer lookUp(final String name) {
    return algorithms.get(defaultString(name).toLowerCase());
  }
}
