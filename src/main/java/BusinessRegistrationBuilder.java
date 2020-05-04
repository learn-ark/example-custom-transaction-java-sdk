import org.arkecosystem.crypto.transactions.builder.AbstractTransactionBuilder;
import org.arkecosystem.crypto.transactions.types.Transaction;

public class BusinessRegistrationBuilder extends AbstractTransactionBuilder<BusinessRegistrationBuilder> {
    public BusinessRegistrationBuilder(){
        super();
        this.transaction.fee = 5000000000L;
    }

    @Override
    public Transaction getTransactionInstance() {
        return new BusinessRegistration();
    }

    @Override
    public BusinessRegistrationBuilder instance() {
        return this;
    }

    public BusinessRegistrationBuilder businessAsset(String name, String website){
        this.transaction.asset.customAsset.put("name", name);
        this.transaction.asset.customAsset.put("website", website);
        return this;
    }
}
