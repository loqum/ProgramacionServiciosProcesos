package com.rfm.utils;

import java.io.IOException;

public interface Factory extends AutoCloseable {

  public String readFile(String fileName) throws IOException;

}
