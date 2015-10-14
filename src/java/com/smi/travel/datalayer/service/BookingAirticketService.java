/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.AirticketBookingDao;
import com.smi.travel.datalayer.dao.AirticketDescDao;
import com.smi.travel.datalayer.dao.AirticketPnrDao;
import com.smi.travel.datalayer.dao.BookingFlightDao;
import com.smi.travel.datalayer.dao.BookingPassengerDao;
import com.smi.travel.datalayer.dao.BookingPnrDao;
import com.smi.travel.datalayer.dao.MAirlineDao;
import com.smi.travel.datalayer.dao.MAirportDao;
import com.smi.travel.datalayer.dao.MListItemDao;
import com.smi.travel.datalayer.entity.AirticketBooking;
import com.smi.travel.datalayer.entity.AirticketPnr;
import com.smi.travel.datalayer.entity.BookingPnr;
import com.smi.travel.datalayer.entity.MFlight;
import com.smi.travel.datalayer.entity.MFlightservice;
import com.smi.travel.datalayer.entity.MPricecategory;
import com.smi.travel.datalayer.entity.MTicketType;
import com.smi.travel.datalayer.entity.Master;
import java.util.List;
/**
 *
 * @author Surachai
 */
public class BookingAirticketService {
    private MAirlineDao mAirlineDao;
    private BookingPassengerDao bookingPassengerDao;
    private BookingFlightDao bookingFlightDao;
    private BookingPnrDao bookingPnrDao;
    private AirticketPnrDao airticketPnrDao;
    private AirticketBookingDao  airticketBooking;
    private AirticketDescDao airticketdescDao;
    private MListItemDao listItemdao;
    private MAirportDao mAirportDao;

    public BookingPnr getBookingPnr(String PNR){
        return bookingPnrDao.getBookingPnr(PNR);
    }
    
    public AirticketBooking getBookDetailAir(String refno){
        return airticketBooking.getBookDetailAir(refno);
    }
    
    public AirticketPnr getPNRDetail(String PNR,String refno) {
        return airticketBooking.getPNRDetail(PNR,refno);
    }
    
    public AirticketPnr getPNRDetailByID(String PNRID,String refno) {
        return airticketBooking.getPNRDetailByID(PNRID, refno);
    }
    
    public List<MTicketType> getListMTicketType(){
        return listItemdao.getListMTicketType();
    }
    
    public List<MPricecategory> getListMPricecategory(){
         return listItemdao.getListMPricecategory();
    }
    
    public int insertBookingPnr(BookingPnr bPnr) {
        return bookingPnrDao.insertBookingPnr(bPnr);
    }
    
    
    public int updateBookingPnr(BookingPnr bPnr) {
        return bookingPnrDao.updateBookingPnr(bPnr);
    }
    public int insertBookingAirTicket(AirticketBooking AirBooking) {
        return airticketBooking.insertBookingAirTicket(AirBooking);
    }
    
    public int updateBookingAirTicket(AirticketBooking AirBooking) {
        return airticketBooking.updateBookingAirTicket(AirBooking);
    }
    public AirticketPnr getAirticketPnrFromId(String id){
        return airticketPnrDao.getAirticketPnrFromId(id);
    }
    
    public int insertAirticketPnr(AirticketPnr airPnr ) {
        return airticketPnrDao.insertAirticketPnr(airPnr);
    }
    
    public int importUpdateAirticketPnr(AirticketPnr airPnr){
        return airticketPnrDao.importUpdateAirticketPnr(airPnr);
    }
    
    public int UpdateAirticketPnr(AirticketPnr airPnr ) {
        return airticketPnrDao.UpdateAirticketPnr(airPnr);
    }
    
    public int importExistingAirticketPnr(AirticketPnr airPnr) {
        return airticketPnrDao.importExistingAirticketPnr(airPnr);
    }
    
    public List<BookingPnr> getListBookingPnr( ) {
        return bookingPnrDao.getListBookingPnr();
    }
    
