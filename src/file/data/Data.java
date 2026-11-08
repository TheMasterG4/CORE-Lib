package file.data;

import java.io.File;

public interface Data<T> {
    T getData();
    File outputFile();
}
