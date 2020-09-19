import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.JiraAPISteps;

public class DeleteIssue {
    @Test
    public void deleteIssue() {

        Response createIssue = JiraAPISteps.createIssue();
        String keyIssue = createIssue.path("key");
        Response deleteIssue = JiraAPISteps.deleteIssue(keyIssue);
        Response checkIfIssueDeleted = JiraAPISteps.checkIfIssueDeleted(keyIssue);
    }
}
