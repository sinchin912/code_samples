package com.hbg.conduent.log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.ws.transport.TransportOutputStream;

/**
 * The com.hbg.conduent.log.ByteArrayTransportOutputStream derived from TransportOutputStream.
 * It overrides the methods createOutputStream() & addHeader(String name, String value). 
 * 
 * @author Gurpreet.Singh
 */
public class ByteArrayTransportOutputStream extends TransportOutputStream {

  private static final String NEW_LINE = System.getProperty("line.separator");

  private ByteArrayOutputStream byteArrayOutputStream;
  
  /**
   * Adds a header with the given name and value. This method can be called multiple times, to allow for headers with
   * multiple values.
   * 
   * @param name	the name of the header
   * @param value	the value of the header
   *  
   * @see org.springframework.ws.transport.TransportOutputStream#addHeader(java.lang.String, java.lang.String)
   */
  @Override
  public void addHeader(String name, String value) throws IOException {
    createOutputStream();
    String header = name + ": " + value + NEW_LINE;
    byteArrayOutputStream.write(header.getBytes());
  }
  
  /**
   * Returns the output stream to write to.
   * 
   * @see org.springframework.ws.transport.TransportOutputStream#createOutputStream()
   */
  @Override
  protected OutputStream createOutputStream() throws IOException {
    if (byteArrayOutputStream == null) {
      byteArrayOutputStream = new ByteArrayOutputStream();
    }
    return byteArrayOutputStream;
  }
  
  /**
   * Converts the array stream to byte array.
   *
   * @return byte[] the current contents of this output stream, as a byte array.
   */
  public byte[] toByteArray() {
    return byteArrayOutputStream.toByteArray();
  }
}