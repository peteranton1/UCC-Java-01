package org.tabular.accum.readers;

import org.tabular.accum.DataReader;
import org.tabular.accum.FieldType;
import org.tabular.accum.ReaderBuilder;
import org.tabular.accum.TabularException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlainTextBuilder implements ReaderBuilder {

    private final List<PlainTextFieldDef> fieldDefs;

    public PlainTextBuilder() {
        fieldDefs = new ArrayList<>();
    }

    @Override
    public ReaderBuilder newBuilder() {
        return new PlainTextBuilder();
    }

    @Override
    public void fixedColField(int posStart, int posFinish, String name, FieldType fieldType) {
        if (fieldType.equals(FieldType.COL_STR)) {
            fieldDefs.add(new PlainTextFieldDef(posStart, posFinish, name, fieldType));
        } else {
            String message = String.format("Unknown colFieldType: '%s', available: %s",
                fieldType, Arrays.stream(FieldType.values()).toList());
            throw new TabularException(message);
        }
    }

    @Override
    public DataReader build() {
        if (fieldDefs.isEmpty()) {
            String message = "No fields have been defined.";
            throw new TabularException(message);
        }
        ArrayList<PlainTextFieldDef> list = new ArrayList<>(fieldDefs);
        return new PlainTextReader(list);
    }
}
