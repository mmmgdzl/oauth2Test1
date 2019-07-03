package com.mmmgdzl.test.springcloudsecurityauth.service;

import com.mmmgdzl.test.springcloudsecurityauth.mapper.OauthClientDetailsMapper;
import com.mmmgdzl.test.springcloudsecurityauth.pojo.OauthClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailServiceImpl implements ClientDetailsService {

    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OauthClientDetails oauthClientDetails = oauthClientDetailsMapper.selectByPrimaryKey(clientId);

        //客户端不存在
        if(oauthClientDetails == null) {
            throw new ClientRegistrationException(clientId);
        }


        //"password,authorization_code,refresh_token,client_credentials"
        BaseClientDetails client = new BaseClientDetails(clientId, null, oauthClientDetails.getScope(),
                oauthClientDetails.getAuthorizedGrantTypes(), oauthClientDetails.getAuthorities());
        client.setClientSecret(oauthClientDetails.getClientSecret());
        //设置过期时间
        return client;
    }
}
