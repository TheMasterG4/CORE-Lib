package util.containers.immutable;

public class TriObject<T1,T2,T3> {
    public final T1 o1;
    public final T2 o2;
    public final T3 o3;
    public TriObject(T1 o1, T2 o2, T3 o3){
        this.o1 = o1;
        this.o2=o2;
        this.o3=o3;
    }

    @Override
    public String toString(){
        return o1.toString() + ", " + o2.toString() + ", " + o3;
    }
}
