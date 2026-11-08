package util.sorting.output;

public non-sealed interface LongOutput<Input> extends Output<Input>{
    long  output(Input i);
}
