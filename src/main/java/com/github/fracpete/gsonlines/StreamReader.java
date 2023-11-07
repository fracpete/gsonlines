/*
 * StreamReader.java
 * Copyright (C) 2023 University of Waikato, Hamilton, New Zealand
 */

package com.github.fracpete.gsonlines;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;

/**
 * Stream reader for jsonlines.
 *
 * @author fracpete (fracpete at waikato dot ac dot nz)
 */
public class StreamReader
  extends AbstractReader {

  /**
   * Initializes the reader for reading from the file.
   *
   * @param f		the file to read from
   * @throws IOException	if opening the file fails
   */
  public StreamReader(File f) throws IOException {
    super(f);
  }

  /**
   * Initializes the reader using the input stream. The caller must close the stream.
   *
   * @param is		the stream to read from
   */
  public StreamReader(InputStream is) {
    super(is);
  }

  /**
   * Initializes the stream reader using the supplied reader.
   *
   * @param r		the reader to use
   */
  public StreamReader(Reader r) {
    super(r);
  }

  /**
   * Returns true if there are more elements to read.
   *
   * @return true if more
   */
  public boolean hasNext() {
    return checkNext();
  }

  /**
   * Returns the next element in the iteration.
   *
   * @param classOfT  the non-generic class
   * @return the next element in the iteration
   */
  public <T> T next(Class<T> classOfT) {
    T 		result;

    result = null;

    if (checkNext()) {
      result     = m_Gson.fromJson(m_NextLine, classOfT);
      m_NextLine = null;
    }

    return result;
  }
}
