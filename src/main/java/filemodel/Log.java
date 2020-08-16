package filemodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Log {
    private final int numRows;

    private List<String> headers;
    private Map<String, String[]> data;

    public Log(int rows) {
        numRows = rows;
        headers = new ArrayList<String>();
        data = new HashMap<String, String[]>();
    }

    public void createHeader(String header) {
        headers.add(header);
        data.put(header, new String[numRows]);
    }

    public String getHeader(int col) {
        return headers.get(col);
    }

    public void putFullRow(String[] values, int row) {
        // The input 'values' must be ordered the same way as the 'headers' field
        for (int i = 0; i < values.length; i++) {
            String header = headers.get(i);
            data.get(header)[row] = values[i];
        }
    }

    public void putSingleValue(String value, String header, int row) {
        data.get(header)[row] = value;
    }

    public int getNumRows() {
        return numRows;
    }

    public String[] getColumn(String header) {
        return data.get(header);
    }

    public String getValue(String header, int row) {
        return data.get(header)[row];
    }
}
