package com.smi.travel.masterdata.controller;

import com.smi.travel.datalayer.entity.MProductType;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.ProductDetail;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.ProductService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class MProductDetailController extends SMITravelController {

    private static final ModelAndView MProductDetail = new ModelAndView("MProductDetail");
    private static final String PRODUCTTYPELIST = "producttype_list";
    private static final String TransectionResult = "result";
    private static final String DISABLED_ADDPRICE = "disabledPrice";
    private static final String DataLap = "product_lap";
    private static final String DATALIST = "productPrice_list";
    private static final String OLDPRODUCT ="Oldproduct";

    private ProductService productService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String code = request.getParameter("code");
        String tempcode = request.getParameter("tempcode");
        String name = request.getParameter("name");
        String ProductTypeID = request.getParameter("producttype");
        String description = request.getParameter("description");
        String remark = request.getParameter("remark");
        String ProductID = request.getParameter("ProductID");
        String isStock = request.getParameter("isStock");
        UtilityFunction util = new UtilityFunction();
        String operation = "";
        String stock = request.getParameter("stock");
        
        System.out.println("action  :" + action);
        System.out.println("code ; "+code);
        if((code == null) ||("null".equalsIgnoreCase(code))){
            code = tempcode;
            System.out.println("temp : "+code);
        }
        int result = 0;
        String resultValidate = "";
        SystemUser user = (SystemUser) session.getAttribute("USER");
        request.setAttribute("disableProductCode", "");
        ProductDetail Priceitem = new ProductDetail();
        Product productID = new Product();
        Product product = new Product();
        product.setCode((String.valueOf(code)).toUpperCase());
        product.setName((String.valueOf(name)).toUpperCase());
        if (!"".equalsIgnoreCase(ProductTypeID)) {
            MProductType producttype = new MProductType();
            producttype.setId(ProductTypeID);
            product.setMProductType(producttype);

        }
        if(remark != null){
            remark = remark.trim();
        }
        if(description != null){
            description = description.trim();
        }
        if(isStock != null){
            product.setIsStock(1);
        } else {
            product.setIsStock(0);
        }
        product.setRemark(remark);
        product.setDescription(description);
        product.setId(ProductID);

        
        if (("addlistprice".equalsIgnoreCase(action)||("updatePriceItem".equalsIgnoreCase(action))||("deletePriceItem".equalsIgnoreCase(action)))){
            System.out.println("ProductID :" + ProductID);
            productID.setId(ProductID);
            Priceitem.setEffectiveFrom(util.convertStringToDate(request.getParameter("effectivefrom")));
            Priceitem.setEffectiveTo(util.convertStringToDate(request.getParameter("effectiveto")));
            Priceitem.setAdCost(util.convertStringToInteger(request.getParameter("ADcost")));
            Priceitem.setAdPrice(util.convertStringToInteger(request.getParameter("ADprice")));
            Priceitem.setChCost(util.convertStringToInteger(request.getParameter("CHcost")));
            Priceitem.setChPrice(util.convertStringToInteger(request.getParameter("CHprice")));
            Priceitem.setInCost(util.convertStringToInteger(request.getParameter("INcost")));
            Priceitem.setInPrice(util.convertStringToInteger(request.getParameter("INprice")));
            Priceitem.setProduct(productID);
            Priceitem.setId(request.getParameter("PriceItemID"));
            request.setAttribute("disableProductCode", "disabled");
           
        }

        if ("save".equalsIgnoreCase(action)) {
            if ((ProductID == null) || ("".equalsIgnoreCase(ProductID))) {
                operation = "add";
                
            } else {
                operation = "update";
                request.setAttribute("disableProductCode", "disabled");
            }
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getCode());
            resultValidate = productService.validateProduct(product, operation);
            System.out.println("resultValidate  :" + resultValidate);
            System.out.println("operation  :" + operation);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                if ("add".equalsIgnoreCase(operation)) {
                    result = productService.insertProduct(product);
                } else if ("update".equalsIgnoreCase(operation)) {
                    result = productService.updateProduct(product);
                }
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute("disableProductCode", "disabled");
                    Product productsave = productService.getProductFromcode(code);
                    code = productsave.getCode();
                    ProductID =  productsave.getId();
                    List<Product> productSaveList = productService.searchProduct(product, 1);
                    if(productSaveList != null){ProductID = productSaveList.get(0).getId();}
                    stock = productService.checkProductIsStock(product.getId());
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }
            // End Save
        } else if ("addlistprice".equalsIgnoreCase(action)) {
            Priceitem.setCreateDate(new Date());
            Priceitem.setCreateBy(user.getUsername());
            Priceitem.setUpdateDate(new Date());
            Priceitem.setUpdateBy(user.getUsername());
            result = productService.insertProductDetail(Priceitem);
            if(result == 1){
                request.setAttribute(TransectionResult, "save price successful");
            }else{
                request.setAttribute(TransectionResult, "save price unsuccessful");
            }
        } else if ("updatePriceItem".equalsIgnoreCase(action)) {
            Priceitem.setUpdateDate(new Date());
            Priceitem.setUpdateBy(user.getUsername());
            result = productService.updateProductDetail(Priceitem);
            if(result == 1){
                request.setAttribute(TransectionResult, "save price successful");
            }else{
                request.setAttribute(TransectionResult, "save price unsuccessful");
            }
        } else if ("deletePriceItem".equalsIgnoreCase(action)) {
            System.out.println("action delete :"+action);
            result = productService.deleteProductDetail(Priceitem);
            if(result == 1){
                request.setAttribute(TransectionResult, "delete price successful");
            }else{
                request.setAttribute(TransectionResult, "delete price unsuccessful");
            }
        }  else if ("edit".equalsIgnoreCase(action)) {
            Product productDetail = productService.getProductFromID(request.getParameter("productid").toString());
            code = (String.valueOf(productDetail.getCode())).toUpperCase();
            name = (String.valueOf(productDetail.getName())).toUpperCase();
            if(description != null){
                description = description.trim();
            }
            description = productDetail.getDescription();
            remark = productDetail.getRemark();
            isStock = String.valueOf(productDetail.getIsStock());
            if(remark != null){
                remark = remark.trim();
            }
            ProductID = request.getParameter("productid").toString();
            request.setAttribute("disableProductCode", "disabled");
            if (productDetail.getMProductType() != null) {
                ProductTypeID = productDetail.getMProductType().getId();
            }
            stock = productService.checkProductIsStock(request.getParameter("productid").toString());

            List<ProductDetail> priceList = new ArrayList<ProductDetail>(productDetail.getProductDetails());
            request.setAttribute(DATALIST, priceList);

        }
        if ((ProductID != null) && (!"".equalsIgnoreCase(ProductID))) {
            System.out.println("ProductID :" + ProductID);
            Product pricelist = productService.getProductFromID(ProductID);
            if (pricelist.getProductDetails() != null) {
                List<ProductDetail> itemList = new ArrayList<ProductDetail>(pricelist.getProductDetails());
                request.setAttribute(DATALIST, itemList);
            }

        }
        
        if (("addlistprice".equalsIgnoreCase(action)||("updatePriceItem".equalsIgnoreCase(action))||("deletePriceItem".equalsIgnoreCase(action)))){
            
            Product productDetail = productService.getProductFromID(request.getParameter("ProductID").toString());
            code =  (String.valueOf(productDetail.getCode())).toUpperCase();
            name = (String.valueOf(productDetail.getName())).toUpperCase();
            description = productDetail.getDescription();
            remark = productDetail.getRemark();
            isStock = String.valueOf(productDetail.getIsStock());
            ProductID = request.getParameter("ProductID").toString();
            if (productDetail.getMProductType() != null) {
                ProductTypeID = productDetail.getMProductType().getId();
            }
            stock = productService.checkProductIsStock(request.getParameter("productid").toString());
        }
        
        
        if((ProductID == null)||("".equalsIgnoreCase(ProductID))){
            System.out.println("ProducdddtID : "+ProductID);
            request.setAttribute(OLDPRODUCT, "product");
        }

        request.setAttribute(PRODUCTTYPELIST, productService.getListMasterProductType());
        request.setAttribute(DISABLED_ADDPRICE, "");
        request.setAttribute("code", code);
        request.setAttribute("name", name);
        request.setAttribute("producttype", ProductTypeID);
        request.setAttribute("description", description);
        request.setAttribute("remark", remark);
        request.setAttribute("isStock", isStock);
        request.setAttribute("ProductID", ProductID);
        request.setAttribute("stock", stock);        

        return MProductDetail;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

}
