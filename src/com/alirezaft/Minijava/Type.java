package com.alirezaft.Minijava;

import com.sun.corba.se.impl.io.TypeMismatchException;

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

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Type)){
            throw new TypeMismatchException();
        }

        Type t = (Type)obj;

        if(t.isArray != this.isArray){
            return false;
        }

        if(!t.type.equals(this.type)){
            return false;
        }

        return true;
    }
}
