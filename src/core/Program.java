package core;

import file.data.Data;
import file.data.SaveSystem;

public interface Program {
    String name();
    String dataOutput();
    SaveSystem save();
    Data[] getData();
}
