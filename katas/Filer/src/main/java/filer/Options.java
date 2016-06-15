package filer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.ParserProperties;

class Options
{
  @Option(name = "-l", aliases = "--levels", metaVar = "DEPTH", usage = "copy folders till this level starting with 1")
  private Integer depth = 1;

  @Option(help = true, name = "-h", aliases = "--help", usage = "prints this usage details and stops")
  private boolean showUsage = false;

  @Option(name = "-a", aliases = "--algorithm", metaVar = "ALGORITHM",
      usage = "define which implementation to use i.e. 'channel', 'files', 'reader'")
  private String algorithm;

  // receives other command line parameters than options
  @Argument
  private List<String> arguments = new ArrayList<String>();

  public static Options parse(final String[] args)
    throws IOException
  {
    Options options = new Options();
    CmdLineParser parser = new CmdLineParser(options, ParserProperties.defaults().withUsageWidth(120));
    try {
      // parse the arguments.
      parser.parseArgument(args);

      if (options.arguments.isEmpty() || options.arguments.size() < 2 || options.showUsage) {
        usage(parser);
      }
    } catch (CmdLineException e) {
      System.err.println(e.getMessage());
      System.err.println();
      usage(parser);
    }
    
    return options;
  }

  private static void usage(final CmdLineParser parser) {
    System.err.println("Usage: java -jar <filer.jar> [-hla] <sourceDirectory> <targetDirectory>");
    System.err.println();
    parser.printUsage(System.err);
    System.err.println();

    System.exit(1);
  }
  
  public String getSource() {
    return arguments.get(0);
  }
  
  public String getTarget() {
    return arguments.get(1);
  }
  
  public Integer getLevels() {
    return depth;
  }
  
  public String getAlgorithm() {
    return algorithm;
  }
}