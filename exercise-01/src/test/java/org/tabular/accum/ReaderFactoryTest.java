package org.tabular.accum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ReaderFactoryTest {

    private ReaderFactory underTest;

    @BeforeEach
    void setUp() {
        underTest = new ReaderFactory();
    }

    @Test
    void forNameWithPlainText() {
        String readerName = "plainText";
        ReaderBuilder builder = underTest.forName(readerName);
        builder.fixedColField(1, 2, "Num1", FieldType.COL_STR);
        builder.fixedColField(10, 13, "Num2", FieldType.COL_STR);
        DataReader reader = builder.build();
        String path = "src/test/resources/tabular/accum/testPlainText.txt";
        List<DataRecord> actual = reader.readData(path);
        int expected = 15;
        for(DataRecord rec: actual) {
            System.out.println(rec);
        }
        Assertions.assertEquals(expected, actual.size());
    }
}