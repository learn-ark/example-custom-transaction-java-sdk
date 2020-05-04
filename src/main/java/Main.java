import com.google.gson.internal.LinkedTreeMap;
import org.arkecosystem.client.Connection;
import org.arkecosystem.crypto.configuration.Network;
import org.arkecosystem.crypto.identities.Address;
import org.arkecosystem.crypto.networks.Testnet;
import org.arkecosystem.crypto.transactions.types.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static long getNonce(Connection connection, String senderWallet) throws IOException {
        return Long.valueOf (((LinkedTreeMap<String, Object>) connection.api().wallets.show(senderWallet).get("data")).get("nonce").toString());
    }

    public static void main(String[] args) throws IOException {
        Network.set(new Testnet());

        HashMap<String, Object> map = new HashMap<>();
        map.put("host", "http://localhost:4003/api/");
        map.put("content-type","application/json");

        Connection connection2 = new Connection(map);

        long nonce = getNonce(connection2, Address.fromPassphrase("clay harbor enemy utility margin pretty hub comic piece aerobic umbrella acquire")) + 1;

        System.out.println(nonce);
        ArrayList<HashMap> payload = new ArrayList<>();

        Transaction transaction = new BusinessRegistrationBuilder()
                .nonce(nonce)
                .businessAsset("google", "https://google.com")
                .sign("clay harbor enemy utility margin pretty hub comic piece aerobic umbrella acquire")
                .transaction;

        payload.add(transaction.toHashMap());
        LinkedTreeMap<String, Object> postResponse = connection2.api().transactions.create(payload);
        System.out.println(postResponse);
    }
}