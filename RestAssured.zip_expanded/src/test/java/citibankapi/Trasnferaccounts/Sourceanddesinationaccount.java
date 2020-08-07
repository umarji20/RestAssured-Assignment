
package citibankapi.Trasnferaccounts;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sourceanddesinationaccount {

    @SerializedName("sourceAccounts")
    @Expose
    private List<SourceAccount> sourceAccounts = null;
    @SerializedName("destinationSourceAcctCombinations")
    @Expose
    private List<DestinationSourceAcctCombination> destinationSourceAcctCombinations = null;

    public List<SourceAccount> getSourceAccounts() {
        return sourceAccounts;
    }

    public void setSourceAccounts(List<SourceAccount> sourceAccounts) {
        this.sourceAccounts = sourceAccounts;
    }

    public List<DestinationSourceAcctCombination> getDestinationSourceAcctCombinations() {
        return destinationSourceAcctCombinations;
    }

    public void setDestinationSourceAcctCombinations(List<DestinationSourceAcctCombination> destinationSourceAcctCombinations) {
        this.destinationSourceAcctCombinations = destinationSourceAcctCombinations;
    }

}
