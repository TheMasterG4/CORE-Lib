package file;

public interface EncryptableFile<T> {
    EasyFile<T> encrypt(int key);
    EasyFile<T> decrypt(int key);
}
