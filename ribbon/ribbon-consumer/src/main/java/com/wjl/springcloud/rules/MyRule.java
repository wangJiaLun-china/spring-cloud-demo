package com.wjl.springcloud.rules;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author wangJiaLun
 * @date 2022-03-07
 **/
@NoArgsConstructor
public class MyRule extends AbstractLoadBalancerRule implements IRule {

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @Override
    public Server choose(Object key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String uri = request.getServletPath() + "?" + request.getQueryString();
        return route(uri.hashCode(), getLoadBalancer().getAllServers());
    }

    public Server route(int hashId, List<Server> addressList){
        if (CollectionUtils.isEmpty(addressList)) {
            return null;
        }

        TreeMap<Long, Server> address = new TreeMap<>();

        addressList.stream().forEach(e -> {
                    for (int i = 0; i < 8; i++) {
                        long hash = hash(e.getId());
                        address.put(hash, e);
                    }
                }
        );
        long hash = hash(String.valueOf(hashId));
        SortedMap<Long, Server> last = address.tailMap(hash);

        // 当request URL 生成的key大于任意一个服务器对应的hashKey
        // 取address的第一个节点
        if (last.isEmpty()) {
            address.firstEntry().getValue();
        }
        return last.get(last.firstKey());
    }

    public long hash(String key){
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
        md5.update(keyByte);
        byte[] digest = md5.digest();

        long hashCode = ((long) (digest[2] & 0xFF << 16))
                | ((long) (digest[1] & 0xFF << 8))
                | ((long) (digest[0] & 0xFF));
        return hashCode & 0xffffffff;
    }
}
