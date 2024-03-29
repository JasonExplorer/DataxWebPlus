package com.daas.datax.rpc.remoting.net.impl.netty.http.client;

import com.daas.datax.rpc.remoting.net.AbstractClient;
import com.daas.datax.rpc.remoting.net.common.AbstractConnectClient;
import com.daas.datax.rpc.remoting.net.params.XxlRpcRequest;

/**
 * netty_http client
 *
 * @author xuxueli 2015-11-24 22:25:15
 */
public class NettyHttpClient extends AbstractClient {

    private Class<? extends AbstractConnectClient> connectClientImpl = NettyHttpConnectClient.class;

    @Override
    public void asyncSend(String address, XxlRpcRequest xxlRpcRequest) throws Exception {
        AbstractConnectClient.asyncSend(xxlRpcRequest, address, connectClientImpl, xxlRpcReferenceBean);
    }

}
