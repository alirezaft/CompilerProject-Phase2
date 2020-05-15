package com.alirezaft.Minijava;

public class LocalVarToken extends Token {
    private String Name;
    private String Type;
    private boolean isArray;
    private boolean isClass;
    private boolean isDefined;

    public LocalVarToken(String Identifier, String Type, boolean Array, boolean Class, boolean Defined){
        super("LocalVar", "var_" + Identifier);
        Name = Identifier;
        isArray = Array;
        isClass = Class;
        isDefined = Defined;
        this.Type = Type;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Value: " + Value + " | ");
        sb.append("(name: " + Name + ") ") ;
        sb.append("(type: ");
        if(isArray){
            sb.append("array of ");
        }
        if(isClass){
            sb.append("[classType = " + Type);
            sb.append(", isDefined" + isDefined + "]");
        }else{
            sb.append(Type);
        }
        sb.append(")\n");

        return sb.toString();
    }
}
