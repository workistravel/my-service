package pl.dernovyi.myservice.converters;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import pl.dernovyi.myservice.models.dao.EmployeeDao;
import pl.dernovyi.myservice.models.dao.UnionDao;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

public class ConvertToPDF {
    private List<EmployeeDao> list;

    public ConvertToPDF(List<EmployeeDao> list) {
        this.list = list;
    }

    public void buildPDF(){
        StringBuffer sb = new StringBuffer();

        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "\n" +
                "<fo:root xmlns:fo=\"http://www.w3.org/1999/XSL/Format\">\n" +
                "\n" +
                "    <fo:layout-master-set>\n" +
                "        <fo:simple-page-master master-name=\"spm\"\n" +
                "                               page-height=\"29.7cm\"\n" +
                "                               page-width=\"21cm\"\n" +
                "                               margin-top=\"1cm\"\n" +
                "                               margin-bottom=\"1cm\"\n" +
                "                               margin-left=\"1cm\"\n" +
                "                               margin-right=\"1cm\">\n" +
                "            <fo:region-body/>\n" +
                "        </fo:simple-page-master>\n" +
                "    </fo:layout-master-set>\n" +
                "\n" +


                "    <fo:page-sequence master-reference=\"spm\">\n" +
                "        <fo:flow flow-name=\"xsl-region-body\">\n" +
                "\n" +
                "            <fo:block font-weight=\"bold\" font-size=\"16pt\" font-family=\"sans-serif\" line-height=\"24pt\"\n" +
                "                      space-after.optimum=\"15pt\" text-align=\"center\" padding-top=\"3pt\">\n" +
                "                Employees in our company\n" +
                "            </fo:block>\n" +
                "\n" +


                "<fo:table table-layout=\"fixed\" width=\"100%\" font-size=\"10pt\" border-color=\"black\" border-width=\"0.3mm\" border-style=\"solid\" >\n" +
                "                <fo:table-column column-width=\"10%\"/>\n" +
                "                <fo:table-column column-width=\"20%\"/>\n" +
                "                <fo:table-column column-width=\"20%\"/>\n" +
                "                <fo:table-column column-width=\"20%\"/>\n" +
                "                <fo:table-column column-width=\"30%\"/>\n" +
                "               <fo:table-body>\n" +
                "                    <fo:table-row>\n" +
                "                        <fo:table-cell>\n" +
                "                            <fo:block font-weight=\"bold\"  font-size=\"10pt\" font-family=\"serif\">\n" +
                "                            Id \n" +
                "                            </fo:block>\n" +
                "                        </fo:table-cell>\n" +
                "\n" +
                "                        <fo:table-cell>\n" +
                "                            <fo:block font-weight=\"bold\" font-size=\"10pt\" font-family=\"serif\">\n" +
                "                               Name\n" +
                "                            </fo:block>\n" +
                "                        </fo:table-cell>\n" +
                "\n" +
                "                        <fo:table-cell>\n" +
                "                            <fo:block font-weight=\"bold\"  font-size=\"10pt\" font-family=\"serif\">\n" +
                "                                Salary:\n" +
                "                            </fo:block>\n" +
                "                        </fo:table-cell>\n" +
                "\n" +
                "                        <fo:table-cell>\n" +
                "                            <fo:block font-weight=\"bold\" font-size=\"10pt\" font-family=\"serif\">\n" +
                "                                Date_empl\n" +
                "                            </fo:block>\n" +
                "                        </fo:table-cell>\n" +
                "\n" +
                "                        <fo:table-cell>\n" +
                "                            <fo:block font-weight=\"bold\" font-size=\"10pt\" font-family=\"serif\">\n" +
                "                                Unions\n" +
                "                            </fo:block>\n" +
                "                        </fo:table-cell>\n" +
                "                    </fo:table-row>\n" +
                "                </fo:table-body>\n" +

                "</fo:table>\n" +
                //  "<fo:table table-layout=\"fixed\" width=\"100%\" border-collapse=\"separate\">\n" +
                "<fo:table table-layout=\"fixed\" width=\"100%\" font-size=\"10pt\" border-color=\"black\" border-width=\"0.3mm\" border-style=\"solid\" >\n" +
                "                <fo:table-column column-width=\"10%\"/>\n" +
                "                <fo:table-column column-width=\"20%\"/>\n" +
                "                <fo:table-column column-width=\"20%\"/>\n" +
                "                <fo:table-column column-width=\"20%\"/>\n" +
               "                <fo:table-column column-width=\"30%\"/>\n"
        );

