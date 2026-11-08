package util.containers;

public class QuadObject <T1,T2,T3,T4> {
    public T1 o1;
    public T2 o2;
    public T3 o3;
    public T4 o4;
    public QuadObject(T1 o1, T2 o2, T3 o3,T4 o4){
        this.o1 = o1;
        this.o2=o2;
        this.o3=o3;
        this.o4 = o4;
    }
    @Override
    public String toString(){
        return o1.toString() + ", " + o2.toString() + ", " + o3+ ", "+o4;
    }

}