package com.alirezaft.Minijava;

import java.util.ArrayList;

public class MethodToken extends Token {
    private String Name;
    private String AccessModifier;
    private ArrayList<ParameterDeclaration> ParametersList;
    private Type returnType;


    public MethodToken(String Identifier, String name, String accessModifier, String returnType, ArrayList<ParameterDeclaration> parameters,
                       boolean Array, boolean Class, boolean Defined){
        super("method", Identifier);
        Name = name;
        if(accessModifier.equals("private")){
            AccessModifier = "ACCESS_MODIFIER_PRIVATE";
        }else{
            AccessModifier = "ACCESS_MODIFIER_PUBLIC";
        }
        ParametersList = parameters;
    }

    public MethodToken(String Identifier, String name, String accessModifier, Type returnType, ArrayList<ParameterDeclaration> params){
        super("method", Identifier);
        this.returnType = returnType;
        Name = name;
        if(accessModifier.equals("private")){
            AccessModifier = "ACCESS_MODIFIER_PRIVATE";
        }else{
            AccessModifier = "ACCESS_MODIFIER_PUBLIC";
        }
        ParametersList = params;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Value = Method: ");
        sb.append("(name: " + Name + ") ");
        sb.append("(returnType: ");
        if(returnType == null){
            System.out.println("AAAAAHHH! " + Name);
        }
        sb.append(returnType.toString() + ") ");
        sb.append("(accessModifier: " + AccessModifier + ") ");
        if(ParametersList != null){
            sb.append("(parametersType: ");
            for(ParameterDeclaration p : ParametersList){
                sb.append(p.toString());
            }
            sb.append(")");
        }
        sb.append("\n");

        return sb.toString();
    }

    public Type getType(){
        return returnType;
    }

    public ArrayList<ParameterDeclaration> getParametersList(){
        return ParametersList;
    }
}
