package util.sorting.output;

public non-sealed interface ByteOutput<Input> extends Output<Input>{
    byte  output(Input i);
}
