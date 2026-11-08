package file;

import file.io.EasyFileReader;
import file.io.EasyFileWriter;
import file.pathing.FilePathBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.net.URI;

public class DefaultFile extends EasyFile<String>{
    public DefaultFile(@NotNull String pathname) {
        super(pathname);
    }

    public DefaultFile(String parent, @NotNull String child) {
        super(parent, child);
    }

    public DefaultFile(File parent, @NotNull String child) {
        super(parent, child);
    }

    public DefaultFile(@NotNull URI uri) {
        super(uri);
    }
    public DefaultFile(@NotNull String... path){
        super(FilePathBuilder.bp(path));
    }
    public DefaultFile (@NotNull StringBuilder filePathBuilderStringBuilder){
        super(FilePathBuilder.bp(filePathBuilderStringBuilder.toString()));
    }
    @Override
    public @Nullable String read() {
        return EasyFileReader.read(this);
    }

    @Override
    public void write(String d) {
        EasyFileWriter.write(d,this);
    }

    @Override
    public EasyFile<String> encrypt(int key) {
        return null;
    }

    @Override
    public EasyFile<String> decrypt(int key) {
        return null;
    }
}
