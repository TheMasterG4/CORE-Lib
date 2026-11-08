package file;

import file.io.EasyFileReader;
import file.io.EasyFileWriter;
import file.pathing.FilePathBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.net.URI;

import static util.Duplicator.duplicate;

public class BinaryFile extends EasyFile<byte[]>{
    public BinaryFile(@NotNull String pathname) {
        super(pathname);
    }

    public BinaryFile(String parent, @NotNull String child) {
        super(parent, child);
    }

    public BinaryFile(File parent, @NotNull String child) {
        super(parent, child);
    }

    public BinaryFile(@NotNull URI uri) {
        super(uri);
    }
    public BinaryFile(@NotNull String... path){
        super(FilePathBuilder.bp(path));
    }
    public BinaryFile (@NotNull StringBuilder filePathBuilderStringBuilder){
        super(FilePathBuilder.bp(filePathBuilderStringBuilder.toString()));
    }
    @Override
    public byte @Nullable[] read() {
        byte[] b = EasyFileReader.readBytes(this);
        content =b;
        return duplicate(b);
    }

    @Override
    public void write(byte[] d) {
        try {
            if (!exists()) createNewFile();
            EasyFileWriter.write(d, this);
        }catch (Exception ignored){}
    }



    @Override
    public EasyFile<byte[]> encrypt(int key) {
        return null;
    }

    @Override
    public EasyFile<byte[]> decrypt(int key) {
        return null;
    }
}
