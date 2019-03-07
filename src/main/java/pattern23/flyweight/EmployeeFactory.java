package pattern23.flyweight;

import java.util.HashMap;
import java.util.Map;

public class EmployeeFactory {
    private static final Map<String, Employee> employee_map = new HashMap<>();

    public  static Manager getManager(String department) {
        Manager manager = (Manager) employee_map.get(department);
        if (null == manager) {
            manager = new Manager(department) ;
            System.out.println("创建部门经理："+department);
            String reportConent = department+"部门汇报，汇报内容为...";
            manager.setReportConent(reportConent);
            System.out.println("创建汇报内容："+reportConent);
            employee_map.put(department,manager);
            return manager;
        }
        return manager;
    }
}
