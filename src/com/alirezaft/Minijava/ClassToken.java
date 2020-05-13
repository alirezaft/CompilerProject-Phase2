package com.alirezaft.Minijava;

public class ClassToken extends Token {
    private String Name;
    private String Parent;
    private String[] Interfaces;

    public ClassToken(String Identifier, String parent, String[] interfaces){
        super("Class", Identifier);
        Parent = parent;
        Interfaces = interfaces;
    }

@Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Key = " + Identifier + " | ");
        sb.append("Value = " + Value + ": ");
        sb.append("(name : " + Name + ") ");
        if(Parent != null) {
            sb.append("(extend : " + Parent);
        }
        if(Interfaces != null){
            if(Parent == null){
                sb.append("|");
            }else{
                sb.append("(");
            }
            sb.append("implements");
            for (String inter : Interfaces){
                sb.append(inter + ", ");
            }
            sb.setLength(sb.length() - 1);
            sb.append(")\n");
        }
        return sb.toString();
    }
}
