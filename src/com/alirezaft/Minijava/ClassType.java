package com.alirezaft.Minijava;

public class ClassType extends Type {
    private boolean isDefined;

    public ClassType(String Type, boolean Array, boolean Defined){
        super(Array, Type);
        isDefined = Defined;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        if(isArray){
            sb.append("array of ");
        }
        sb.append("[classType = " + type + ", ");
        sb.append("isDefined = " + isDefined + "]");

        return sb.toString();
    }

}
