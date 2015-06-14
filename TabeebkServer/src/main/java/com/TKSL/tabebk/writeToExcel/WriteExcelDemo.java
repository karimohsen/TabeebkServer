/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TKSL.tabebk.writeToExcel;

//import statements

import com.tabeebkServer.parseExcel.Constants;
import com.tabeebkServer.daos.DoctorDao;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.tabeebkServer.pojo.Doctorspeciality;
import com.tabeebkServer.pojo.Telephone;
import java.net.URLDecoder;



public class WriteExcelDemo
{
     DoctorDao dao=new DoctorDao();
         FileOutputStream fileOut ;
         
            Workbook wb = new HSSFWorkbook();
             CreationHelper createHelper = wb.getCreationHelper();
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        
        ArrayList<String> arr=new ArrayList();
        //arr.add("phone");
        arr.add("doctorName");
       arr.add("phone");
        arr.add("doctorDegree");
        arr.add("doctorspeciality");
        WriteExcelDemo w=new WriteExcelDemo();
        w.writeToExcel(arr);
        /////////////////////////////
//        Workbook wb = new HSSFWorkbook();
//    //Workbook wb = new XSSFWorkbook();
//    CreationHelper createHelper = wb.getCreationHelper();
//    Sheet sheet = wb.createSheet("new sheet");
//
//    // Create a row and put some cells in it. Rows are 0 based.
//    Row row = sheet.createRow((short)0);
//    // Create a cell and put a value in it.
//    Cell cell = row.createCell(0);
//    cell.setCellValue(1);
//
//    // Or do it on one line.
//    row.createCell(1).setCellValue(1.2);
//    row.createCell(2).setCellValue(
//         createHelper.createRichTextString("This is a string"));
//    row.createCell(3).setCellValue(true);
//
//    
//    row = sheet.createRow((short)1);
//    // Create a cell and put a value in it.
//     cell = row.createCell(0);
//    cell.setCellValue(1);
//
//    // Or do it on one line.
//    row.createCell(1).setCellValue(1.2);
//    row.createCell(2).setCellValue(
//         createHelper.createRichTextString("This is a string"));
//    row.createCell(3).setCellValue(true);
//
//    // Write the output to a file
//    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
//    wb.write(fileOut);
//    fileOut.close();
       
    }
    public void writeToExcel(List<String> allSelections) throws FileNotFoundException, IOException{
        
    
            //Workbook wb = new XSSFWorkbook();
           
            Sheet sheet = wb.createSheet("doctor sheet");
            // Create a row and put some cells in it. Rows are 0 based.
            Row row = sheet.createRow((short)0);
            //////////////////////////////////
           
            List results;
            List phones;
            ArrayList doctorSelctions=new ArrayList();
            for (int i = 0; i <allSelections.size(); i++) {
                String Selection=allSelections.get(i);
                
                
                
                row.createCell(i).setCellValue(
                        createHelper.createRichTextString(Selection));
                if(Selection.equals("phone") || Selection.equals("address"))continue;
                doctorSelctions.add(Selection);
            }  
            results=dao.getAllDoctors(doctorSelctions);
            //////////////excel data fill 
            Iterator itr1=results.iterator();
            Object[] res1=(Object[]) itr1.next();
           int doctorId=1;
           System.out.println("doctorid is "+doctorId);
//            int doctorId=1;
            int rowIdx=1 , cellIdx=0;
            Iterator itr=results.iterator();
            while(itr.hasNext()){
                 Object[] res=(Object[]) itr.next();
                   doctorId=(Integer) res[doctorSelctions.size()];
                System.out.println("in while loooooooooooop");
                String cellData="";
                row = sheet.createRow((short)rowIdx);
                int itridx=0;
                for (int i = 0; i < allSelections.size(); i++) {
                    String Selection=allSelections.get(i);
                    if(Selection.equals("phone") )
                    {
                        Telephone t=(Telephone) dao.getDoctorPhones(doctorId).get(0);
                        cellData=t.getTelephoneNumber();
                        System.out.println("cell data here is "+cellData);
                       
                    }
                    else if(Selection.equals("address")){
                        
                        
                        
                    }
//                    else if(Selection.equals("doctorspeciality")){
//                        Object[] res=(Object[]) itr.next();
//                            Doctorspeciality specialtity=(Doctorspeciality) res[itridx++];
//                            cellData=specialtity.getDoctorSpecialityDescription();
//                    }
                    else{
                        System.out.println("kjhkuhihu");
                       
                    
                       if(!Selection.equals("doctorspeciality"))
                        cellData= res[itridx]+"";
                       else{
                           Doctorspeciality specialtity=(Doctorspeciality) res[itridx];
                       cellData=specialtity.getDoctorSpecialityDescription();
                       }
                        itridx++;
                    
                    }
                        
                        
                    
                    
                    row.createCell(i).setCellValue(
                            createHelper.createRichTextString(cellData));
                }
            
            
           rowIdx++;
          
            }
                           String path = this.getClass().getClassLoader().getResource("").getPath();

            String fullPath = URLDecoder.decode(path, "UTF-8");
String pathArr[] = fullPath.split("/WEB-INF/classes/");
        String filePath =pathArr[0]+"\\bulkDownload\\mspWorkBook.xls";

                fileOut = new FileOutputStream(filePath);
             wb.write(fileOut);
            fileOut.close();
        }
   
    String[] sheetName={"","hospital sheet","clinic sheet"};
      public void writeToExcelClinic(List<String> allSelections ,  int mspType ) throws FileNotFoundException, IOException{
         Sheet sheet = wb.createSheet(sheetName[mspType]);
            // Create a row and put some cells in it. Rows are 0 based.
            Row row = sheet.createRow((short)0); 
        List<String[]>clinicResult=  dao.getAllClinics2(allSelections,mspType);
               for (int i = 0; i <allSelections.size(); i++) {
                String Selection=allSelections.get(i);
               
                row.createCell(i).setCellValue(
                        createHelper.createRichTextString(Selection));
              }
               
               for (int i = 0; i <clinicResult.size(); i++) {
                   String [] clinicData=clinicResult.get(i);
                     row = sheet.createRow((short)i+1);
                   for (int j = 1; j < clinicData.length; j++) {
                        row.createCell(j-1).setCellValue(
                        createHelper.createRichTextString(clinicData[j]));
                   }
              
          }
               String path = this.getClass().getClassLoader().getResource("").getPath();

String fullPath = URLDecoder.decode(path, "UTF-8");
String pathArr[] = fullPath.split("/WEB-INF/classes/");
        String filePath =pathArr[0]+"\\bulkDownload\\mspWorkBook.xls";

                fileOut = new FileOutputStream(filePath);
             wb.write(fileOut);
            fileOut.close();
          
      }
    
}


