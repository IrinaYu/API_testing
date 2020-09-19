import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.JiraAPISteps;

public class CreateIssue {

    @Test
    public void createIssue() {

        Response createIssue = JiraAPISteps.createIssue();
        String keyIssue = createIssue.path("key");
        Response getIssue = JiraAPISteps.getIssue(keyIssue);
    }
}