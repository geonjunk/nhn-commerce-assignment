package com.nhn.commerce.repository

import com.nhn.commerce.model.Product
import org.apache.ibatis.annotations.*
import java.time.LocalDateTime

@Mapper
interface ProductRepository {
    @Select("SELECT * FROM product")
    fun findProductList(): List<Product>

    @Select("SELECT * FROM product WHERE product_no = #{productNo}")
    fun findProductDetail(productNo:Int): Product


    @Select("INSERT INTO product (product_no,product_name,sale_price,register_ymdt,update_ymdt) VALUES(#{productNo},#{productName},#{salePrice},#{localDateTime},#{localDateTime})")
    fun addProduct(productNo:Int,productName:String,salePrice: Int,localDateTime: LocalDateTime= LocalDateTime.now())

    @Select("Update product SET sale_price=#{salePrice} , product_name=#{productName},update_ymdt=#{localDateTime} where product_no=#{productNo}")
    fun updateProduct(productNo:Int,productName: String,salePrice:Int,localDateTime: LocalDateTime= LocalDateTime.now())

    @Select("Delete from product where product_no=#{productNo}")
    fun deleteProduct(productNo: Int)
}
