package org.tabular.accum.readers;

import org.tabular.accum.DataItem;
import org.tabular.accum.DataReader;
import org.tabular.accum.DataRecord;
import org.tabular.accum.TabularException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PlainTextReader implements DataReader {
    private final List<PlainTextFieldDef> fieldDefs;

    public PlainTextReader(List<PlainTextFieldDef> fieldDefs) {
        this.fieldDefs = fieldDefs;
    }

    @Override
    public List<DataRecord> readData(String path) {
        // read file into lines
        List<String> lines = readAllLines(path);
        // parse the lines into records
        // each record is list of items
        // return records
        return parseLines(lines);
    }

    private List<DataRecord> parseLines(List<String> lines) {
        List<DataRecord> records = new ArrayList<>();
        for (var line : lines) {
            records.add(parseLine(line));
        }
        return records;
    }

    private DataRecord parseLine(String line) {
        List<DataItem> items = new ArrayList<>();
        for (var fieldDef : fieldDefs) {
            items.add(parseField(line, fieldDef));
        }
        return new DataRecord(items);
    }

    private DataItem parseField(String line, PlainTextFieldDef fieldDef) {
        String value = "";
        if (line != null && line.length() - 1 >= fieldDef.posStart()) {
            if (line.length() < fieldDef.posFinish()) {
                value = line.substring(fieldDef.posStart());
            } else {
                value = line.substring(fieldDef.posStart(), fieldDef.posFinish());
            }
        }
        return new DataItem(value, fieldDef.name());
    }

    private List<String> readAllLines(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            throw new TabularException(e);
        }
    }
}
