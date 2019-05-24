package com.jupitertools.springtestredis.customizer;

import java.util.Objects;

/**
 * Created on 2019-05-24
 *
 * @author Korovin Anatoliy
 */
public class RedisContainerDescription {

    private String hostPropertyName;
    private String portPropertyName;

    public String getHostPropertyName() {
        return hostPropertyName;
    }

    public String getPortPropertyName() {
        return portPropertyName;
    }

    public RedisContainerDescription(String hostPropertyName, String portPropertyName) {
        this.hostPropertyName = hostPropertyName;
        this.portPropertyName = portPropertyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        RedisContainerDescription that = (RedisContainerDescription) o;
        return Objects.equals(hostPropertyName, that.hostPropertyName) &&
               Objects.equals(portPropertyName, that.portPropertyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostPropertyName, portPropertyName);
    }
}
