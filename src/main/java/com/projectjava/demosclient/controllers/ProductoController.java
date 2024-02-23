package com.projectjava.demosclient.controllers;


import com.projectjava.demosclient.dao.ProductoDao;
import com.projectjava.demosclient.entity.Cliente;
import com.projectjava.demosclient.entity.Productos;
import com.projectjava.demosclient.excel.UserExcelImport;
import com.projectjava.demosclient.paginator.PageRender;
import com.projectjava.demosclient.services.productoService.ProductoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
/*
@Controller
@RequestMapping("")
public class ProductoController {

    @Autowired
    ProductoServices productoServices;

    @Autowired
    ProductoDao productoDao;


    @GetMapping("/productos")
    public String listarProductos(@RequestParam(name="page", defaultValue= "0") int page , Model model,@Param("palabraClave") String palabraClave){
        Pageable pageRequest =  PageRequest.of(page,4);
        Page<Productos> listProductos = productoServices.findAll(pageRequest, palabraClave);
        PageRender pageRender = new PageRender<>("/productos", listProductos);

        model.addAttribute("titulo", "SISTEMA INVENTARIO");


        model.addAttribute("productos",listProductos);

        model.addAttribute("page", pageRender);


        model.addAttribute("palabraClave",palabraClave);
       // model.addAttribute("productos", listProductos);
        return "/productos";
    }


    @RequestMapping("/crearproducto")
    public String crear(Map<String, Object> model){
        Productos producto = new Productos();
        model.put("producto", producto);
        return "/crearproducto";
    }
    @PostMapping("/crearproducto")
    public String crearProducto(Productos producto){
        productoServices.guardarProducto(producto);

        return "redirect:/productos";
    }

    @RequestMapping("/productos")
    public String importarExcel(Model model){
        UserExcelImport excel = new UserExcelImport();
        model.addAttribute("userexcelimport", excel);
        return "/productos";
    }


        @GetMapping("/eliminarprod/{id}")
        public String eliminarProducto(@PathVariable(value = "id") Long id){
        productoServices.eliminarProducto(id);
        return "redirect:/productos";
        }

        @GetMapping("/crearproducto/{id}")
        public String editarProducto(@PathVariable(value = "id") Long id, Model model){
        Productos producto2 = productoServices.editarProducto(id);

        model.addAttribute("producto", producto2);
        return "/editarproducto";
        }
}
*/


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    ProductoServices productoServices;

    @GetMapping("/all")
    public Page<Productos>  buscarPorProductos(@RequestParam(name = "limit", defaultValue = "0") int page,
                                         @RequestParam(name = "offset", defaultValue = "3") int size,
                                         @RequestParam(required = false) String categoria,
                                         @RequestParam(required = false) String codigo,
                                         @RequestParam(required = false) String descripcion
    ){

        Page<Productos> productos;
        productos = productoServices.findByCategoriaAndCodigoAndDescripcion(categoria,codigo, descripcion, page, size);
        return productos;
    }





    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editProducts(@PathVariable(value = "id") Long id,
                                          @RequestBody Productos productosBody) {
        if (productoServices.existsByCodigo(productosBody)) {
            return ResponseEntity.ok("{\"response\": \"Duplicado\"}");
        } else {
            productoServices.save(productosBody);
            return ResponseEntity.ok("{\"response\": \"200\"}");
        }

    }


    @GetMapping("/find/{id}")
    public ResponseEntity<?> seeProducts(@PathVariable(value = "id") Long id){
        Optional<Productos> products = productoServices.findById(id);
        return ResponseEntity.ok(products.get());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProducts(@RequestBody Productos products ){
        if (productoServices.existsByCodigo(products)) {
            return ResponseEntity.ok("{\"response\": \"Duplicado\"}");
        } else {
            productoServices.save(products);
            return ResponseEntity.ok("{\"response\": \"200\"}");
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProducts(@PathVariable(value = "id") Long id) {
        productoServices.deleteById(id);
        return ResponseEntity.ok("{\"response\": \"200\"}");
    }


    @PostMapping("/upload")
    public ResponseEntity<?> importarExcel(@RequestBody UserExcelImport excelImp) {
        return ResponseEntity.ok().body(excelImp);
    }

}

