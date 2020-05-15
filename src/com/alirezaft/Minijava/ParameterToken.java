package com.alirezaft.Minijava;

public class ParameterToken extends Token {
    private String Type;
    private String Name;
    private boolean isArray;
    private boolean isClass;
    private boolean isDefined;
    private int Index;

    public ParameterToken(String type, String name, boolean Array, boolean Class, boolean Defined, int index){
        super("Parameter", "var_" + name);
        Type = type;
        Name = name;
        isArray = Array;
        isClass = Class;
        isDefined = Defined;
        Index = index;
    }

    @Override
    public String toString(){
        StringBuilder sb  = new StringBuilder();

        sb.append("Value = " + Value + " | ");
        sb.append("(name: " + Name + ") ");
        sb.append("(type: ");
        if(isArray){
            sb.append("array of");
        }
        if(isClass){
            sb.append("[class type = " + Type +", ");
            sb.append("isDefined = " + isDefined + "]) ");
        }else{
            sb.append(Type + ") ");
        }
        sb.append("(index: " + Index + ")\n");

        return sb.toString();
    }
}
