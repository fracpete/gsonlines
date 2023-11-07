/*
 * AbstractReader.java
 * Copyright (C) 2023 University of Waikato, Hamilton, New Zealand
 */

package com.github.fracpete.gsonlines;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Ancestor for readers.
 *
 * @author fracpete (fracpete at waikato dot ac dot nz)
 */
public abstract class AbstractReader
  implements Closeable {

  /** for reading the actual json. */
  protected Gson m_Gson;

  /** the internal reader. */
  protected BufferedReader m_BufferedReader;

  /** the file reader to close. */
  protected FileReader m_FileReader;

  /** the input stream reader to close. */
  protected InputStreamReader m_InputStreamReader;

  /** the next line to parse. */
  protected String m_NextLine;

  /**
   * Initializes the reader for reading from the file.
   *
   * @param f		the file to read from
   * @throws IOException        if opening the file fails
   */
  protected AbstractReader(File f) throws IOException {
    m_FileReader = new FileReader(f);
    init(m_FileReader);
  }

  /**
   * Initializes the reader using the input stream. The caller must close the stream.
   *
   * @param is		the stream to read from
   */
  protected AbstractReader(InputStream is) {
    m_InputStreamReader = new InputStreamReader(is);
    init(m_InputStreamReader);
  }

  /**
   * Initializes the stream reader using the supplied reader.
   *
   * @param r		the reader to use
   */
  protected AbstractReader(Reader r) {
    init(r);
  }

  /**
   * Initializes the reader.
   *
   * @param r		the reader to get the data from
   */
  protected void init(Reader r) {
    if (r instanceof BufferedReader)
      m_BufferedReader = (BufferedReader) r;
    else
      m_BufferedReader = new BufferedReader(r);

    m_NextLine = null;
    m_Gson     = new Gson();
  }

  /**
   * Returns true if there are more elements to read.
   *
   * @return true if more
   */
  protected boolean checkNext() {
    if (m_BufferedReader == null)
      return false;

    if (m_NextLine != null)
      return true;

    try {
      m_NextLine = m_BufferedReader.readLine();
    }
    catch (Exception e) {
      e.printStackTrace();
      // ignored
    }
    return (m_NextLine != null);
  }

  /**
   * Closes the reader.
   */
  public void close() {
    if (m_BufferedReader != null) {
      Utils.closeQuietly(m_BufferedReader);
      m_BufferedReader = null;
    }
    if (m_FileReader != null) {
      Utils.closeQuietly(m_FileReader);
      m_FileReader = null;
    }
    if (m_InputStreamReader != null) {
      Utils.closeQuietly(m_InputStreamReader);
      m_InputStreamReader = null;
    }
  }
}
