package net.velex.title;

import net.velex.title.api.ServerAdaptModel;
import net.velex.title.builder.ActionBarBuilder;
import net.velex.title.builder.TitleBuilder;
import net.velex.title.impl.SimpleAdaptManagerImpl;
import net.velex.title.impl.TablistManagerModelImpl;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface Title {
  int SERVER_VERSION = serverRelease();
  
  /**
   * Returns a new implementation for the {@link net.velex.title.api.AdaptManagerModel} type.
   *
   * @return A {@link SimpleAdaptManagerImpl} reference.
   */
  @Contract(value = " -> new", pure = true)
  static @NotNull SimpleAdaptManagerImpl newAdaptManager() {
    return new SimpleAdaptManagerImpl();
  }
  
  /**
   * Returns a new implementation for the {@link net.velex.title.api.TablistManagerModel} type.
   *
   * @return A {@link TablistManagerModelImpl} reference.
   */
  @Contract(value = "_ -> new", pure = true)
  static @NotNull TablistManagerModelImpl newTablistManager(final @NotNull ServerAdaptModel serverAdaptModel) {
    return new TablistManagerModelImpl(serverAdaptModel);
  }
  
  /**
   * Returns a new instance of {@link TitleBuilder} type.
   *
   * @return A {@link TitleBuilder} reference.
   */
  @Contract(value = "_ -> new", pure = true)
  static @NotNull TitleBuilder newTitle(final @NotNull ServerAdaptModel serverAdaptModel) {
    return new TitleBuilder(serverAdaptModel);
  }
  
  /**
   * Returns a new instance of {@link ActionBarBuilder} type.
   *
   * @return A {@link ActionBarBuilder} reference.
   */
  @Contract(value = "_ -> new", pure = true)
  static @NotNull ActionBarBuilder newActionBar(final @NotNull ServerAdaptModel serverAdaptModel) {
    return new ActionBarBuilder(serverAdaptModel);
  }
  
  /**
   * Returns the current server version.
   *
   * @return The server version on use.
   */
  static int serverRelease() {
    String version = Bukkit.getVersion();
    int index = version.lastIndexOf("MC:");
    // Checks if the 'index' value isn't -1.
    // Else, check if the 'version' ends with 'SNAPSHOT'.
    if (index != -1) {
      version = version.substring(index + 4, version.length() - 1);
    } else if (version.endsWith("SNAPSHOT")) {
      index = version.indexOf('-');
      version = version.substring(0, index);
    }
    final int lastDot = version.lastIndexOf('.');
    // Checks if the index of the 'version' value are distinct of 'lastDot' value.
    if (version.indexOf('.') != lastDot) {
      version = version.substring(0, lastDot);
    }
    return Integer.parseInt(version.substring(2));
  }
}