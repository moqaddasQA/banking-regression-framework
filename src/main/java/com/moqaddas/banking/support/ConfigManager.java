package com.moqaddas.banking.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {
  private static final Properties PROPS = new Properties();

  static {
    try (InputStream in = ConfigManager.class.getResourceAsStream("/framework.properties")) {
      if (in == null) {
        throw new IllegalStateException("framework.properties not found on classpath");
      }
      PROPS.load(in);
    } catch (IOException e) {
      throw new IllegalStateException("Unable to load framework.properties", e);
    }
  }

  private ConfigManager() {}

  public static String get(String key, String defaultValue) {
    return System.getProperty(key, PROPS.getProperty(key, defaultValue));
  }

  public static String baseUrl() {
    return get("base.url", "https://parabank.parasoft.com/");
  }

  public static String username() {
    return get("user.name", "demo");
  }

  public static String password() {
    return get("user.password", "demo");
  }

  public static boolean headless() {
    return Boolean.parseBoolean(get("browser.headless", "false"));
  }
}
