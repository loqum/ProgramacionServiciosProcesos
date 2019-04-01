package com.rfm.utils;

import java.io.IOException;
import java.util.List;

public interface Factory extends AutoCloseable {

  public String readFile(String fileName) throws IOException;

  public List<?> readFilesList(String fileName) throws IOException;

  public void writeFile(String fileName, String content) throws IOException;
  
  

}
