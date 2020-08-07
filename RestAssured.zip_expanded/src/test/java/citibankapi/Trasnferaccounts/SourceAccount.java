
package citibankapi.Trasnferaccounts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SourceAccount {

    @SerializedName("sourceAccountId")
    @Expose
    private String sourceAccountId;
    @SerializedName("displaySourceAccountNumber")
    @Expose
    private String displaySourceAccountNumber;
    @SerializedName("sourceAccountCurrencyCode")
    @Expose
    private String sourceAccountCurrencyCode;
    @SerializedName("accountGroup")
    @Expose
    private String accountGroup;
    @SerializedName("availableBalance")
    @Expose
    private Double availableBalance;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("accountNickName")
    @Expose
    private String accountNickName;
    @SerializedName("nextPaymentAmount")
    @Expose
    private Integer nextPaymentAmount;

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getDisplaySourceAccountNumber() {
        return displaySourceAccountNumber;
    }

    public void setDisplaySourceAccountNumber(String displaySourceAccountNumber) {
        this.displaySourceAccountNumber = displaySourceAccountNumber;
    }

    public String getSourceAccountCurrencyCode() {
        return sourceAccountCurrencyCode;
    }

    public void setSourceAccountCurrencyCode(String sourceAccountCurrencyCode) {
        this.sourceAccountCurrencyCode = sourceAccountCurrencyCode;
    }

    public String getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(String accountGroup) {
        this.accountGroup = accountGroup;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
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

    public Integer getNextPaymentAmount() {
        return nextPaymentAmount;
    }

    public void setNextPaymentAmount(Integer nextPaymentAmount) {
        this.nextPaymentAmount = nextPaymentAmount;
    }

}
