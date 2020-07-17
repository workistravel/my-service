package pl.dernovyi.myservice.converters;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pl.dernovyi.myservice.models.dao.EmployeeDao;
import pl.dernovyi.myservice.models.dao.UnionDao;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class ConvertToXML {
    private List<EmployeeDao> list;
    public ConvertToXML(List<EmployeeDao> list) {
        this.list= list;
    }
    String fileName = "employees";






    public void buildXML() throws Exception{
       DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
       DocumentBuilder documentBuilder = factory.newDocumentBuilder();
       Document document = documentBuilder.newDocument();
       Element employeesE1  = document.createElement("Employees");
        document.appendChild(employeesE1);


       for (int r = 0; r < list.size() ; r++) {

           Element employeeE1  = document.createElement("employee");
           Attr attrEmployee = document.createAttribute("id");
           attrEmployee.setValue(list.get(r).getId().toString());
           employeesE1.appendChild(employeeE1);
           employeeE1.setAttributeNode(attrEmployee);



           Element nameE1  = document.createElement("name");
           nameE1.appendChild(document.createTextNode(list.get(r).getName()));
           employeeE1.appendChild(nameE1);

           if(list.get(r).getDateOfEmployment()!=null){
               Element dateE1  = document.createElement("date_empl");
               dateE1.appendChild(document.createTextNode(list.get(r).getDateOfEmployment().toString()));
               employeeE1.appendChild(dateE1);
           }

           if(list.get(r).getSalary()!=null){
               Element salaryE1  = document.createElement("salary");
               salaryE1.appendChild(document.createTextNode(list.get(r).getSalary().toString()));
               employeeE1.appendChild(salaryE1);
           }



           Iterator<UnionDao> it = list.get(r).getUnions().iterator();
           while(it.hasNext()){
               UnionDao union = it.next();
               Element unionE1  = document.createElement("union");
               Attr unionsAttr = document.createAttribute("id");
               unionsAttr.setValue(union.getId().toString());
               unionE1.setAttributeNode(unionsAttr);
               employeeE1.appendChild(unionE1);

               Element nameU1  = document.createElement("union_name");
               nameU1.appendChild(document.createTextNode(union.getName()));
               unionE1.appendChild(nameU1);


           }

       }


       TransformerFactory transformerFactory = TransformerFactory.newInstance();
       Transformer transformer = transformerFactory.newTransformer();

       DOMSource source = new DOMSource(document);

       StreamResult streamResult = new StreamResult(new File("src/main/resources/result/Import.xml"));

       transformer.transform(source, streamResult);


   }

}
