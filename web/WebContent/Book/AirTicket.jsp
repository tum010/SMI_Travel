<%-- 
    Document   : AirTicket
    Created on : Dec 15, 2014, 3:00:26 PM
    Author     : sumeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="" style="padding-top: 10px">
    <!-- Air Table --> 
    <div class="col-sm-12">
        <table class="display table-striped table-responsive">
            <thead>
                <tr class="datatable-header">
                    <th>No</th>
                    <th>PNR</th>
                    <th>Flight</th>
                    <th colspan="2">From</th>
                    <th colspan="2">To</th>
                    <th colspan="3">Departure Date</th>
                    <th colspan="2">Price</th>
                    <th colspan="2">Tax</th>
                    <th>Status</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>6K4RMZ</td>
                    <td>TG0644</td>
                    <td>BKK</td>
                    <td>Bangkok</td>
                    <td>NGO</td>
                    <td>Nagoya</td>
                    <td>03DEC2014</td>
                    <td>0005</td>
                    <td>0730</td>
                    <td>23,195.00</td>
                    <td>THB</td>
                    <td>7,650.00</td>
                    <td>THB</td>
                    <td>OK</td>
                    <td>
                        <span data-toggle="modal" class="glyphicon glyphicon-remove-circle text-danger"></span>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>6K4RMZ</td>
                    <td>TG0645</td>
                    <td>NGO</td>
                    <td>Nagoya</td>
                    <td>BKK</td>
                    <td>Bangkok</td>
                    <td>07DEC2014</td>
                    <td>1100</td>
                    <td>1540</td>
                    <td>0.00</td>
                    <td>THB</td>
                    <td>0.00</td>
                    <td>THB</td>
                    <td>OK</td>
                    <td>
                        <span data-toggle="modal" class="glyphicon glyphicon-remove-circle text-danger"></span>
                    </td>
                </tr>

            </tbody>
        </table>  
    </div>
    <!--form-->
    <form id="containerForm" novalidate="novalidate" class="bv-form" _lpchecked="1"><button type="submit" class="bv-hidden-submit" style="display: none; width: 0px; height: 0px;"></button><button type="submit" class="bv-hidden-submit" style="display: none; width: 0px; height: 0px;"></button>

        <!--PNR--> 
        <div class="col-sm-12 alert alert-success">
            <div class="col-sm-12">
                <div class="col-sm-2">
                    <label class="col-sm-2 control-label">PNR</label>
                    <div class="col-sm-7">  
                        <input type="text" class="form-control" id="" name="name">
                    </div>
                </div>
                <div class="col-md-3">
                    <label for="Adult" class="col-sm-2 control-label">Airlien</label>
                    <div class="col-sm-2">  
                        <input type="text" class="form-control" id="" name="">
                    </div>
                    <div class="col-sm-8">  

                        <input type="text" class="form-control" id="" name="">
                    </div>
                </div>


                <label for="Adult" class="col-sm-2"><input type="checkbox" name="">Group Pax</label>
                <div class="col-sm-5">
                    <label for="Adult" class="col-sm-4 control-label">Internal Deadline</label>
                    <div class="col-sm-8">  

                        <input type="text" class="form-control" id="" name=""></div>
                </div>
            </div>  
            <div class="col-sm-12">
                <div class="col-sm-2">  
                </div>
                <div class="col-sm-5">
                    <label for="Adult" class="col-sm-2 control-label">Owner</label>
                    <div class="col-sm-3">  
                        <input type="text" class="form-control" id="" name="">
                    </div>


                    <div class="col-sm-6">  
                        <input type="text" class="form-control" id="" name="">
                    </div>
                </div>


                <div class="col-sm-5">
                    <label for="Adult" class="col-sm-4 control-label">Client's Deadline</label>
                    <div class="col-sm-8">  
                        <input type="text" class="form-control" id="" name="">
                    </div></div>

            </div>  
            <div class="col-sm-12">
                <div class="col-sm-2">  

                </div>
                <div class="col-sm-10">
                    <label class="col-sm-2 control-label">Re-Confirm by</label>
                    <div class="col-sm-4">  
                        <input type="text" class="form-control" id="" name="">
                    </div><label for="Adult" class="col-sm-2 control-label">Ticket Date</label><div class="col-sm-4">  
                        <input type="text" class="form-control" id="" name="">
                    </div></div>





            </div>  
        </div>
        <!--Departure --> 
        <div class="col-sm-12 alert alert-info">
            <div class="">
                <div class="col-sm-12">
                    <label class="col-sm-1 control-label">Order</label>
                    <div class="col-sm-1">  
                        <input type="number" min="1" class="form-control" name="order" data-bv-field="order"><i class="form-control-feedback bv-no-label" data-bv-icon-for="order" style="display: none;"></i>
                        <small class="help-block" data-bv-validator="greaterThan" data-bv-for="order" data-bv-result="NOT_VALIDATED" style="display: none;">Please enter a value greater than or equal to %s</small><small class="help-block" data-bv-validator="integer" data-bv-for="order" data-bv-result="NOT_VALIDATED" style="display: none;">Please enter a valid number</small></div>

                    <label for="Adult" class="col-sm-1 control-label">Departure</label>
                    <div class="col-sm-1">  
                        <input type="text" class="form-control" id="" name="">
                    </div>
                    <div class="col-sm-1">  

                        <input type="text" class="form-control" id="" name=""></div>



                    <label for="Adult" class="col-sm-1 control-label">Dept Date</label>
                    <div class="col-sm-1">  

                        <input type="text" class="form-control" id="" name=""></div>

                    <label for="Adult" class="col-sm-1 control-label">Ticket</label>
                    <div class="col-sm-1">  

                        <select class="form-control">
                            <option>6 MONTH</option>
                        </select></div>
                    <label for="Adult" class="col-sm-1 control-label">Class</label>

                    <div class="col-sm-1">  

                        <select class="form-control">
                            <option>Econo</option>
                        </select></div><div class="col-sm-1">  

                        <input type="text" class="form-control" id="" name=""></div>
                </div>  
                <div class="col-sm-12">
                    <div class="col-sm-2">  

                    </div>
                    <label for="Adult" class="col-sm-1 control-label">Arrival</label>
                    <div class="col-sm-1">  
                        <input type="text" class="form-control" id="" name="">
                    </div>


                    <div class="col-sm-2">  
                        <input type="text" class="form-control" id="" name="">
                    </div>

                    <label for="Adult" class="col-sm-1 control-label">Flight</label>



                    <div class="col-sm-1">  
                        <input type="text" class="form-control" id="" name="" autocomplete="off">
                    </div><label for="Adult" class="col-sm-1 control-label">Air Port</label><div class="col-sm-2">  
                        <input type="text" class="form-control" id="" name="">
                    </div>
                </div>  
                <div class="col-sm-12">

                    <label for="Adult" class="col-sm-2 control-label">Dept/Arrv. Time</label>
                    <div class="col-sm-3">
                        <div class="col-sm-3">  
                            <input type="text" class="form-control" id="" name="">
                        </div>
                        <div class="col-sm-3">  
                            <input type="text" class="form-control" id="" name="">
                        </div><div class="col-sm-3">  
                            <input type="text" class="form-control" id="" name="">
                        </div>
                        <div class="col-sm-3">  
                            <input type="text" class="form-control" id="" name="">
                        </div>
                    </div>
                    <label for="Adult" class="col-sm-2 control-label">Flag Route</label>
                    <div class="col-sm-1">  

                        <select class="form-control">
                            <option>6 MONTH</option>
                        </select></div>

                    <label for="Adult" class="col-sm-1 control-label">Status</label>
                    <div class="col-sm-1">  

                        <select class="form-control">
                            <option>OK</option>
                        </select></div>

                    <label for="Adult" class="col-sm-1 control-label">Update By
                    </label>
                    <div class="col-sm-1">  

                        <input type="text" class="form-control" id="" name=""></div>

                </div>  
            </div>
        </div>
        <!--Cost Price--> 
        <div class="col-sm-10 alert alert-info">
            <!--Cost-->
            <div class="col-sm-4">
                <div class="">
                    <label class="col-sm-12">Cost</label>
                    <div class="col-sm-12">
                        <label class="col-sm-2 control-label">Adult</label>
                        <div class="col-sm-2">  
                            <input type="number" class="form-control" name="CostAdult" data-bv-field="CostAdult"><i class="form-control-feedback bv-no-label" data-bv-icon-for="CostAdult" style="display: none;"></i><i class="form-control-feedback bv-no-label" data-bv-icon-for="CostAdult" style="display: none;"></i><i class="form-control-feedback" data-bv-icon-for="CostAdult" style="display: none;"></i>
                            <small class="help-block" data-bv-validator="integer" data-bv-for="CostAdult" data-bv-result="NOT_VALIDATED" style="display: none;">Please enter a valid number</small></div>
                        <div class="col-sm-2">  
                            <select name="" class="">
                                <option value="">THB</option>
                            </select>
                        </div>
                        <label for="Adult" class="col-sm-1 control-label">Tax</label>
                        <div class="col-sm-2">  
                            <input type="text" class="form-control" id="" name="">
                        </div>
                        <div class="col-sm-2">  
                            <select name="" class="">
                                <option value="">THB</option>
                            </select>
                        </div>
                    </div>  
                    <div class="form-group has-feedback">
                        <label class="col-sm-2 control-label">Child</label>
                        <div class="col-sm-2">  
                            <input type="number" class="form-control" name="Child" data-bv-field="Child"><i class="form-control-feedback" data-bv-icon-for="Child" style="display: none;"></i>
                            <small class="help-block" data-bv-validator="integer" data-bv-for="Child" data-bv-result="NOT_VALIDATED" style="display: none;">Please enter a valid number</small></div>
                        <div class="col-sm-2">  
                            <select name="" class="">
                                <option value="">THB</option>
                            </select>
                        </div>
                        <label for="Adult" class="col-sm-1 control-label">Tax</label>
                        <div class="col-sm-2">  
                            <input type="text" class="form-control" id="" name="">
                        </div>
                        <div class="col-sm-2">  
                            <select name="" class="">
                                <option value="">THB</option>
                            </select>
                        </div>
                    </div>  
                    <div class="form-group">
                        <label for="Adult" class="col-sm-2 control-label">Infant</label>
                        <div class="col-sm-2">  
                            <input type="text" class="form-control" id="" name="">
                        </div>
                        <div class="col-sm-2">  
                            <select name="" class="">
                                <option value="">THB</option>
                            </select>
                        </div>
                        <label for="Adult" class="col-sm-1 control-label">Tax</label>
                        <div class="col-sm-2">  
                            <input type="text" class="form-control" id="" name="">
                        </div>
                        <div class="col-sm-2">  
                            <select name="" class="">
                                <option value="">THB</option>
                            </select>
                        </div>
                    </div>  
                </div>
            </div>  
            <!--Price-->
            <div class="col-sm-5">
                <div class="">
                    <label for="Adult" class="col-sm-12">Price</label><div class="form-group">
                        <label for="Adult" class="col-sm-2 control-label">Adult</label>
                        <div class="col-sm-2">  
                            <input type="text" class="form-control" id="" name="">
                        </div>
                        <div class="col-sm-2">  
                            <select name="" class="">
                                <option value="">THB</option>
                            </select>
                        </div>
                        <label for="Adult" class="col-sm-1 control-label">Tax</label>
                        <div class="col-sm-2">  
                            <input type="text" class="form-control" id="" name="">
                        </div>
                        <div class="col-sm-2">  
                            <select name="" class="">
                                <option value="">THB</option>
                            </select>
                        </div>
                    </div>  
                    <div class="form-group">
                        <label for="Adult" class="col-sm-2 control-label">Child</label>
                        <div class="col-sm-2">  
                            <input type="text" class="form-control" id="" name="">
                        </div>
                        <div class="col-sm-2">  
                            <select name="" class="">
                                <option value="">THB</option>
                            </select>
                        </div>
                        <label for="Adult" class="col-sm-1 control-label">Tax</label>
                        <div class="col-sm-2">  
                            <input type="text" class="form-control" id="" name="">
                        </div>
                        <div class="col-sm-2">  
                            <select name="" class="">
                                <option value="">THB</option>
                            </select>
                        </div>
                    </div>  
                    <div class="form-group">
                        <label for="Adult" class="col-sm-2 control-label">Infant</label>
                        <div class="col-sm-2">  
                            <input type="text" class="form-control" id="" name="">
                        </div>
                        <div class="col-sm-2">  
                            <select name="" class="">
                                <option value="">THB</option>
                            </select>
                        </div>
                        <label for="Adult" class="col-sm-1 control-label">Tax</label>
                        <div class="col-sm-2">  
                            <input type="text" class="form-control" id="" name="">
                        </div>
                        <div class="col-sm-2">  
                            <select name="" class="">
                                <option value="">THB</option>
                            </select>
                        </div>
                    </div>  
                </div>
            </div>
            <!--Detail-->
            <div class="col-sm-3">
                <div class="">
                    <div class="form-group">
                        <label for="Detail" class="col-sm-3 control-label">Detail</label>
                        <div class="col-sm-9">  
                            <input type="text" class="form-control" id="" name="Detail">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label for="Qty" class="col-sm-2 control-label">Qty</label>
                        <div class="col-sm-6">  
                            <input type="text" class="form-control" id="" name="Detail">
                        </div>
                        <div class="col-sm-1">  
                            <input type="checkbox" name="">
                        </div>
                        <label for="Detail" class="col-sm-1">Pay</label>
                    </div> 
                    <div class="form-group">
                        <label for="Detail" class="col-sm-2 control-label">Cost</label>
                        <div class="col-sm-4">  
                            <input type="text" class="form-control" id="" name="Detail">
                        </div>
                        <label for="Detail" class="col-sm-3 control-label">Amount</label>
                        <div class="col-sm-3">  
                            <input type="text" class="form-control" id="" name="Detail">
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <!--Dept Arrv-->
        <div class="col-sm-10 alert alert-warning">
            <div class="">
                <div class="form-group">
                    <div class="col-sm-12">
                        <label for="Adult" class="col-sm-3 control-label">Dept Arrv</label>






                        <label for="Adult" class="col-sm-2 control-label">Ticket No</label><label for="Adult" class="col-sm-2 control-label">D B C O</label>

                        <label for="Adult" class="col-sm-1 control-label">COJ</label></div>
                </div>
                <div class="form-group">
                    <div class="col-sm-12">
                        <div class="col-sm-1"><input type="text" class="form-control"></div>
                        <div class="col-sm-1"><input type="text" class="form-control"></div>
                        <div class="col-sm-1"><input type="text" class="form-control"></div>
                        <div class="col-sm-1"><input type="text" class="form-control"></div>
                        <div class="col-sm-1"><input type="text" class="form-control"></div>
                        <div class="col-sm-1"><input type="text" class="form-control"></div>
                        <div class="col-sm-2">


                            <input type="radio" class=""><input type="radio" class=""><input type="radio" class=""><input type="radio" class=""></div>


                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-12">
                        <div class="col-sm-1"><select name="" class="form-control">
                                <option value="">Mr.</option>
                            </select></div>
                        <div class="col-sm-2"><input type="text" class="form-control"></div>
                        <div class="col-sm-2"><input type="text" class="form-control"></div>
                        <div class="col-sm-1"><select name="" class="form-control">
                                <option value="">Add</option>
                            </select></div>
                        <div class="col-sm-1"><label class="form-label">Com</label>
                        </div>
                        <div class="col-sm-1"><input type="text" class="form-control"></div>



                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-12">
                        <div class="col-sm-1"><label class="form-label">Ticket Far</label></div>
                        <div class="col-sm-2"><input type="text" class="form-control"></div>
                        <div class="col-sm-1"><label class="form-label">Tax</label></div>
                        <div class="col-sm-2"><input type="text" class="form-control"></div>

                        <div class="col-sm-2"><select name="" class="form-control">
                                <option value="">NORMAL</option>
                            </select></div>



                    </div>
                </div>



            </div>
        </div>  
        <!--Remarks-->
        <div class="col-sm-12 alert alert-success">
            <div class="">
                <div class="col-sm-12">
                    <label class="col-sm-1 control-label">Remarks</label>
                    <div class="col-sm-1">  
                        <input type="text" class="form-control" name="">
                    </div>


                    <label for="Adult" class="col-sm-1"><input type="checkbox" name="">Pick Up</label><label for="Adult" class="col-sm-1"><input type="checkbox" name="">Delivery</label><div class="col-sm-3">
                        <label for="Adult" class="col-sm-5 control-label">Agent Comm</label>
                        <div class="col-sm-2">  
                            <input type="text" class="form-control" name="">
                        </div><div class="col-sm-5">  

                            <input type="text" class="form-control" name=""></div></div>



                    <div class="col-sm-2">
                        <label class="col-sm-2 control-label">com</label>
                        <div class="col-sm-10">  

                            <input type="text" class="form-control" name=""></div></div>


                </div>

                <div class="col-sm-12">
                    <label for="Adult" class="col-sm-1 control-label">Guide</label>
                    <div class="col-sm-3">  
                        <input type="text" class="form-control" id="" name="">
                    </div>


                    <label for="Adult" class="col-sm-1 control-label">Com</label>

                    <div class="col-sm-3">  

                        <input type="text" class="form-control" id="" name=""></div>

                    <div class="col-sm-4 text-right">  
                        <a class="btn btn-success btn-sm">New</a>
                        <a class="btn btn-primary btn-sm">OK</a>

                    </div>

                </div>  

            </div>
        </div>  

    </form>
</div>


<script>

    $(document).ready(function () {
        $('#containerForm').bootstrapValidator({
            container: 'tooltip',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                firstName: {
                    validators: {
                        notEmpty: {
                            message: 'The first name is required'
                        }
                    }
                },
                lastName: {
                    validators: {
                        notEmpty: {
                            message: 'The last name is required'
                        }
                    }
                },
                phone: {
                    validators: {
                        digits: {
                            message: 'The phone number can contain digits only'
                        },
                        notEmpty: {
                            message: 'The phone number is required'
                        }
                    }
                }
            }
        });
    });

</script>





