import java.io.*;

public class Main {
    public static void main(String[] args) {
        AutomateData data1 = new AutomateData();
        data1.setName("Sandeepa Sadewaka");
        data1.setMassage("What are you doing?");

        FbAutomation obj1 = new FbAutomation();
        obj1.loginDetails();
        obj1.LoginFb();
        obj1.senderName(data1.getName(), obj1.getDriver());
        obj1.message(data1.getMassage(), obj1.getDriver());
        obj1.closeMessageBar(obj1.getDriver());
        obj1.closeWindow();

        AutomateData data2 = new AutomateData();
        data2.setName("Hirantha Dissanayake");
        data2.setMassage("Hello!!");

        FbAutomation obj2 = new FbAutomation();
        obj1.loginDetails();
        obj2.LoginFb();
        obj2.senderName(data2.getName(), obj2.getDriver());
        obj2.message(data2.getMassage(), obj2.getDriver());
        obj2.closeMessageBar(obj2.getDriver());
        obj2.closeWindow();

        AutomateData data3 = new AutomateData();
        data3.setName("Chathura Piyumal");
        data3.setMassage("How Are You?");

        FbAutomation obj3 = new FbAutomation();
        obj1.loginDetails();
        obj3.LoginFb();
        obj3.senderName(data3.getName(), obj3.getDriver());
        obj3.message(data3.getMassage(), obj3.getDriver());
        obj3.closeMessageBar(obj3.getDriver());
        obj3.closeWindow();

        obj1.printResult();
        obj2.printResult();
        obj3.printResult();

    }

}
