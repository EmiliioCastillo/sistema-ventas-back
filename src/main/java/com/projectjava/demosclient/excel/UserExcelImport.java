package com.projectjava.demosclient.excel;

import com.projectjava.demosclient.entity.Productos;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.*;

public class UserExcelImport {
    public Set<Productos> excelImport(MultipartFile file) {
        Set<Productos> listProductos = new HashSet<>();


        FileInputStream inputStream;
        try {
            XSSFWorkbook workbook = XSSFWorkbookFactory.createWorkbook(OPCPackage.open(new ByteArrayInputStream(file.getBytes())));
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();
            Productos productos = new Productos();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    int columnIndex = nextCell.getColumnIndex();
                    switch (columnIndex) {

                        case 1 -> productos.setCodigo(nextCell.getStringCellValue());
                        case 2 -> productos.setProducto(nextCell.getStringCellValue());
                        case 3 -> productos.setCategoria(nextCell.getStringCellValue());
                        case 4 ->productos.setDescripcion(nextCell.getStringCellValue());
                        case 5 -> productos.setPrecio(nextCell.getStringCellValue());
                        case 6 -> productos.setCantidad(nextCell.getStringCellValue());
                        case 7 -> productos.setEstatus(nextCell.getStringCellValue());
                    }
                    listProductos.add(productos);
                }
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listProductos;
    }
}
