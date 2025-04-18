package org.tabular.accum.readers;

import org.tabular.accum.FieldDef;
import org.tabular.accum.FieldType;

public record PlainTextFieldDef(int posStart, int posFinish, String name, FieldType fieldType) implements FieldDef {
}
