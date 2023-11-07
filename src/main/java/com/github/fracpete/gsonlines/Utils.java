/*
 * Utils.java
 * Copyright (C) 2023 University of Waikato, Hamilton, New Zealand
 */

package com.github.fracpete.gsonlines;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * Class with helper methods.
 *
 * @author fracpete (fracpete at waikato dot ac dot nz)
 */
public class Utils {

  /**
   * Closes the reader.
   *
   * @param r		the reader to close
   */
  public static void closeQuietly(Reader r) {
    if (r != null) {
      try {
        r.close();
      }
      catch (Exception e) {
        // ignored
      }
    }
  }

  /**
   * Closes the input stream.
   *
   * @param is		the input stream to close
   */
  public static void closeQuietly(InputStream is) {
    if (is != null) {
      try {
	is.close();
      }
      catch (Exception e) {
	// ignored
      }
    }
  }

  /**
   * Closes the writer.
   *
   * @param w		the writer to close
   */
  public static void closeQuietly(Writer w) {
    if (w != null) {
      try {
	w.close();
      }
      catch (Exception e) {
	// ignored
      }
    }
  }

  /**
   * Closes the output stream.
   *
   * @param os		the output stream to close
   */
  public static void closeQuietly(OutputStream os) {
    if (os != null) {
      try {
	os.close();
      }
      catch (Exception e) {
	// ignored
      }
    }
  }
}
