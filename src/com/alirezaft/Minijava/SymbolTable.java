package com.alirezaft.Minijava;


import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;

public class SymbolTable {
    private Hashtable<String, Token> Table;

    public SymbolTable(){
        Table = new Hashtable<>();
    }

    public void insert(String Identifier, Token token){
        Table.put(Identifier, token);
    }

    public Token lookup(String Identifier){
        return Table.get(Identifier);
    }

    public String printItems(){
        StringBuilder sb = new StringBuilder();

        Table.forEach((k, v) -> {
            sb.append("Key = " + k + " | ");
            sb.append(v.toString());
        });

        return sb.toString();
    }
}