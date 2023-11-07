/*
 * ArrayReader.java
 * Copyright (C) 2023 University of Waikato, Hamilton, New Zealand
 */

package com.github.fracpete.gsonlines;

import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Array reader for jsonlines.
 *
 * @author fracpete (fracpete at waikato dot ac dot nz)
 */
public class ArrayReader
  extends AbstractReader {

  /**
   * Initializes the reader for reading from the file.
   *
   * @param f		the file to read from
   * @throws IOException        if opening the file fails
   */
  public ArrayReader(File f) throws IOException {
    super(f);
  }

  /**
   * Initializes the reader using the input stream. The caller must close the stream.
   *
   * @param is		the stream to read from
   */
  public ArrayReader(InputStream is) {
    super(is);
  }

  /**
   * Initializes the stream reader using the supplied reader.
   *
   * @param r		the reader to use
   */
  public ArrayReader(Reader r) {
    super(r);
  }

  /**
   * Reads all objects.
   *
   * @param classOfT  	the non-generic class
   * @return 		all the objects
   */
  public <T> T[] read(Class<T> classOfT) {
    T[] 	result;
    List<T>	list;
    int		i;

    list = new ArrayList<>();
    while (checkNext()) {
      list.add(m_Gson.fromJson(m_NextLine, classOfT));
      m_NextLine = null;
    }

    result = (T[]) Array.newInstance(classOfT, list.size());
    for (i = 0; i < list.size(); i++)
      result[i] = list.get(i);

    return result;
  }
}
