package com.alirezaft.Minijava;

public class ParameterDeclaration {
    private String Name;
    private int Index;
    private Type type;

    public ParameterDeclaration(String type, String name, boolean Array, boolean Class, boolean Defined, int index){
        Name = name;
        Index = index;
    }

    public ParameterDeclaration(String name, int index, Type type){
        this.type = type;
        Name = name;
        Index = index;
    }

    @Override
    public String toString(){
        StringBuilder sb  = new StringBuilder();
        sb.append("[");
        sb.append(type.toString() + ", ");
        sb.append("index: " + Index);
        sb.append("]");

        return sb.toString();
    }
}
