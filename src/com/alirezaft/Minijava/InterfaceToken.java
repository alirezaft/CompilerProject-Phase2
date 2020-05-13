package com.alirezaft.Minijava;

public class InterfaceToken extends Token{
    String Name;

    public InterfaceToken(String Identifier, String name){
        super("Interface", Identifier);
        Name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Key = " + Identifier + " | ");
        sb.append("Value = " + Value + ": ");
        sb.append("(name: " + Name + ")\n");

        return sb.toString();
    }
}
