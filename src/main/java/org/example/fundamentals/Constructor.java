package org.example.fundamentals;

public class Constructor {
    private string field1;
    private String field2;
    Constructor() {}
    Constructor(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }


    public static void main(String[] args) {
        Constructor defaultConstructor = new Constructor();
        Constructor allArgsConstructor = new Constructor("field1", "field2");
        System.out.println(allArgsConstructor);
    }
}
