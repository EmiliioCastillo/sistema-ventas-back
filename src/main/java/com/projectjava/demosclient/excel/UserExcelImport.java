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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class UserExcelImport {
    public HashSet<Productos> excelImport(MultipartFile file) {
        HashSet<Productos> listProductos = new HashSet<>();
        long id = 0;
        String codigo = "";
        String descripcion = "";
        String categoria = "";
        String almacen = "";


        FileInputStream inputStream;
        try {
            XSSFWorkbook workbook = XSSFWorkbookFactory.createWorkbook(OPCPackage.open(new ByteArrayInputStream(file.getBytes())));
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    int columnIndex = nextCell.getColumnIndex();
                    switch (columnIndex) {
                        case 0 -> id = (long) nextCell.getNumericCellValue();
                        case 1 -> codigo = nextCell.getStringCellValue();
                        case 2 -> descripcion = nextCell.getStringCellValue();
                        case 3 -> categoria = nextCell.getStringCellValue();
                        case 4 -> almacen = nextCell.getStringCellValue();
                    }
                    listProductos.add(new Productos(id, codigo, descripcion, categoria, almacen));
                }
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listProductos;
    }
}
