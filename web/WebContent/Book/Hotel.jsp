<%-- 
    Document   : Hotel
    Created on : Dec 15, 2014, 3:34:29 PM
    Author     : sumeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="" style="padding-top: 10px">
    <div class="alert alert-info">
        <!--Order-->
        <div class="col-sm-12">
            <div class="col-sm-9">
                <div class="col-sm-12">
                    <label class="col-sm-1 control-label">Order</label>
                    <div class="col-sm-1">  
                        <input type="text" class="form-control" id="Hotel" name="Hotel">
                    </div>
                    <label class="col-sm-1 control-label">Hotel</label>
                    <div class="col-sm-2">  
                        <input type="text" class="form-control" id="Hotel" name="Hotel">
                    </div>
                    <label class="col-sm-2 control-label">Hotel Ref</label>
                    <div class="col-sm-3">  
                        <input type="text" class="form-control" id="Hotel" name="Hotel" autocomplete="off">
                    </div>
                </div>  
                <div class="col-sm-12">
                    <label class="col-sm-1 control-label">Leader</label>
                    <div class="col-sm-3">  
                        <input type="text" class="form-control" id="Hotel" name="Hotel">
                    </div>
                    <div class="col-sm-1">  
                        <input type="checkbox">
                        <label>PAY</label>

                    </div>

                    <label class="col-sm-2 control-label">Re-confirm</label>
                    <div class="col-sm-3">  
                        <input type="text" class="form-control" id="Hotel" name="Hotel">
                    </div>
                </div>  
                <div class="col-sm-12">
                    <label class="col-sm-1 control-label">Check In/Out</label>
                    <div class="col-sm-1">  
                        <input type="text" class="form-control">
                    </div>
                    
                    <div class="col-sm-1">  
                        <input type="text" class="form-control">
                    </div>
                    <div class="col-sm-2 text-center">  
                        <a class="btn btn-primary btn-xs">New Hotel</a>
                    </div>
                    <label class="col-sm-2 control-label">Meal</label>
                    <div class="col-sm-3">  
                        <select class="form-control">
                            <option value="">Mean</option>
                        </select>
                    </div>
                </div>  

            </div>
            <div class="col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <label class="">Internal Deadine</label>
                        <input class="form-control">
                        <label class="">Clent's Deadline</label>
                        <input class="form-control">
                    </div>
                </div>

            </div>

        </div>
        <!--Formula-->
        <div class="col-sm-12">
            <div class="col-sm-7">
                <div class="col-sm-12">
                    <div class="col-sm-1">
                        <a class="btn btn-default btn-xs">Formula</a>
                    </div>
                    <div class="col-sm-10">  
                        <table class="display">
                            <thead>
                                <tr>
                                    <th>Qty</th>
                                    <th>Room</th>
                                    <th>Category</th>
                                    <th>Cost</th>
                                    <th></th>
                                    <th>Price</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>

                                <tr>
                                    <td><input type="text" class="form-control" value="Qty"></td>
                                    <td><input type="text" class="form-control" value="Room"></td>
                                    <td><input type="text" class="form-control" value="Category"></td>
                                    <td><input type="text" class="form-control" value="Cost"></td>
                                    <td>
                                        <select>
                                            <option value="">THB</option>
                                        </select>
                                    </td>
                                    <td><input type="text" class="form-control" value="Price"></td>
                                    <td>
                                        <select>
                                            <option value="">THB</option>
                                        </select>
                                    </td>
                                    <td><a><span class="glyphicon glyphicon-remove-circle text-danger"></span></a></td>
                                </tr><tr>
                                    <td><input type="text" class="form-control" value="Qty"></td>
                                    <td><input type="text" class="form-control" value="Room"></td>
                                    <td><input type="text" class="form-control" value="Category"></td>
                                    <td><input type="text" class="form-control" value="Cost"></td>
                                    <td>
                                        <select>
                                            <option value="">THB</option>
                                        </select>
                                    </td>
                                    <td><input type="text" class="form-control" value="Price"></td>
                                    <td>
                                        <select>
                                            <option value="">THB</option>
                                        </select>
                                    </td>
                                    <td><a><span class="glyphicon glyphicon-remove-circle text-danger"></span></a></td>
                                </tr>

                            </tbody>

                        </table>
                    </div>
                </div>  
            </div>
            <div class="col-sm-2" style="">
                <div class="col-sm-12">
                    <label for="Hotel" class="col-sm-3">Adult</label>
                    <div class="col-sm-6">  
                        <input type="text" class="form-control" id="Hotel" name="Hotel">
                    </div>
                </div>   
                <div class="col-sm-12">
                    <label for="Hotel" class="col-sm-3 control-label">Child</label>
                    <div class="col-sm-6">  
                        <input type="text" class="form-control" id="Hotel" name="Hotel">
                    </div>
                </div>   
                <div class="col-sm-12">
                    <label for="Hotel" class="col-sm-3">Infant</label>
                    <div class="col-sm-6">  
                        <input type="text" class="form-control" id="Hotel" name="Hotel">
                    </div>
                </div>   
            </div>
            <div class="col-sm-2">
                <div class="col-sm-12">
                    <label class="col-sm-5">Status</label>
                    <div class="col-sm-7">  
                        <select>
                            <option value="">WAIT</option>
                        </select>
                    </div>
                </div>   
                <div class="col-sm-12">
                    <label for="Hotel" class="col-sm-5 control-label">Chang To</label>
                    <div class="col-sm-7">  
                        <select>
                            <option value="">THB</option>
                        </select>
                    </div>
                </div>   
            </div>
        </div>
        <!--Request-->
        <div class="col-sm-12">
            <div class="col-sm-8">
                <div class="form-group">
                    <label for="Hotel" class="col-sm-1 control-label">Request</label>
                    <div class="col-sm-9">  
                        <table class="display">
                            <thead>
                                <tr>
                                    <th>Category</th>
                                    <th>Description</th>
                                    <th>Cost</th>
                                    <th></th>
                                    <th>Price</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><input type="text" class="form-control" value="Category"></td>
                                    <td><input type="text" class="form-control" value="Description"></td>
                                    <td><input type="text" class="form-control" value="Cost"></td>
                                    <td>
                                        <select>
                                            <option value="">THB</option>
                                        </select>
                                    </td>
                                    <td><input type="text" class="form-control" value="Price"></td>
                                    <td>
                                        <select>
                                            <option value="">THB</option>
                                        </select>
                                    </td>
                                    <td><a><span class="glyphicon glyphicon-remove-circle text-danger"></span></a></td>
                                </tr>
                                <tr>
                                    <td><input type="text" class="form-control" value="Category"></td>
                                    <td><input type="text" class="form-control" value="Description"></td>
                                    <td><input type="text" class="form-control" value="Cost"></td>
                                    <td>
                                        <select>
                                            <option value="">WAIT</option>
                                        </select>
                                    </td>
                                    <td><input type="text" class="form-control" value="Price"></td>
                                    <td>
                                        <select>
                                            <option value="">Agent</option>
                                        </select>
                                    </td>
                                    <td><a><span class="glyphicon glyphicon-remove-circle text-danger"></span></a></td>
                                </tr>
                            </tbody>

                        </table>
                    </div>
                </div>  
            </div>
            <div class="col-sm-4" style="margin: -1 -60px 0 -50px">
                <div class="form-group">
                    <label for="Hotel" class="col-sm-2 control-label">Remarks</label>
                    <div class="col-sm-8">  
                        <textarea class="form-control"></textarea>
                    </div>
                </div>   
            </div>
        </div>
        
        <!--Flight-->
        <div class="row">
            <div class="col-sm-12">
 <div class="col-sm-2">
                <label class="col-sm-3">Flight</label>
           
 

