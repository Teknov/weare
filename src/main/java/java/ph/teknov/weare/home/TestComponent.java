package ph.teknov.weare.home;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Kenneth Mandawe
 *         Date: 11/9/2015
 *         Time: 7:01 AM
 */
@Component
public class TestComponent {

    private String testName = "TEKNOV!";
    private String secondOne = "THis is the second one!";

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getSecondOne() {
        return secondOne;
    }

    public void setSecondOne(String secondOne) {
        this.secondOne = secondOne;
    }

    public String goToHome(){
        return "/home?faces-redirect=true";
    }
}
