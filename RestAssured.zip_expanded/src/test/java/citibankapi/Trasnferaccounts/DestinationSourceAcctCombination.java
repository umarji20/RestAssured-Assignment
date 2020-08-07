
package citibankapi.Trasnferaccounts;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DestinationSourceAcctCombination {

    @SerializedName("destinationAccountId")
    @Expose
    private String destinationAccountId;
    @SerializedName("displayDestinationAccountNumber")
    @Expose
    private String displayDestinationAccountNumber;
    @SerializedName("destinationAccountCurrencyCode")
    @Expose
    private String destinationAccountCurrencyCode;
    @SerializedName("availableBalance")
    @Expose
    private Double availableBalance;
    @SerializedName("sourceAccountIds")
    @Expose
    private List<SourceAccountId> sourceAccountIds = null;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("accountNickName")
    @Expose
    private String accountNickName;

    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(String destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public String getDisplayDestinationAccountNumber() {
        return displayDestinationAccountNumber;
    }

    public void setDisplayDestinationAccountNumber(String displayDestinationAccountNumber) {
        this.displayDestinationAccountNumber = displayDestinationAccountNumber;
    }

    public String getDestinationAccountCurrencyCode() {
        return destinationAccountCurrencyCode;
    }

    public void setDestinationAccountCurrencyCode(String destinationAccountCurrencyCode) {
        this.destinationAccountCurrencyCode = destinationAccountCurrencyCode;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public List<SourceAccountId> getSourceAccountIds() {
        return sourceAccountIds;
    }

    public void setSourceAccountIds(List<SourceAccountId> sourceAccountIds) {
        this.sourceAccountIds = sourceAccountIds;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAccountNickName() {
        return accountNickName;
    }

    public void setAccountNickName(String accountNickName) {
        this.accountNickName = accountNickName;
    }

}
