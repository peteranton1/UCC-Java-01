package org.tabular.accum;

public interface ReaderBuilder {

    ReaderBuilder newBuilder();

    void fixedColField(int posStart, int posFinish, String name, FieldType fieldType);

    DataReader build();
}
