package pattern23.flyweight;

public class Manager implements Employee {

    @Override
    public void report() {
        System.out.println(reportConent);
    }
    private  String  department;
    private  String  reportConent;

    public Manager(String department) {
        this.department = department;
    }

    public void setReportConent(String reportConent) {
        this.reportConent = reportConent;
    }
}
