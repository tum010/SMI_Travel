package com.smi.travel.masterdata.controller;
import com.smi.travel.controller.*;
import com.smi.travel.datalayer.entity.MProductType;
import com.smi.travel.datalayer.view.entity.ProductPriceDetail;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.service.ProductService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class ProductController extends SMITravelController {
    private static final ModelAndView Product = new ModelAndView("Product");
    private static final String DATALIST = "product_list";
    private static final String PRODUCTTYPELIST = "producttype_list";
    private static final String TransectionResult = "result";
    
    private ProductService productService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String ProductID = request.getParameter("productID"); 
        String productTypeId = request.getParameter("type"); 
        int result = 1;
        Product product = new Product();
        product.setCode(code);
        product.setName(name);
        product.setId(ProductID);
        if ("search".equalsIgnoreCase(action)) {
            ProductPriceDetail productdetail = new ProductPriceDetail();
            productdetail.setCode(code);
            productdetail.setName(name);
            productdetail.setId(ProductID);
            productdetail.setProductTypeId(productTypeId);
            List<ProductPriceDetail> PriceList = productService.getListProductPriceDetail(productdetail,2);
            request.setAttribute(DATALIST, PriceList);
        } else if ("delete".equalsIgnoreCase(action)) {
            result = productService.deleteProduct(product);
            if (result == 1) {
                request.setAttribute(TransectionResult, "delete successful");
            } else {
                request.setAttribute(TransectionResult, "delete unsuccessful");
            }
        }
        request.setAttribute(PRODUCTTYPELIST, productService.getListMasterProductType());
        
        request.setAttribute("code", code);
        request.setAttribute("name", name);
        request.setAttribute("productTypeId", productTypeId);
        
        return Product;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    
    
}
