package Services;

import DTO.ProductDTO;
import Model.Product;
import Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MappingUntils mappingUntils;

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(mappingUntils::mappingProductDTO).toList();
    }

    public List<ProductDTO> findById(Long id) {
        return productRepository.findById(id).stream().map(mappingUntils::mappingProductDTO).toList();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id,String name,double price,Product product) {
        if(productRepository.existsById(id)) {
            product.setId(id);
            product.setName_product(name);
            product.setPrice(price);
            return productRepository.save(product);
        }
        else{
            throw new RuntimeException("Product not found");
        }
    }

    public void deleteProduct(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Product not found");
        }
    }


}
