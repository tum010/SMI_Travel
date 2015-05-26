<%-- 
    Document   : Billable
    Created on : Dec 15, 2014, 3:37:12 PM
    Author     : sumeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="billable" value="${requestScope['BillableList']}" />
${billable.billTo}
<div class="" style="padding-top: 10px">
     
    <div class="col-sm-12 alert alert-success">
        <div class="col-sm-5">
            <div class="form-group">
                <label for="passengerId" class="col-sm-2 control-label">Passenger</label>
                <div class="col-sm-2">  
                    <input type="text" class="form-control" id="passengerId" name="passengerId">                                                            
                </div>
                <div class="col-sm-2">
                    <select class="">
                        <option value="">Mr.</option>
                    </select>
                </div>
                <div class="col-sm-2">  
                    <input type="text" class="form-control" id="Others" name="Land">                                                            
                </div>
                <div class="col-sm-2">  
                    <input type="text" class="form-control" id="Others" name="Land">                                                            
                </div>
            </div>
            <div class="form-group">
                <label for="Land" class="col-sm-2 control-label">Point To</label>
                <div class="col-sm-2">  
                    <input type="text" class="form-control" id="Others" name="Land">                                                            
                </div>
                <div class="col-sm-2">
                    <select class="">
                        <option value="">Mr.</option>
                    </select>
                </div>
                <div class="col-sm-2">  
                    <input type="text" class="form-control" id="Others" name="Land" autocomplete="off">                                                            
                </div>
                <div class="col-sm-2">  
                    <input type="text" class="form-control" id="Others" name="Land">                                                            
                </div>
            </div>
            <div class="form-group">
                <label for="Land" class="col-sm-3 control-label">Current Point</label>
                <div class="col-sm-6">  
                    <input type="text" class="form-control" id="Others" name="Land">                                                            
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="Bill To" class="col-sm-3 control-label">Bill To</label>
                <div class="col-sm-3">  
                    <input type="text" class="form-control" name="" value="${billable.billTo}">                                                            
                </div>
                <div class="col-sm-6">  
                    <input type="text" class="form-control" id="Others" name="Land">                                                            
                </div>
            </div>
            <div class="form-group">
                <label for="Land" class="col-sm-3 control-label">Name</label>
                <div class="col-sm-9">  
                    <input type="text" class="form-control" id="Others" name="Land">                                                            
                </div>
            </div>
            <div class="form-group">
                <label for="Land" class="col-sm-3 control-label">Address</label>
                <div class="col-sm-9">  
                    <input type="text" class="form-control" id="Others" name="Land">                                                            
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <label for="Land" class="col-sm-6 control-label">Bill Date</label>
                <div class="col-sm-6">  
                    <input type="text" class="form-control" id="Others" name="Land">                                                            
                </div>
            </div>
            <div class="form-group">
                <label for="Land" class="col-sm-6 control-label">Term Pay</label>
                <div class="col-sm-6">
                    <select class="form-control">
                        <option value="">Credit 30 day</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="Land" class="col-sm-6 control-label">Pay By</label>
                <div class="col-sm-6">
                    <select class="form-control">
                        <option value="">Bank Tranfer</option>
                    </select>
                </div>
            </div>
        </div>
    </div>


    <div class="col-sm-12">
        <div class="col-sm-5">
            <table class="display">
                <thead class="datatable-header">
                    <tr>
                        <th></th>
                        <th colspan="2">Cost</th>
                        <th colspan="2">Price</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Air Ticket</td>
                        <td><input class="form-control" value="29,730.00"></td>
                        <td><input class="form-control" value="THB"></td>
                        <td><input class="form-control" value="30,890.00"></td>
                        <td><input class="form-control" value="THB"></td>

                    </tr>
                    <tr>
                        <td>Hotel</td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                    </tr>
                    <tr>
                        <td>Group Tour</td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                    </tr>
                    <tr>
                        <td>Day Tour</td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                    </tr>
                    <tr>
                        <td>Land</td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                    </tr>
                    <tr>
                        <td>Coupon</td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                    </tr>
                    <tr>
                        <td>Others</td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                        <td><input class="form-control"></td>
                    </tr>
                </tbody>
            </table>
            <br>
            <div class="text-center">
                <label>Total 30,890.00</label>
            </div>
        </div>
        <div class="col-sm-7">
            <table class="display">
                <thead class="datatable-header">
                    <tr>
                        <th style="width: 8%">No</th>
                        <th></th>
                        <th colspan="2">Cost</th>
                        <th colspan="2">Price</th>
                        <th>Remarks</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input class="form-control" value="1"></td>
                        <td>
                            <select class="">
                                <option value="">Air ticket</option>
                            </select>
                        </td>
                        <td><input class="form-control" value="29,730.00"></td>
                        <td> 
                            <select class="">
                                <option value="">THB</option>
                            </select>
                        </td>
                        <td><input class="form-control" value="30,890.00"></td>
                        <td>
                            <select class="">
                                <option value="">THB</option>
                            </select>
                        </td>
                        <td><input class="form-control" value=""></td>
                        <td><span data-toggle="modal" class="glyphicon glyphicon-remove-circle text-danger"></span></td>
                    </tr>
                    <tr>
                        <td><input class="form-control" value="1"></td>
                        <td>
                            <select class="">
                                <option value="">Air ticket</option>
                            </select>
                        </td>
                        <td><input class="form-control" value="29,730.00"></td>
                        <td> 
                            <select class="">
                                <option value="">THB</option>
                            </select>
                        </td>
                        <td><input class="form-control" value="30,890.00"></td>
                        <td>
                            <select class="">
                                <option value="">THB</option>
                            </select>
                        </td>
                        <td><input class="form-control" value=""></td>
                        <td><span data-toggle="modal" class="glyphicon glyphicon-remove-circle text-danger"></span></td>
                    </tr>
                    <tr>
                        <td><input class="form-control" value="1"></td>
                        <td>
                            <select class="">
                                <option value="">Air ticket</option>
                            </select>
                        </td>
                        <td><input class="form-control" value="29,730.00"></td>
                        <td> 
                            <select class="">
                                <option value="">THB</option>
                            </select>
                        </td>
                        <td><input class="form-control" value="30,890.00"></td>
                        <td>
                            <select class="">
                                <option value="">THB</option>
                            </select>
                        </td>
                        <td><input class="form-control" value=""></td>
                        <td><span data-toggle="modal" class="glyphicon glyphicon-remove-circle text-danger"></span></td>
                    </tr>
                    <tr>
                        <td><input class="form-control" value="1"></td>
                        <td>
                            <select class="">
                                <option value="">Air ticket</option>
                            </select>
                        </td>
                        <td><input class="form-control" value="29,730.00"></td>
                        <td> 
                            <select class="">
                                <option value="">THB</option>
                            </select>
                        </td>
                        <td><input class="form-control" value="30,890.00"></td>
                        <td>
                            <select class="">
                                <option value="">THB</option>
                            </select>
                        </td>
                        <td><input class="form-control" value=""></td>
                        <td><span data-toggle="modal" class="glyphicon glyphicon-remove-circle text-danger"></span></td>
                    </tr>
                    <tr>
                        <td><input class="form-control" value="1"></td>
                        <td>
                            <select class="">
                                <option value="">Air ticket</option>
                            </select>
                        </td>
                        <td><input class="form-control" value="29,730.00"></td>
                        <td> 
                            <select class="">
                                <option value="">THB</option>
                            </select>
                        </td>
                        <td><input class="form-control" value="30,890.00"></td>
                        <td>
                            <select class="">
                                <option value="">THB</option>
                            </select>
                        </td>
                        <td><input class="form-control" value=""></td>
                        <td><span data-toggle="modal" class="glyphicon glyphicon-remove-circle text-danger"></span></td>
                    </tr>
                    <tr>
                        <td><input class="form-control" value="1"></td>
                        <td>
                            <select class="">
                                <option value="">Air ticket</option>
                            </select>
                        </td>
                        <td><input class="form-control" value="29,730.00"></td>
                        <td> 
                            <select class="">
                                <option value="">THB</option>
                            </select>
                        </td>
                        <td><input class="form-control" value="30,890.00"></td>
                        <td>
                            <select class="">
                                <option value="">THB</option>
                            </select>
                        </td>
                        <td><input class="form-control" value=""></td>
                        <td><span data-toggle="modal" class="glyphicon glyphicon-remove-circle text-danger"></span></td>
                    </tr>


                </tbody>
            </table>
            <div class="form-group" style="padding-top: 10px">
                <label class="col-sm-4 control-label text-right">Total Amount </label>
                <div class="col-sm-4"> 
                    <input value="30,890.00" class="form-control">
                </div>
            </div>
            <div class="form-group" style="padding-top: 10px">
                <label class="col-sm-2 control-label text-left">Description</label>
                <div class="col-sm-10"> 
                    <textarea class="form-control"></textarea>
                </div>

            </div>
        </div>

    </div>   
</div>
