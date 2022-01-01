package Base;

public class Selector {
    public Identifier func;
    public String id;
    public static enum Identifier {
        findByText(0),
        findByRescId(1),
        findByContDesc(2);
        private int num;

        Identifier(int n) {
            num = n;
        }
    }
    public Selector(Identifier f,String i){
        func=f;
        id=i;
    }
}
