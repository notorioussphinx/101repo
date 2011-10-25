package company.beans;

import company.AbstractComponent;
import company.CompanyComponent;
import company.DepartmentComponent;
import company.EmployeeComponent;
import java.io.Serializable;
import java.util.List;
import java.util.Stack;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Tobias
 */
@ManagedBean(name = "companyBean")
@SessionScoped
public class CompanyBean implements Serializable {

    private Integer selectedDepartment;
    private Integer selectedEmployee;
    
    private Stack<Integer> history;
    
    private AbstractComponent component;
    
    public CompanyBean() {
        System.out.println("CompanyBean");
        history = new Stack<Integer>();
        this.component = new CompanyComponent();
    }

    public String getName() {
        return this.component.getName();
    }

    public void setName(String name) {
        this.component.setName(name);
    }

    public String getAddress() {
        return this.component.getAddress();
    }

    public void setAddress(String address) {
        this.component.setAddress(address);
    }

    public double getTotal() {
        return this.component.getTotal();
    }
    
    public void setTotal(double total) {
        this.component.setTotal(total);
    }

    public Integer getSelectedDepartment() {
        return selectedDepartment;
    }
    
    public void setSelectedDepartment(Integer selectedDepartment) {
        this.selectedDepartment = selectedDepartment;
    }

    public Integer getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Integer selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }
    
    public String getManager() {
        return this.component.getManager();
    }
    
    public List<SelectItem> getDepartments() {
        for (SelectItem item : this.component.getDepartments()) {
            System.out.println(item.getLabel());
        }
        return this.component.getDepartments();
    }

    public List<SelectItem> getEmployees() {
        return this.component.getEmployees();
    }
    
    public void save() {
        this.component.save();
    }
    
    public void cut() {
        this.component.cut();
    }
    
    public void navigateToDepartment() {
        if (component instanceof DepartmentComponent) {
            history.push(((DepartmentComponent)component).getId());
        }
        component = new DepartmentComponent(selectedDepartment);
    }
    
    public void navigateToEmployee() {
        history.push(((DepartmentComponent)component).getId());
        component = new EmployeeComponent(selectedEmployee);
    }
    
    public void navigateToManager() {
        history.push(((DepartmentComponent)component).getId());
        component = new EmployeeComponent(((DepartmentComponent)component).getManagerId());
    }
    
    public String back() {
        if (history.isEmpty()) {
            component = new CompanyComponent();
            return "company";
        } else {
            component = new DepartmentComponent(history.pop());
            return "department";
        }
    }
    
    public boolean isBackDisabled() {
        return component instanceof CompanyComponent;
    }
}