        for (int i = 0; i <list.size() ; i++) {
            sb.append("                <fo:table-body>\n" +
                    "                    <fo:table-row>\n" +
                    "                        <fo:table-cell>\n" +
                    "                            <fo:block font-weight=\"normal\"  font-size=\"10pt\" font-family=\"serif\">\n" +
                    list.get(i).getId()+"\n" +
                    "                            </fo:block>\n" +
                    "                        </fo:table-cell>\n" +
                    "\n" +
                    "                        <fo:table-cell>\n" +
                    "                            <fo:block font-weight=\"normal\" font-size=\"10pt\" font-family=\"serif\">\n" +
                    list.get(i).getName()+"\n" +
                    "                            </fo:block>\n" +
                    "                        </fo:table-cell>\n" +
                    "\n" );

                    if(list.get(i).getSalary()!=null){
                        sb.append(
                            "    <fo:table-cell>\n" +
                            "           <fo:block font-weight=\"normal\"  font-size=\"10pt\" font-family=\"serif\">\n" +
                            list.get(i).getSalary() +"\n" +
                            "                 </fo:block>\n" +
                            "                        </fo:table-cell>\n" +
                            "\n" );
                    }else{
                        sb.append(
                                "                 <fo:table-cell>\n" +
                                "                 <fo:block font-weight=\"normal\"  font-size=\"10pt\" font-family=\"serif\">\n" +
                                " none \n" +
                                "                  </fo:block>\n" +
                                "                  </fo:table-cell>\n" +
                                "\n" );
                         }
            if(list.get(i).getDateOfEmployment()!=null){
                sb.append(
                                "           <fo:table-cell>\n" +
                                "           <fo:block font-weight=\"normal\"  font-size=\"10pt\" font-family=\"serif\">\n" +
                                list.get(i).getDateOfEmployment() +"\n" +
                                "                 </fo:block>\n" +
                                "                 </fo:table-cell>\n" +
                                "\n" );
            }else {
                sb.append(
                        "                 <fo:table-cell>\n" +
                                "                 <fo:block font-weight=\"normal\"  font-size=\"10pt\" font-family=\"serif\">\n" +
                                " none \n" +
                                "                  </fo:block>\n" +
                                "                  </fo:table-cell>\n" +
                                "\n");
            }
                if (!list.get(i).getUnions().isEmpty()) {
                    Iterator<UnionDao> it = list.get(i).getUnions().iterator();
                    StringBuilder u = new StringBuilder();

                    while(it.hasNext()){
                        UnionDao union = it.next();
                       u.append(union.getId() + "-");
                       u.append(union.getName() +" ");
                    }
                    sb.append(
                            "    <fo:table-cell>\n" +
                                    "           <fo:block font-weight=\"normal\"  font-size=\"10pt\" font-family=\"serif\">\n" +
                                    u.toString()+"\n" +
                                    "                 </fo:block>\n" +
                                    "                 </fo:table-cell>\n" +
                               "\n");

        } else {
                    sb.append(
                            "                 <fo:table-cell>\n" +
                                    "                 <fo:block font-weight=\"normal\"  font-size=\"10pt\" font-family=\"serif\">\n" +
                                    " none \n" +
                                    "                  </fo:block>\n" +
                                    "                  </fo:table-cell>\n" +
                                    "\n");
                }


                sb.append(
                        "          </fo:table-row>\n" +
                        "           </fo:table-body>\n" );

        }

        sb.append("           </fo:table>\n" +
                  "        </fo:flow>\n" +
                  "     </fo:page-sequence>\n" +
                  "  </fo:root>");




        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        try {
            Fop fop = fopFactory.newFop("application/pdf", new FileOutputStream("src/main/resources/result/Import.pdf"));
            Transformer transformer = transformerFactory.newTransformer();

            Source source = new StreamSource(new ByteArrayInputStream(sb.toString().getBytes("UTF8")));
            Result result = new SAXResult(fop.getDefaultHandler());

            transformer.transform(source, result);
        } catch (Exception e) {
            System.err.println("Error of PDf generating: " + e.getLocalizedMessage());
        }
    }

}