    public AirticketPnr getAirTicketPnr(String refno){
        return airticketPnrDao.getAirticketDetail(refno);
    }
    
    public MAirlineDao getmAirlineDao() {
        return mAirlineDao;
    }

    public void setmAirlineDao(MAirlineDao mAirlineDao) {
        this.mAirlineDao = mAirlineDao;
    }

    public BookingPassengerDao getBookingPassengerDao() {
        return bookingPassengerDao;
    }

    public void setBookingPassengerDao(BookingPassengerDao bookingPassengerDao) {
        this.bookingPassengerDao = bookingPassengerDao;
    }

    public BookingFlightDao getBookingFlightDao() {
        return bookingFlightDao;
    }

    public void setBookingFlightDao(BookingFlightDao bookingFlightDao) {
        this.bookingFlightDao = bookingFlightDao;
    }

    public BookingPnrDao getBookingPnrDao() {
        return bookingPnrDao;
    }

    public void setBookingPnrDao(BookingPnrDao bookingPnrDao) {
        this.bookingPnrDao = bookingPnrDao;
    }

    public AirticketPnrDao getAirticketPnrDao() {
        return airticketPnrDao;
    }

    public void setAirticketPnrDao(AirticketPnrDao airticketPnrDao) {
        this.airticketPnrDao = airticketPnrDao;
    }

    public AirticketBookingDao getAirticketBooking() {
        return airticketBooking;
    }

    public void setAirticketBooking(AirticketBookingDao airticketBooking) {
        this.airticketBooking = airticketBooking;
    }

    public AirticketDescDao getAirticketdescDao() {
        return airticketdescDao;
    }

    public void setAirticketdescDao(AirticketDescDao airticketdescDao) {
        this.airticketdescDao = airticketdescDao;
    }

    public MListItemDao getListItemdao() {
        return listItemdao;
    }

    public void setListItemdao(MListItemDao listItemdao) {
        this.listItemdao = listItemdao;
    }
    
    public int cancelBookAirticketPNR(String PNRID){
        return airticketPnrDao.cancelBookAirticketPNR(PNRID);
    }
    
    public int enableBookAirticketPNR(String PNRID){
        return airticketPnrDao.enableBookAirticketPNR(PNRID);
    }
    
    public int cancelBookAirticketFilght(String FilghtID){
        return airticketPnrDao.cancelBookAirticketFlight(FilghtID);
    }
    
    public int enableBookAirticketFilght(String FilghtID){
        return airticketPnrDao.enableBookAirticketFlight(FilghtID);
    }
    
    public int deletePassenger(String airPassenger){
        return airticketPnrDao.deletePassenger(airPassenger);
    }
    
    public int DeleteDesc(AirticketBooking booking, String descId){
        return airticketBooking.DeleteDesc(booking, descId);
    }

    public MAirportDao getmAirportDao(){
        return mAirportDao;
    }

    public void setmAirportDao(MAirportDao mAirportDao) {
        this.mAirportDao = mAirportDao;
    }
    
    public String getAirportName(String AirportCode){
        return mAirportDao.getAirportName(AirportCode);
    }
    
//    public MFlight MappingFlightClass(String Flight){
//        return airticketPnrDao.MappingFlightClass(Flight);
//    }
    public MFlight MappingFlightClass(String flightClass){
        return airticketPnrDao.MappingFlightClass(flightClass);
    }
    
    public String MappingTicketType(String TicketType) {
        return airticketPnrDao.MappingTicketType(TicketType);
    }
    
    public MTicketType MappingTicketLife(String TicketLife) {
        return airticketPnrDao.MappintTicketLife(TicketLife);
    }
    
    public List<String> getListPnrFromRefno(String Refno){
        return airticketPnrDao.getListPnrFromRefno(Refno);
    }

    public int updateBookingAirUnlock(Master master) {
        return airticketBooking.updateBookingAirUnlock(master);
    }
}
