package com.alirezaft.Minijava;

public class Type {
    protected boolean isArray;
    protected String type;

    public Type(boolean Array, String type){
        isArray = Array;
        this.type = type;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        if(isArray){
            sb.append("array of ");
        }
        sb.append(type);

        return sb.toString();
    }
}
