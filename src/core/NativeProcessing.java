package core;

public class NativeProcessing {

    public static void loadLibraries(){
        System.setProperty("java.library.path", "native");
        
    }

    public static void loadLibrary(String library){
        System.loadLibrary(library);
    }
    public static void ll(String library){
        System.loadLibrary(library);
    }
}
