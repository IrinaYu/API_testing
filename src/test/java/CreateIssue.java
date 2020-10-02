import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.JiraAPISteps;

import static org.testng.Assert.assertTrue;

public class CreateIssue {

    @Feature("creating")
    @Test
    public void createIssue() {

        Response createIssue = JiraAPISteps.createIssue();
        String keyIssue = createIssue.path("key");
        Response getIssue = JiraAPISteps.getIssue(keyIssue);
        assertTrue(createIssue.path("key").toString().contains("WEBINAR-"));
    }
}