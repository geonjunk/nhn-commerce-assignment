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
    fun addProduct(
        productNo: Int,productName:String,salePrice:Int):String{
        try{
            salePrice.also{
                if(it.isPositive()){
                    productService.addProduct(productNo,productName,salePrice)
                }else{
                    println("추가 불가능 : 판매금액이 음수입니다.")
                    throw Error("판매 금액 음수 에러")
                }
            }
        }catch(e:Error){
            println(e)
        }finally {
            return "redirect:/product"
        }
    }


    // TODO (상품 수정 기능 + Exception 처리) 완료
    @PostMapping("/product/update")
    fun updateProduct(product: Product,model: Model):String{
        productService.updateProduct(product.productNo,product.productName,product.salePrice)
        return "redirect:/product"
    }
    
    fun Int.isPositive(): Boolean = this > 0
    // TODO (상품 삭제 기능 + Exception 처리)
    @PostMapping("/product/delete")
    fun deleteProduct(product: Product,model: Model):String{
            productService.deleteProduct(product.productNo)
        return  "redirect:/product"
    }
}
