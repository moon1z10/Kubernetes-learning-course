package hodlene.k8s.currencyexchange.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InstanceInformationService {

    private static final String HOST_NAME = "HOSTNAME";

    private static final String DEFAULT_ENV_INSTANCE_GUID = "UNKNOWN";

    @Value("${" + HOST_NAME + ":" + DEFAULT_ENV_INSTANCE_GUID + "}")
    private String hostName;

    public String retrieveInstanceInfo() {
        return hostName + " v1 " + hostName.substring(hostName.length()-5);
    }

}