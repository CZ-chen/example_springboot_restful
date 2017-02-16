package tech.nodex.example_springboot_restful.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.nodex.example_springboot_restful.dao.Product;
import tech.nodex.example_springboot_restful.dao.utils.ModelUtils;
import tech.nodex.example_springboot_restful.domain.ProductVo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController //使用该注解将自动封装json
@RequestMapping(value="/product")
@Validated
public class ProductController {

    @RequestMapping(value="/create", method= RequestMethod.POST)
    public ProductVo createProduct( @Valid @RequestBody ProductVo product){
        product.setId(null);
        Product productModel = ModelUtils.from(product,Product.class);
        productModel.save();
        return getProduct(productModel.getId());
    }

    @RequestMapping(value="/get/{productId}", method= RequestMethod.GET)
    public ProductVo getProduct(@PathVariable Long productId) {
        Product product = Product.db.findById(productId);
        if(product!=null){
            return ModelUtils.to(product,ProductVo.class);
        }
        return null;
    }

    @RequestMapping(value="/querystore/{storeId}", method=RequestMethod.GET)
    List<ProductVo> getStoreProducts(@PathVariable Long storeId) {
        List<Product> products = Product.findProductsByStore(storeId);
        return ModelUtils.to(products,ProductVo.class);
    }

    @RequestMapping(value="/query-by-name", method=RequestMethod.GET)
    List<ProductVo> getStoreProducts(@NotNull @Size(min=2) String name) {
        List<Product> products = Product.findProductsByName(name);
        return ModelUtils.to(products,ProductVo.class);
    }
}
