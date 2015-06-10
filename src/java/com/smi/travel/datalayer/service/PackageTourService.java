/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.PackageTourDao;
import com.smi.travel.datalayer.entity.PackageTour;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class PackageTourService {
    private PackageTourDao packagedao;
    
    public List<PackageTour> SearchPackage(PackageTour mpackage, int option){
        return packagedao.SearchPackage(mpackage, option);
    }
    
    public String validatePackage(PackageTour VpackageTour,String operation){
        String validate = "";
        PackageTour packagetour = new PackageTour();
        packagetour.setCode(VpackageTour.getCode());
        List<PackageTour> list = packagedao.SearchPackage(packagetour,1);
        if(list != null){
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(VpackageTour.getId()))){
                    validate = "package code already exist";
                }
            }else{
                 validate = "package code already exist";
            }
        }
        /*
        packagetour.setName(VpackageTour.getName());
        packagetour.setCode(null);
        list = packagedao.SearchPackage(packagetour,1);
        if(list != null){      
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(VpackageTour.getId()))){
                    validate = "package name already exist";
                }
            }else{
                 validate = "package name already exist";
            }
        } */
        return validate;
    }
    
    public String SavePackage(PackageTour mpackage){
        String result ="";
        if(mpackage.getId() == null){
            result = packagedao.insertPackage(mpackage);
        }else{
            result = packagedao.updatePackage(mpackage);
        }
        return result;
    }
    
    public String DeletePackage(PackageTour mpackage){
        return packagedao.DeletePackage(mpackage);
    }
    
    public String DeletePackageItinerary(String ItineraryID) {
        return packagedao.DeletePackageItinerary(ItineraryID);
    }
    
    public String DeletePackagePrice(String PriceID) {
        return packagedao.DeletePackagePrice(PriceID);
    }
    
    public String DeletePackageCity(String CityID){
        return packagedao.DeletePackageCity(CityID);
    }
    
    public PackageTour getPackageFromID(String packageID){
        return packagedao.getPackageFromID(packageID);
    }


    public PackageTourDao getPackagedao() {
        return packagedao;
    }

    public void setPackagedao(PackageTourDao packagedao) {
        this.packagedao = packagedao;
    }
    
    
}
