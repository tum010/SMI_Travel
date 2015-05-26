/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.entity;

/**
 *
 * @author Surachai
 */
public class MGalileo {
    private String id;
    private String name;
    private String section;
    private String line;
    private int length;
    private int startlength;
    
    public MGalileo(){
        
    }

    public MGalileo(String name, String section, String line, int length, int startlength) {
        this.name = name;
        this.section = section;
        this.line = line;
        this.length = length;
        this.startlength = startlength;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getStartlength() {
        return startlength;
    }

    public void setStartlength(int startlength) {
        this.startlength = startlength;
    }
    
    
    
              
}
