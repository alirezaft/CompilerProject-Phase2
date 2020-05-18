package com.alirezaft.Minijava;

public class ParameterToken extends Token {
    private String Name;
    private int Index;
    private Type type;

    public ParameterToken(String type, String name, boolean Array, boolean Class, boolean Defined, int index){
        super("Parameter", "var_" + name);
        Name = name;
        Index = index;
    }

    public ParameterToken(String name, int index, Type type){
        super("Parameter", "var_" + name);
        this.type = type;
        Name = name;
        Index = index;
    }

    @Override
    public String toString(){
        StringBuilder sb  = new StringBuilder();

        sb.append("Value = " + Value + " | ");
        sb.append("(name: " + Name + ") ");
        sb.append("(type: ");
        sb.append(type.toString() + ") ");
        sb.append("(index: " + Index + ")\n");

        return sb.toString();
    }
}
