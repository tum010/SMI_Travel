/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.PackageItinerary;
import com.smi.travel.datalayer.entity.PackagePrice;
import com.smi.travel.datalayer.entity.PackageTour;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface PackageTourDao {
    public List<PackageTour> SearchPackage(PackageTour mpackage, int option);
    public PackagePrice getValueFromPackage(String PackageID);
    public String insertPackage(PackageTour mpackage);
    public String updatePackage(PackageTour mpackage);
    public String DeletePackage(PackageTour mpackage);
    public String DeletePackageItinerary(String ItineraryID);
    public String DeletePackagePrice(String PriceID);
    public PackageTour getPackageFromID(String packageID);
    public List<PackageItinerary> SortItineraryList(List<PackageItinerary> data);
    public List<PackagePrice> SortPriceList(List<PackagePrice> data);
}
