package org.tabular.accum;

import java.util.List;

public interface DataReader {

    List<DataRecord> readData(String path);
}
