/*
 * StreamWriter.java
 * Copyright (C) 2023 University of Waikato, Hamilton, New Zealand
 */

package com.github.fracpete.gsonlines;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * For streaming jsonlines output data.
 *
 * @author fracpete (fracpete at waikato dot ac dot nz)
 */
public class StreamWriter
  extends AbstractWriter {

  /**
   * Initializes the writer for writing to the file.
   *
   * @param f the file to write to
   * @throws IOException if opening the file fails
   */
  public StreamWriter(File f) throws IOException {
    super(f);
  }

  /**
   * Initializes the writer using the input stream. The caller must close the stream.
   *
   * @param is the stream to write to
   */
  public StreamWriter(OutputStream is) {
    super(is);
  }

  /**
   * Initializes the stream writer using the supplied writer.
   *
   * @param r the writer to use
   */
  public StreamWriter(Writer r) {
    super(r);
  }

  /**
   * Outputs the provided data.
   *
   * @param data	the data to output
   * @param <T>		the data type
   * @throws IOException	if writing fails
   */
  public <T> void write(T data) throws IOException {
    initOutput();
    m_BufferedWriter.write(m_Gson.toJson(data));
    m_BufferedWriter.write("\n");
  }
}
