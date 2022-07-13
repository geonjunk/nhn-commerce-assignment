package com.nhn.commerce.service

import com.nhn.commerce.model.Product
import com.nhn.commerce.repository.ProductRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    fun findProductList(): List<Product> = productRepository.findProductList()
    fun findProductDetail(productNo:Int): Product = productRepository.findProductDetail(productNo)
    fun addProduct(product: Product):List<Product> = productRepository.addProduct(product.productNo,product.productName,product.salePrice,LocalDateTime.now())
    fun updateProduct(productNo: Int,productName:String,salePrice:Int):List<Product> = productRepository.updateProduct(productNo,productName,salePrice,LocalDateTime.now())

    fun deleteProduct(productNo: Int):List<Product> = productRepository.deleteProduct(productNo)
}
