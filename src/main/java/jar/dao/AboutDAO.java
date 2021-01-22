package jar.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.services.drive.model.About;

import jar.DriveConnection;

public class AboutDAO {
    public static Builder newQuery() {
        return new Builder();
    }

    public static class Builder {
        private List<String> fields = new ArrayList<String>();

        public Map<String, Map<String, Long>> build() throws IOException {
            Map<String, Map<String, Long>> r = new HashMap<>();

            String aux = fields.get(0);
            for (int i = 1; i < fields.size(); i++)
                aux = aux.concat(", " + fields.get(i));

            About ab = DriveConnection.service.about().get().setFields(aux).execute();
            for (String string : fields)
                r.put(string, (Map<String, Long>) ab.get(string));

            return r;
        }

        public Builder getStorageInfo() {
            fields.add("storageQuota");
            return this;
        }

        public Builder getUserInfo() {
            fields.add("user");
            return this;
        }
    }
}
