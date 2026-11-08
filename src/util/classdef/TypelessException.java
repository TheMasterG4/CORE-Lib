package util.classdef;

public class TypelessException extends RuntimeException {
    public TypelessException() {
        super("This Class has no Type Parameters.");
    }
}
