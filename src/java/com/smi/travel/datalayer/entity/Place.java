package com.smi.travel.datalayer.entity;
// Generated Dec 17, 2014 3:54:36 PM by Hibernate Tools 3.6.0



/**
 * Place generated by hbm2java
 */
public class Place {


     private String id;
     private MPlacestatus MPlacestatus;
     private String place;

    public Place() {
    }

    public Place(MPlacestatus MPlacestatus, String place) {
       this.MPlacestatus = MPlacestatus;
       this.place = place;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public MPlacestatus getMPlacestatus() {
        return this.MPlacestatus;
    }
    
    public void setMPlacestatus(MPlacestatus MPlacestatus) {
        this.MPlacestatus = MPlacestatus;
    }
    public String getPlace() {
        return this.place;
    }
    
    public void setPlace(String place) {
        this.place = place;
    }




}


