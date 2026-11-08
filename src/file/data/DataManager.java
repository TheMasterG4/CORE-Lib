package file.data;

import file.BinaryFile;
import file.DefaultFile;
import file.EasyFile;
import file.pathing.FileName;
import file.io.EasyFileReader;
import file.io.EasyFileWriter;

import static core.CORE.*;
import static file.pathing.FilePathBuilder.bp;

public final class DataManager {

    private DataManager(){}


    public static<T extends Data<String>> void saveData(FileName f, T toSave){
        new DefaultFile(bp(program.dataOutput(),program.name(),f.toString())).write(toSave.getData());
    }
    public static<T extends Data<byte[]>> void saveByteData(FileName f, T toSave){
        new BinaryFile(bp(program.dataOutput(),program.name(),f.toString())).write(toSave.getData());
    }

    public static String retrieveData(FileName f){
        return new DefaultFile(bp(program.dataOutput(),program.name(),f.toString())).read();
    }
    public static byte[] retrieveByteData(FileName f){
        return new BinaryFile(bp(program.dataOutput(),program.name(),f.toString())).read();
    }
}
