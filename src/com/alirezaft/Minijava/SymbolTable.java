package com.alirezaft.Minijava;


import java.util.Hashtable;

public class SymbolTable {
    private Hashtable<String, Hashtable<String, String>> Table;
    private int StartLineNumber;

    public SymbolTable(int lineNumber){
        Table = new Hashtable<>();
        StartLineNumber = lineNumber;
    }

    public void insert(String Identifier, Hashtable<String, String> attributes){
        Table.put(Identifier, attributes);
    }

    public Hashtable<String, String> lookup(String Identifier){
        return Table.get(Identifier);
    }

    public int getStartLineNumber(){
        return StartLineNumber;
    }

}