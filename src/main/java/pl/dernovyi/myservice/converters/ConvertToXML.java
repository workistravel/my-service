package pl.dernovyi.myservice.converters;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pl.dernovyi.myservice.models.dto.ActiveEmployeeDto;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;

public class ConvertToXML {
    private List<ActiveEmployeeDto> list;
    public ConvertToXML(List<ActiveEmployeeDto> list) {
        this.list= list;
    }

   public void buildXLS() throws Exception{
       DocumentBuilderFactory documentBuilderFactory =  DocumentBuilderFactory.newInstance();
       DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

       Document document = documentBuilder.newDocument();

       for (int r = 0; r < list.size() ; r++) {



           Element element  = document.createElement("Employee");
           document.appendChild(element);

           Attr attr = document.createAttribute("Id");
           attr.setValue(list.get(r).getId().toString());
           element.setAttributeNode(attr);

           Element name  = document.createElement("name");
           name.appendChild(document.createTextNode(list.get(r).getName()));
           element.appendChild(name);

           Element unions  = document.createElement("unions");
           Attr attr2 = document.createAttribute("Id");
           name.appendChild(document.createTextNode(list.get(r).getName()));
           element.appendChild(unions);

       }






   }



}
