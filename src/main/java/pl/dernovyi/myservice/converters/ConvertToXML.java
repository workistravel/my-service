package pl.dernovyi.myservice.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pl.dernovyi.myservice.models.dao.EmployeeDao;
import pl.dernovyi.myservice.models.dao.UnionDao;
import pl.dernovyi.myservice.models.dto.ActiveEmployeeDto;

import javax.sql.rowset.spi.XmlWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;

public class ConvertToXML {
    private List<EmployeeDao> list;
    public ConvertToXML(List<EmployeeDao> list) {
        this.list= list;
    }
    String fileName = "employees";


//    public  void try2toXML() throws JAXBException, FileNotFoundException {
//
//        //Create JAXB Context
//        JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeDao.class);
//
//        //Create Marshaller
//        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
//        //Required formatting??
//        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//
//        //Store XML to File
//        File file = new File("employee.xml");
//
//        //Writes XML file to file-system
//        jaxbMarshaller.marshal(list, System.out);
//        jaxbMarshaller.marshal(list.toArray(), file);
//    }





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


//       XMLService.
//       public void parseObjectToXml(String filePath, XMLModel xmlModel) {
//           try {
//               JAXBContext contextObj = JAXBContext.newInstance(XMLModel.class);
//
//               Marshaller marshallerObj = contextObj.createMarshaller();
//               marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//               marshallerObj.marshal(xmlModel, new FileOutputStream(filePath));
//           } catch (JAXBException je) {
//               System.out.println("JAXBException");
//           } catch (IOException ie) {
//               System.out.println("IOException");
//           }
//       }

   }

        public  void TryToSave() throws JAXBException {

           // ObjectMapper mapper = new XmlMapper()
//            for (EmployeeDao empl: list) {
//                convertObjectToXml(empl, fileName);
//            }
//            EmployeeDao employeeDao = EmployeeDao.builder()
//                    .id(1L)
//                    .name("wewee")
//                    .salary(1234.0)
//                    .build();
//
//            convertObjectToXml(employeeDao, fileName);
        }
//
    public void convertObjectToXml() throws  IOException {

        XStream xStream = new XStream(new DomDriver());
        xStream.alias(fileName, List.class);
        xStream.processAnnotations(EmployeeDao.class);

        String xml = xStream.toXML(list);
        saveToFile(xml, fileName);

    }
        private static void saveToFile(String xml, String nameFile) throws IOException {
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(nameFile+".xml")));
//            bufferedWriter.write(xml);
//            bufferedWriter.close();
        }
//        try {
//            JAXBContext context = JAXBContext.newInstance(EmployeeDao.class);
//            Marshaller marshaller = context.createMarshaller();
//            // устанавливаем флаг для читабельного вывода XML в JAXB
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//
//            // маршаллинг объекта в файл
//            marshaller.marshal(employee, new File(fileName));
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }

}
