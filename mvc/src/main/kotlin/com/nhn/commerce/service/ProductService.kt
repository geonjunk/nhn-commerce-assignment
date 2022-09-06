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
    fun addProduct(productNo: Int,productName:String,salePrice:Int)= productRepository.addProduct(productNo,productName,salePrice)
    fun updateProduct(productNo: Int,productName:String,salePrice:Int) = productRepository.updateProduct(productNo,productName,salePrice)

    fun deleteProduct(productNo: Int) = productRepository.deleteProduct(productNo)
}
