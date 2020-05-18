package com.alirezaft.Minijava;

public class FieldToken extends Token {
    private String Name;
    private String AccessModifier;
    private Type type;

    public FieldToken(String Identifier, String name, String accessModifier, boolean Class, String type, boolean Defined,
                        boolean Array){
        super("Field", Identifier);
        Name = name;
        if(accessModifier == "private"){
            AccessModifier = "ACCESS_MODIFIER_PRIVATE";
        }else{
            AccessModifier = "ACCESS_MODIFIER_PUBLIC";
        }
    }

    public FieldToken(String Identifier, String name, String accessModifier, Type type){
        super("Field", Identifier);
        Name = name;
        if(accessModifier == "private"){
            AccessModifier = "ACCESS_MODIFIER_PRIVATE";
        }else{
            AccessModifier = "ACCESS_MODIFIER_PUBLIC";
        }
        this.type = type;

    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Value = " + Value + ": ");
        sb.append("(name: " + Name + ") ");
        sb.append("(type: ");
        sb.append(type.toString());
        sb.append(") ");
        sb.append("(accessModifier: " + AccessModifier + ")\n");

        return sb.toString();
    }
}
