import org.arkecosystem.crypto.transactions.types.Transaction;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;

public class BusinessRegistration extends Transaction {
    @Override
    public int getTransactionType() {
        return 100;
    }

    @Override
    public int getTransactionTypeGroup() {
        return 1001;
    }

    @Override
    public HashMap<String, Object> assetToHashMap() {
        HashMap<String, Object> asset = new HashMap<>();
        HashMap<String, String> businessData = new HashMap<>();
        businessData.put("name", (String) this.asset.customAsset.get("name"));
        businessData.put("website", (String) this.asset.customAsset.get("website"));
        asset.put("businessData", businessData);
        return asset;
    }

    @Override
    public byte[] serialize() {
        String name = (String) this.asset.customAsset.get("name");
        String website = (String) this.asset.customAsset.get("website");

        byte[] nameBytes = name.getBytes();
        byte[] websiteBytes = website.getBytes();

        ByteBuffer buffer = ByteBuffer.allocate(nameBytes.length + websiteBytes.length + 2);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        buffer.put((byte) nameBytes.length);
        buffer.put(nameBytes);

        buffer.put((byte) websiteBytes.length);
        buffer.put(websiteBytes);
        return buffer.array();
    }

    @Override
    public void deserialize(ByteBuffer byteBuffer) {
        int nameLength = byteBuffer.get();
        byte[] name = new byte[nameLength];
        byteBuffer.get(name);

        int websiteLength = byteBuffer.get();
        byte[] website = new byte[websiteLength];
        byteBuffer.get(website);

        this.asset.customAsset.put("name", new String(name));
        this.asset.customAsset.put("website", new String(website));
    }
}
