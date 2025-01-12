package com.utils.interfaces;

import java.io.IOException;
import java.util.Map;

public interface IFileWriter {
    public void writeJsonFile(String outputFilePath, Map<String, Object> outputData) throws IOException;
}
