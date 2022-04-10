package com.murca.productdatabase.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
@Autowired private ProductRrpository repo;

@RequestMapping("/product")
    public List<Products> productList()
{
   // List<Products> all = repo.findAll();
    return (List<Products>) repo.findAll();
}


    public void save(Products products) {
    repo.save(products);
    }
    public Products get(Integer id) throws UserNotFoundException
    {
        Optional<Products> result = repo.findById(id);
        if(result.isPresent())
        {
            return result.get();
        }
        throw new UserNotFoundException("Could not any Products with ID" + id );
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count=repo.countById(id);
        if(count==null || count==0)
        {
            throw new UserNotFoundException("Could not any Products with ID" + id );
        }
        repo.deleteById(id);
    }
}
