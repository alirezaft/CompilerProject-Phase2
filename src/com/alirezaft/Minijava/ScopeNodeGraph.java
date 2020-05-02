package com.alirezaft.Minijava;

import java.util.ArrayList;
import java.util.Hashtable;

public class ScopeNodeGraph {
    private SymbolTable Table;
    private String ScopeIdentifier;
    private ArrayList<ScopeNodeGraph> Children;

    public ScopeNodeGraph(String Identifier, SymbolTable table){
        ScopeIdentifier = Identifier;
        Table = table;
        Children = new ArrayList<>();
    }

    public ArrayList<ScopeNodeGraph> getChildren(){
        return Children;
    }

    public void addChild(ScopeNodeGraph node){
        Children.add(node);
    }

}
