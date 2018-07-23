
package webservice;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Company", targetNamespace = "http://WebService/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Company {


    /**
     * 
     * @return
     *     returns java.util.List<webservice.Departments>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "fetchDepartments", targetNamespace = "http://WebService/", className = "webservice.FetchDepartments")
    @ResponseWrapper(localName = "fetchDepartmentsResponse", targetNamespace = "http://WebService/", className = "webservice.FetchDepartmentsResponse")
    @Action(input = "http://WebService/Company/fetchDepartmentsRequest", output = "http://WebService/Company/fetchDepartmentsResponse")
    public List<Departments> fetchDepartments();

    /**
     * 
     * @param departmentID
     * @return
     *     returns java.util.List<webservice.Staff>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "fetchStaff", targetNamespace = "http://WebService/", className = "webservice.FetchStaff")
    @ResponseWrapper(localName = "fetchStaffResponse", targetNamespace = "http://WebService/", className = "webservice.FetchStaffResponse")
    @Action(input = "http://WebService/Company/fetchStaffRequest", output = "http://WebService/Company/fetchStaffResponse")
    public List<Staff> fetchStaff(
        @WebParam(name = "departmentID", targetNamespace = "")
        Integer departmentID);

}
