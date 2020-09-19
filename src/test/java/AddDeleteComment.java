import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.JiraAPISteps;
import utils.JiraJSONObjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class AddDeleteComment {

    @Test
    public void addDeleteComment() {

        Response createIssue = JiraAPISteps.createIssue();
        String keyIssue = createIssue.path("key");

        Response getIssue = JiraAPISteps.getIssue(keyIssue);

        Response postComment = JiraAPISteps.postComment(keyIssue);
        String commentId = postComment.path("id");

        Response deleteComment = JiraAPISteps.deleteComment(keyIssue, commentId);

        String getIssueForCheckingComment = JiraAPISteps.getIssueForCheckingComment(keyIssue);
        String emptyComment = new String("[]");
        assertEquals(getIssueForCheckingComment, emptyComment);
        assertNotEquals(getIssueForCheckingComment, JiraJSONObjects.commentJSON());
    }
}

