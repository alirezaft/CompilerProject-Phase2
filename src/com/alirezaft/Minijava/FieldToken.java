package com.alirezaft.Minijava;

public class FieldToken extends Token {
    private String Name;
    private String AccessModifier;
    private boolean isClass;
    private String Type;
    private boolean isDefined;
    private boolean isArray;

    public FieldToken(String Identifier, String name, String accessModifier, boolean Class, String type, boolean Defined,
                        boolean Array){
        super("Field", Identifier);
        Name = name;
        if(accessModifier == "private"){
            AccessModifier = "ACCESS_MODIFIER_PRIVATE";
        }else{
            AccessModifier = "ACCESS_MODIFIER_PUBLIC";
        }
        isClass = Class;
        Type = type;
        isDefined = Defined;
        isArray = Array;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Key = " + Identifier + " | ");
        sb.append("Value = " + Value + ": ");
        sb.append("(name: " + Name + ") ");
        sb.append("(type: ");
        if(isArray){
            sb.append("array of ");
        }
        if(isClass){
            sb.append("[class type = " + Type);
            sb.append(", isDefined = " + isDefined);
            sb.append("]");
        }else{
            sb.append(Type);
        }
        sb.append(") ");
        sb.append("(accessModifier: " + AccessModifier + ")\n");

        return sb.toString();
    }
}
