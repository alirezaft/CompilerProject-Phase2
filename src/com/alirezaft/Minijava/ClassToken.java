package com.alirezaft.Minijava;

public class ClassToken extends Token {
    private String Name;
    private String Parent;
    private String[] Interfaces;

    public ClassToken(String Identifier, String parent, String[] interfaces){
        super("Class", Identifier);
        Parent = parent;
        Interfaces = interfaces;
        Name = Identifier;
    }

@Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

//        sb.append("Key = " + Identifier + " | ");
        sb.append("Value = " + Value + ": ");
        sb.append("(name : " + Name + ") ");
        if(Parent != null) {
            sb.append("(extends : " + Parent);
        }
        if(Interfaces != null){
            if(Parent == null){
                sb.append(" | ");
            }else{
                sb.append(" | ");
            }
            sb.append("implements : ");
            for (int i = 0; i < Interfaces.length; i++){
                sb.append(Interfaces[i]);
                if(i !=  Interfaces.length - 1){
                    sb.append(", ");
                }
            }
            sb.append(")\n");
        }else if(Parent != null){
            sb.append(")\n");
        }else{
            sb.append("\n");
        }
        return sb.toString();
    }
}