<div class="col-sm-8">
          
                <input type="text" class="form-control" autocomplete="off">
  </div> </div>
  
  
  
    <div class="col-sm-2">
                <label class="col-sm-6">Agent Com</label>
           
 

<div class="col-sm-6">
          
                <input type="text" class="form-control" autocomplete="off">
  </div> </div><div class="col-sm-1">
                
           
 

<div class="col-sm-12">
          
                <input type="text" class="form-control" autocomplete="off">
  </div> </div><div class="col-sm-2">
                <label class="col-sm-6">ComService</label>
           
 

<div class="col-sm-6">
          
                <input type="text" class="form-control" autocomplete="off">
  </div> </div><div class="col-sm-2">
                <label class="col-sm-4">Guide</label>
           
 

<div class="col-sm-8">
          
                <input type="text" class="form-control" autocomplete="off">
  </div> </div><div class="col-sm-2">
                <label class="col-sm-4">Com</label>
           
 

<div class="col-sm-8">
          
                <input type="text" class="form-control" autocomplete="off">
  </div> </div><div class="col-sm-1">
                <a class="btn btn-primary  btn-xs">New</a>
                <a class="btn btn-success btn-xs">OK</a>
      </div></div>
        </div>
    </div>
    <!-- Hotel Table-->
    <div class="col-sm-12">
        <table class="display">
            <thead class="datatable-header">
                <tr>
                    <th>No</th>
                    <th>Hotel</th>
                    <th></th>
                    <th>Req</th>
                    <th>Check In</th>
                    <th>Check Out</th>
                    <th>Remarks</th>
                    <th>Price Request</th>
                    <th>Price Request</th>
                    <th>Total Price</th>
                    <th>Cur</th>
                    <th>Status</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><input type="text" class="form-control" value="1"></td>
                    <td><input type="text" class="form-control" value="OBKK-NO"></td>
                    <td><input type="text" class="form-control" value="NOVOTEL SUVANABHUMI AIR"></td>
                    <td><input type="checkbox"></td>
                    <td><input type="text" class="form-control" value="18FEB2015"></td>
                    <td><input type="text" class="form-control" value="19FEB2015"></td>
                    <td><input type="text" class="form-control" value=""></td>
                    <td><input type="text" class="form-control" value=""></td>
                    <td><input type="text" class="form-control" value="4,850.00"></td>
                    <td><input type="text" class="form-control" value="4,850.00"></td>
                    <td><input type="text" class="form-control" value="THB"></td>
                    <td>
                        <select>
                            <option value="">OK</option>
                        </select>
                    </td>
                    <td><a><span class="glyphicon glyphicon-remove-circle text-danger"></span></a></td>
                </tr>
                <tr>
                    <td><input type="text" class="form-control" value="1"></td>
                    <td><input type="text" class="form-control" value="OBKK-NO"></td>
                    <td><input type="text" class="form-control" value="NOVOTEL SUVANABHUMI AIR"></td>
                    <td><input type="checkbox"></td>
                    <td><input type="text" class="form-control" value="18FEB2015"></td>
                    <td><input type="text" class="form-control" value="19FEB2015"></td>
                    <td><input type="text" class="form-control" value=""></td>
                    <td><input type="text" class="form-control" value=""></td>
                    <td><input type="text" class="form-control" value="4,850.00"></td>
                    <td><input type="text" class="form-control" value="4,850.00"></td>
                    <td><input type="text" class="form-control" value="THB"></td>
                    <td>
                        <select>
                            <option value="">OK</option>
                        </select>
                    </td>
                    <td><a><span class="glyphicon glyphicon-remove-circle text-danger"></span></a></td>
                </tr>
                <tr>
                    <td><input type="text" class="form-control" value="1"></td>
                    <td><input type="text" class="form-control" value="OBKK-NO"></td>
                    <td><input type="text" class="form-control" value="NOVOTEL SUVANABHUMI AIR"></td>
                    <td><input type="checkbox"></td>
                    <td><input type="text" class="form-control" value="18FEB2015"></td>
                    <td><input type="text" class="form-control" value="19FEB2015"></td>
                    <td><input type="text" class="form-control" value=""></td>
                    <td><input type="text" class="form-control" value=""></td>
                    <td><input type="text" class="form-control" value="4,850.00"></td>
                    <td><input type="text" class="form-control" value="4,850.00"></td>
                    <td><input type="text" class="form-control" value="THB"></td>
                    <td>
                        <select>
                            <option value="">OK</option>
                        </select>
                    </td>
                    <td><a><span class="glyphicon glyphicon-remove-circle text-danger"></span></a></td>
                </tr>
            </tbody>
        </table>
    </div>

</div>


<style>   
    #flight>input[type="text"] {
        width: 100px;
    }
</style>