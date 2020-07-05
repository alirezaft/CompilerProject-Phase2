package com.alirezaft.Minijava;

import com.sun.corba.se.impl.io.TypeMismatchException;

public class ClassType extends Type {
    boolean isDefined;

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

    @Override
    public boolean equals(Object obj) {
        boolean basiccheck = super.equals(obj);

        if(!(obj instanceof ClassType)){
            throw new TypeMismatchException();
        }

        ClassType c = (ClassType)obj;

        if(c.isDefined != this.isDefined){
            return false;
        }

        return basiccheck;
    }
}
