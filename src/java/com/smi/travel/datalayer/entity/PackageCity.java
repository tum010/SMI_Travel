package com.smi.travel.datalayer.entity;
// Generated Jun 9, 2015 10:39:12 AM by Hibernate Tools 3.6.0



/**
 * PackageCity generated by hbm2java
 */
public class PackageCity  {


     private String id;
     private PackageTour packageTour;
     private MCity MCity;

    public PackageCity() {
    }

    public PackageCity(PackageTour packageTour, MCity MCity) {
       this.packageTour = packageTour;
       this.MCity = MCity;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public PackageTour getPackageTour() {
        return this.packageTour;
    }
    
    public void setPackageTour(PackageTour packageTour) {
        this.packageTour = packageTour;
    }
    public MCity getMCity() {
        return this.MCity;
    }
    
    public void setMCity(MCity MCity) {
        this.MCity = MCity;
    }




}


