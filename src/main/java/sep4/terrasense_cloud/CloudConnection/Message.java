package sep4.terrasense_cloud.CloudConnection;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID=1L;
    private String contents;
    public String getContents()
    {
        return contents;
    }
    public void setContents(String contents)
    {
        this.contents=contents;
    }
}
