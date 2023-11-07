/*
 * AbstractWriter.java
 * Copyright (C) 2023 University of Waikato, Hamilton, New Zealand
 */

package com.github.fracpete.gsonlines;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.zip.GZIPOutputStream;

/**
 * Ancestor for writers.
 *
 * @author fracpete (fracpete at waikato dot ac dot nz)
 */
public abstract class AbstractWriter
  implements Closeable {

  /** for writing the actual json. */
  protected Gson m_Gson;

  /** the internal writer. */
  protected BufferedWriter m_BufferedWriter;

  /** the file writer to close. */
  protected FileWriter m_FileWriter;

  /** the input stream writer to close. */
  protected OutputStreamWriter m_OutputStreamWriter;

  /** the input stream to close. */
  protected OutputStream m_OutputStream;

  /** the gzip input stream to close. */
  protected GZIPOutputStream m_GzipOutputStream;

  /**
   * Initializes the writer for writing to the file.
   *
   * @param f		the file to write to
   * @throws IOException        if opening the file fails
   */
  protected AbstractWriter(File f) throws IOException {
    if (f.getName().toLowerCase().endsWith(".gz")) {
      m_OutputStream       = new FileOutputStream(f);
      m_GzipOutputStream   = new GZIPOutputStream(m_OutputStream);
      m_OutputStreamWriter = new OutputStreamWriter(m_GzipOutputStream);
      init(m_OutputStreamWriter);
    }
    else {
      m_FileWriter = new FileWriter(f);
      init(m_FileWriter);
    }
  }

  /**
   * Initializes the writer using the input stream. The caller must close the stream.
   *
   * @param is		the stream to write to
   */
  protected AbstractWriter(OutputStream is) {
    m_OutputStreamWriter = new OutputStreamWriter(is);
    init(m_OutputStreamWriter);
  }

  /**
   * Initializes the stream writer using the supplied writer.
   *
   * @param r		the writer to use
   */
  protected AbstractWriter(Writer r) {
    init(r);
  }

  /**
   * Initializes the writer.
   *
   * @param r		the writer to get the data from
   */
  protected void init(Writer r) {
    if (r instanceof BufferedWriter)
      m_BufferedWriter = (BufferedWriter) r;
    else
      m_BufferedWriter = new BufferedWriter(r);

    m_Gson = null;
  }

  /**
   * Returns the builder to use.
   *
   * @return		the builder
   */
  protected GsonBuilder configureBuilder() {
    return new GsonBuilder();
  }

  /**
   * Initializes the output generation if necessary.
   */
  protected void initOutput() {
    GsonBuilder		builder;

    if (m_Gson == null) {
      builder = configureBuilder();
      m_Gson = builder.create();
    }
  }

  /**
   * Closes the writer.
   */
  public void close() {
    if (m_BufferedWriter != null) {
      Utils.closeQuietly(m_BufferedWriter);
      m_BufferedWriter = null;
    }
    if (m_OutputStreamWriter != null) {
      Utils.closeQuietly(m_OutputStreamWriter);
      m_OutputStreamWriter = null;
    }
    if (m_GzipOutputStream != null) {
      Utils.closeQuietly(m_GzipOutputStream);
      m_GzipOutputStream = null;
    }
    if (m_OutputStream != null) {
      Utils.closeQuietly(m_OutputStream);
      m_OutputStream = null;
    }
    if (m_FileWriter != null) {
      Utils.closeQuietly(m_FileWriter);
      m_FileWriter = null;
    }
  }
}
