package tw.com.ispan.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.domain.ProductBean;
import tw.com.ispan.service.ProductService;

@RestController
@RequestMapping("/rest/pages/products")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> find() {
        List<ProductBean> products = productService.select(null);
        if(products != null && !products.isEmpty()) {
            ResponseEntity<List<ProductBean>> response = ResponseEntity.ok(products);
            return response;
        } else {
            ResponseEntity<Void> response = ResponseEntity.ok().build();
            return response;
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody String entity) {
        ProductBean product = productService.create(entity);
        if (product != null) {
            URI uri = URI.create("/rest/pages/products/"+product.getId());
            return ResponseEntity.created(uri).body(product);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
        ProductBean product = productService.findById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Integer id) {
        if(productService.remove(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable Integer id, @RequestBody ProductBean body) {
        ProductBean product = productService.update(body);
        if(product!=null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
