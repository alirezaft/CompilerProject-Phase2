package com.alirezaft.Minijava;

public class LocalVarToken extends Token {
    private String Name;
    private Type type;

    public LocalVarToken(String Identifier, String Type, boolean Array, boolean Class, boolean Defined){
        super("LocalVar", "var_" + Identifier);
        Name = Identifier;
    }

    public LocalVarToken(String Identifier, Type type){
        super("LocalVar", "var_" + Identifier);
        Name = Identifier;
        this.type = type;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Value: " + Value + " | ");
        sb.append("(name: " + Name + ") ") ;
        sb.append("(type: ");
        sb.append(type.toString());
        sb.append(")\n");

        return sb.toString();
    }
}
