package com.alirezaft.Minijava;


import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;

public class SymbolTable {
    private Hashtable<String, Hashtable<String, String>> Table;

    public SymbolTable(){
        Table = new Hashtable<>();
    }

    public void insert(String Identifier, Hashtable<String, String> attributes){
        Table.put(Identifier, attributes);
    }

    public Hashtable<String, String> lookup(String Identifier){
        return Table.get(Identifier);
    }

    public String printItems(){
        StringBuilder sb = new StringBuilder();
        Table.forEach((k, v) -> {
            sb.append("Key = " + k + " | ");
            if(k.startsWith("class_")){
                printClass(k, v, sb);
            }else if(k.startsWith("interface_")){
                printInterface(k, v, sb);
            }else if(k.startsWith("var_")){
                if(v.get("Value").equals("Field")){
                    printField(k, v, sb);
                }
            }else if(k.startsWith("method_")){
                printMethod(k, v, sb);
            }
        });
        return sb.toString();
    }

    private void printClass(String k, Hashtable<String, String> v, StringBuilder sb){
        sb.append("Value = " + v.get("Value") + ": ");
        sb.append("(name: " + v.get("name") + ") ");
        if(!v.contains("extends")){
            sb.append("(extends : Object");
        }else{
            sb.append("(extends : " + v.get("extends"));
        }
        if(v.contains("implements")){
            sb.append(" | implements: " + v.get("implements"));
        }
        sb.append(")\n");
    }
    private void printInterface(String k, Hashtable<String, String> v, StringBuilder sb){
        sb.append("Value = " + v.get("Value") + ": ");
        sb.append("(name: " + v.get("name") + ")");
        sb.append("\n");
    }

    private void printField(String k, Hashtable<String, String> v, StringBuilder sb){
        sb.append("Value = " + v.get("Value") + ": ");
        sb.append("(name : " + v.get("name") + ") ");
        sb.append("(type: ");
        String type;
        if(v.containsKey("Jtype")){
            type = v.get("Jtype");
            if(type.contains("[]")){
                sb.append("array of ");
            }
            sb.append(type.split("\\[")[0]);
            sb.append(") ");
            sb.append("(accessModifier: " + v.get("accessModifier") + ")\n");
        }else{
            type = v.get("Ctype");
            if(type.contains("[]")){
                sb.append("array of ");
            }
            sb.append("[");
            sb.append("classType = " + type.split("\\[")[0] + ", ");
            sb.append("isDefined = " + v.get("isDefined") + "]) ");
            sb.append("(accessModifier: " + v.get("accessModifier") + ")\n");
        }
    }

    private void printMethod(String k, Hashtable<String, String> v, StringBuilder sb){
        sb.append("Value = " + v.get("Value") + ": ");
        sb.append("(name : " + v.get("name") + ") ");
        sb.append("(returnType : " + v.get("returnType") + ") ");
        sb.append("(accessModifier : " + v.get("accessModifier") + ") ");
        sb.append("(parametersType : ");
        v.forEach((k2, v2) -> {
            if(k2.startsWith("parameter")){
                sb.append("[");
                String[] pparts = v2.split(",");
//                sb.append(pparts[1].contains("array of ") ? "array of " : "");
//                sb.append(pparts[1].contains("%_") ? "[classType = " + pparts[1].substring(1) + ", isDefined = " + pparts[2] + "]" :
//                        pparts[1]);
                if(pparts[1].contains("array of")){
                    sb.append("array of ");
                    if(pparts[1].contains("%_")){
                        sb.append("[classType = " + pparts[1].substring(10) + ", isDefined = " + pparts[2] + "]");
                    }else{
                        sb.append(pparts[1].substring(9));
                    }
                }else{
                    if(pparts[1].contains("%_")){
                        sb.append("[classType = " + pparts[1].substring(1) + ", isDefined = " + pparts[2] + "]");
                    }else{
                        sb.append(pparts[1]);
                    }
                }
                sb.append(" , ");
                sb.append("index = " + pparts[3] + "] ");
            }
        });
        sb.append(")" + "\n");
    }
}