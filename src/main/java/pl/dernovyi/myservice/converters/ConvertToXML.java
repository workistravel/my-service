package pl.dernovyi.myservice.converters;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.*;
import pl.dernovyi.myservice.models.dto.EmployeeDto;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConvertToXML {
    List<EmployeeDto> list;


    public static void cr_X(String json){
        try{
            JSONPObject jsonpObject = new JSONPObject(json, json);
        }catch (Exception e){
            System.out.println(e);
        }


    }
}
