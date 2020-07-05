package com.alirezaft.Minijava;

import java.util.ArrayList;


public class ScopeNodeGraph {
    private SymbolTable Table;
    private String ScopeIdentifier;
    private ArrayList<ScopeNodeGraph> Children;
    private int LineNumber;

    private static ScopeNodeGraph Root;

    public ScopeNodeGraph(String Identifier, SymbolTable table, int lineNumber){
        ScopeIdentifier = Identifier;
        Table = table;
        Children = new ArrayList<>();
        LineNumber = lineNumber;
    }

    public ArrayList<ScopeNodeGraph> getChildren(){
        return Children;
    }

    public void addChild(ScopeNodeGraph node){
        Children.add(node);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-------------   " + ScopeIdentifier + " : " + LineNumber + "   -------------\n");
        sb.append(Table.printItems());
        sb.append("-----------------------------------------\n");

        return sb.toString();
    }

    public static void setRoot(ScopeNodeGraph node){
        Root = node;
    }

    private void traverseScopeGraph(ScopeNodeGraph s){

        System.out.println(s.toString());
        if(!Children.isEmpty()){
            for(ScopeNodeGraph n : Children){
                n.traverseScopeGraph(n);
            }
        }
    }

    private String traverseScopeGraph(){
        int ChildCount = Children.size();
        int TravesedNum = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(this.toString());
        if(!Children.isEmpty()){
            for(ScopeNodeGraph n : Children){
                sb.append(n.traverseScopeGraph());
            }
        }
        return sb.toString();
    }

    public static String printSymbolTables(){
        return Root.traverseScopeGraph();
    }

    public static void setChild(ScopeNodeGraph parent, ScopeNodeGraph child){
//        parent.addChild();
    }

    public static ScopeNodeGraph getRoot(){
        return Root;
    }

    public SymbolTable getSymbolTable(){
        return Table;
    }
}
