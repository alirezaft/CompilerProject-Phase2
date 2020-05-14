package com.alirezaft.Minijava;

public class ParameterDeclaration {
    private String Type;
    private String Name;
    private boolean isArray;
    private boolean isClass;
    private boolean isDefined;
    private int Index;

    public ParameterDeclaration(String type, String name, boolean Array, boolean Class, boolean Defined, int index){
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
        sb.append("[");
        if(isArray){
            sb.append("array of ");
        }
        if(isClass){
            sb.append("[class type = " + Type +", ");
            sb.append("isDefined = " + isDefined + "], ");
        }else{
            sb.append(Type + ", ");
        }
        sb.append("index: " + Index);
        sb.append("]");

        return sb.toString();
    }
}
