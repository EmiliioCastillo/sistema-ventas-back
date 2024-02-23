package com.projectjava.demosclient;


/*
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProveedorProductosTest {

    @Autowired
    private ProveedorProductosDao proveedoresProductosDao;

    @Autowired
    private TestEntityManager tem;

    //Para ver que productos existen
        @Test
        public void añadirProducto(){
            Productos producto = tem.find(Productos.class, 1);
            //Creamos proveedor
            Proveedor proveedor = tem.find(Proveedor.class, 1);

            ProveedoresProductos pyp = new ProveedoresProductos(1L, 5000, 2312, producto, proveedor);

            proveedoresProductosDao.save(pyp);
        }
    @Test
    public void añadirProductoMultiple(){
        Proveedor proveedor = new Proveedor(3L);
        Productos producto = new Productos(11L);


        ProveedoresProductos pyp = new ProveedoresProductos(3L, 145000, 60000, producto, proveedor);

        proveedoresProductosDao.saveAll(List.of(pyp));
    }

    @Test
    public void listarTodo(){
            List<ProveedoresProductos> proveedoresProductosList = proveedoresProductosDao.findAll();

            proveedoresProductosList.forEach(System.out::println);
    }


}
*/