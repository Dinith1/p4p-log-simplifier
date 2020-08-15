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
        data.put(header, new String[numRows]);
    }

    public String getHeader(int col) {
        return headers.get(col);
    }
}
