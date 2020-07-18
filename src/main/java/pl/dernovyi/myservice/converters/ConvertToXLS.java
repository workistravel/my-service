package pl.dernovyi.myservice.converters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import pl.dernovyi.myservice.models.dao.EmployeeDao;
import pl.dernovyi.myservice.models.dao.UnionDao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public  class ConvertToXLS {
    private List<EmployeeDao> list;
    private SXSSFWorkbook wb ;
    private Sheet sheet;

    public ConvertToXLS(List<EmployeeDao> list) {
        this.list=list;
        wb = new SXSSFWorkbook(100);
        sheet = wb.createSheet("employees");
    }

    public void createXLS(){
        writeHeaderRow();
        writeRows();

    }
    private void writeHeaderRow(){

        Row row = sheet.createRow(0);

        Cell cell = row.createCell(0);
        cell.setCellValue("id_employee");

        cell = row.createCell(1);
        cell.setCellValue("name");

        cell = row.createCell(2);
        cell.setCellValue("unions");

        cell = row.createCell(3);
        cell.setCellValue("salary");

        cell = row.createCell(4);
        cell.setCellValue("date");



    }



    public void writeRows(){


        for(int rownum=0; rownum<list.size(); rownum++) {

             Row row = sheet.createRow(rownum+1); //создаем строку

             for (int cellnum=0; cellnum<5;cellnum++){

                   Cell cell = row.createCell(cellnum); //новая клетка

                if(cellnum==0){
                        cell.setCellValue(list.get(rownum).getId());
                    }

                if(cellnum==1){
                        cell.setCellValue(list.get(rownum).getName());

                }
                if(cellnum==2){
                    StringBuilder builder = new StringBuilder();
                    if(!list.get(rownum).getUnions().isEmpty()) {
                        Iterator<UnionDao> it = list.get(rownum).getUnions().iterator();
                        while (it.hasNext()) {
                            UnionDao union = it.next();
                            builder.append(union.getId());
                            builder.append("-");
                            builder.append(union.getName()+ " ");
                        }
                        builder.append(".");
                        cell.setCellValue(builder.toString());
                    }
                }

                if(cellnum ==3){
                    if(list.get(rownum).isActive()){
                        cell.setCellValue(list.get(rownum).getSalary());
                    }

                }

                if(cellnum ==4){
                    if(list.get(rownum).isActive()) {
                        cell.setCellValue(list.get(rownum).getDateOfEmployment().toString());
                    }
                }
            }

        }


        FileOutputStream out;
        try{
            out= new FileOutputStream("src/main/resources/result/Import.xlsx");
            wb.write(out);
            out.close();
        }catch (
                IOException e){
            e.printStackTrace();
        }
    }
}
