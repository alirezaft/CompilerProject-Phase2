package com.alirezaft.Minijava;

import java.util.ArrayList;

public class MethodToken extends Token {
    private String Name;
    private String AccessModifier;
    private String ReturnType;
    private boolean isArray;
    private boolean isClass;
    private boolean isDefined;
    private ArrayList<ParameterDeclaration> ParametersList;


    public MethodToken(String Identifier, String name, String accessModifier, String returnType, ArrayList<ParameterDeclaration> parameters,
                       boolean Array, boolean Class, boolean Defined){
        super("method", Identifier);
        Name = name;
        if(accessModifier.equals("private")){
            AccessModifier = "ACCESS_MODIFIER_PRIVATE";
        }else{
            AccessModifier = "ACCESS_MODIFIER_PUBLIC";
        }
        ReturnType = returnType;
        ParametersList = parameters;
        isArray = Array;
        isClass = Class;
        isDefined = Defined;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Value = Method: ");
        sb.append("(name: " + Name + ") ");
        sb.append("(returnType: ");
        if(isArray){
            sb.append("array of");
        }
        if(isClass){
            sb.append("[classType = " + ReturnType);
            sb.append(", isDefined = " + isDefined + "]) ");
        }else{
            sb.append(ReturnType + ") ");
        }
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
}
