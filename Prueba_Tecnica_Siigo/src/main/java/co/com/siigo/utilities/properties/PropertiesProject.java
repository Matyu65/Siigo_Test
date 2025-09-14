package co.com.siigo.utilities.properties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/** The type General properties. */
public class PropertiesProject {
  private static Properties prop = null;

  private PropertiesProject() {
    throw new IllegalStateException();
  }

  public static String getUrlHttps() throws IOException {
    loadProperties();
    return prop.getProperty("Urltools");
  }

    public static void loadProperties() throws IOException {
    if (prop == null) {
      prop = new Properties();
      File objclasspathRoot = new File(System.getProperty("user.dir"));
      String strFilePath = objclasspathRoot.getAbsolutePath();
      prop.load(new FileReader(strFilePath + "/Siigo.properties"));
    }
  }
}
