package com.nhn.commerce.controller

import com.nhn.commerce.model.Product
import com.nhn.commerce.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class ProductController(
    private val productService: ProductService,
) {
    @GetMapping("/product")
    fun getProductList(model: Model): String {
        model.addAttribute("productList", productService.findProductList())
        model.addAttribute("product",Product(0,"", null,0,null))
        return "product"
    }

    // TODO (상품 상세 조회 기능 + Exception 처리)
    @GetMapping("/product/{productNo}")
    fun getProductDetail(@PathVariable (name="productNo") productNo:Int,model:Model):String{
        model.addAttribute("productDetail",productService.findProductDetail(productNo))
        return "detailProduct"
    }


    // TODO (상품 추가 기능) 완료
    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    fun addProduct(
        product:Product,model: Model):String{
        productService.addProduct(product)
        return getProductList(model)
    }


    // TODO (상품 수정 기능 + Exception 처리) 완료
    @PostMapping("/product/update")
    fun updateProduct(product: Product,model: Model):String{
        productService.updateProduct(product.productNo,product.productName,product.salePrice)
        return "redirect:/product"
    }

    // TODO (상품 삭제 기능 + Exception 처리)
    @PostMapping("/product/delete")
    fun deleteProduct(product: Product,model: Model):String{
            productService.deleteProduct(product.productNo)
        return  "redirect:/product"
    }
}
