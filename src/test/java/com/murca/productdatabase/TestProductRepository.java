package com.murca.productdatabase;

import com.murca.productdatabase.product.ProductRrpository;
import com.murca.productdatabase.product.Products;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class TestProductRepository {
    @Autowired private ProductRrpository repo;

    @Test
   // public void testAdd(@RequestParam(value = "name",defaultValue = "not added")String name,@RequestParam(value = "description",defaultValue = "nothing added")String description,@RequestParam(value = "quantity",defaultValue = "nothing added")String quantity)
    public void testAdd()
    {
        Products product= new Products();

        product.setName("4MM");
        product.setDescription("OBS");
        product.setQuantity("10");
        product.setPrice("250");


        Products prodAdd=repo.save(product);
        Assertions.assertThat(prodAdd).isNotNull();
        Assertions.assertThat(prodAdd.getId()).isGreaterThan(0);
    }

    @Test
    public void testList()
    {
        Iterable<Products> product= repo.findAll();
        Assertions.assertThat(product).hasSizeGreaterThan(0);
        for (Products produced:product) {
            System.out.println(produced);
        }
    }
    @Test
    public void testUpdate()
    {
        Integer productId=8;
        Optional<Products> prodId = repo.findById(productId);
        Products prod= prodId.get();
        prod.setPrice("200");
        repo.save(prod);

        Products saveUpd=repo.findById(productId).get();

        Assertions.assertThat(saveUpd.getPrice().equalsIgnoreCase("200"));


    }

    @Test
    public  void testGet()
    {
        Integer productId=1;
        Optional<Products> prodId = repo.findById(productId);
        Assertions.assertThat(prodId).isPresent();
        System.out.println(prodId.get());
    }

    @Test
    public void testDelete()
    {
        Integer productId=12;
        repo.deleteById(productId);
        Optional<Products> prodId = repo.findById(productId);
        Assertions.assertThat(prodId).isNotPresent();
    }

}
