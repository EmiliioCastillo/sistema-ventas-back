package com.projectjava.demosclient.services.excelServices;

import com.projectjava.demosclient.entity.Productos;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {

    public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
    }
    public static HashSet<Productos> getProductsFromExcel(InputStream inputStream){
        HashSet<Productos> listProductos = new HashSet<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("productos");
            int rowIndex =0;
            for (Row row : sheet){
                if (rowIndex ==0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Productos productos = new Productos();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cell.getColumnIndex()) {
                        case 0: // Primer columna: Código
                            if (cell.getCellType() == CellType.STRING) {
                                productos.setCodigo(cell.getStringCellValue());
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                productos.setCodigo(String.valueOf((int) cell.getNumericCellValue())); // Convierte el número a cadena
                            }
                          break;
                        case 1: // Segunda columna: Producto
                            if (cell.getCellType() == CellType.STRING) {
                                productos.setProducto(cell.getStringCellValue());
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                productos.setProducto(String.valueOf(cell.getNumericCellValue()));
                            }
                            break;
                        case 2: // Tercera columna: Precio
                            if (cell.getCellType() == CellType.STRING) {
                                productos.setPrecio(cell.getStringCellValue());
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                productos.setPrecio(String.valueOf(cell.getNumericCellValue()));
                            }
                            break;
                        case 3: // Cuarta columna: Categoría
                            if (cell.getCellType() == CellType.STRING) {
                                productos.setCategoria(cell.getStringCellValue());
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                productos.setCategoria(String.valueOf(cell.getNumericCellValue()));
                            }
                            break;
                        case 4: // Quinta columna: Cantidad
                            if (cell.getCellType() == CellType.STRING) {
                                productos.setCantidad(cell.getStringCellValue());
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                productos.setCantidad(String.valueOf(cell.getNumericCellValue()));
                            }
                            break;
                        case 5: // Sexta columna: Estatus
                            if (cell.getCellType() == CellType.STRING) {
                                productos.setEstatus(cell.getStringCellValue());
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                productos.setEstatus(String.valueOf(cell.getNumericCellValue()));
                            }

                            break;
                        default :
                            break;
                    }
                    cellIndex++;
                }
                listProductos.add(productos);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return listProductos;
    }
}
