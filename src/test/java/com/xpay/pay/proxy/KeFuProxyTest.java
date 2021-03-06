package com.xpay.pay.proxy;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xpay.pay.BaseSpringJunitTest;
import com.xpay.pay.proxy.IPaymentProxy.PayChannel;
import com.xpay.pay.proxy.PaymentRequest.GoodsBean;
import com.xpay.pay.proxy.kefu.KeFuProxy;

public class KeFuProxyTest extends BaseSpringJunitTest {
	@Autowired 
	private KeFuProxy proxy;
	
	@Test
	public void testUnifiedOrder() {
		PaymentRequest request = new PaymentRequest();
		request.setExtStoreId("MS0000001694830");
		request.setDeviceId("1213");
		request.setPayChannel(PayChannel.ALIPAY);
		request.setTotalFee(0.01f);
		request.setOrderNo("3116201707311003354995996111");
		request.setSubject("测试商品");
		request.setAttach("atach");
		request.setNotifyUrl("http://106.14.47.193/xpay/notify/kefu");
		GoodsBean[] goods = new GoodsBean[1];
		GoodsBean good = new GoodsBean();
		good.setGoodsId("001");
		good.setGoodsName("电子商品1");
		good.setPrice("1");
		good.setQuantity("1");
		good.setGoodsCategory("电子商品");
		good.setBody("测试商品详情");
		goods[0] = good;
		request.setGoods(goods);
		PaymentResponse response = proxy.unifiedOrder(request);
		System.out.println("response code: "+ response.getCode()+" "+response.getMsg());
	}

	@Test
	public void testQuery() {
		PaymentRequest request = new PaymentRequest();
		request.setExtStoreId("MS0000001694830");
		request.setOrderNo("WX2017082110583442329742355");
		PaymentResponse response = proxy.query(request);
		System.out.println("response code: "+ response.getCode()+" "+response.getMsg());
	}
}
