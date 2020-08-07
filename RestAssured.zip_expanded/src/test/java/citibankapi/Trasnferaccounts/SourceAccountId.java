
package citibankapi.Trasnferaccounts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SourceAccountId {

    @SerializedName("sourceAccountId")
    @Expose
    private String sourceAccountId;

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

}
