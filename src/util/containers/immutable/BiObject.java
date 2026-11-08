package util.containers.immutable;

public class BiObject <T1,T2>{
    public final T1 o1;
    public final T2 o2;
    public BiObject(T1 o1, T2 o2){
        this.o1 = o1;
        this.o2=o2;
    }
    @Override
    public String toString(){
        return o1.toString() + ", " + o2.toString();
    }
}
