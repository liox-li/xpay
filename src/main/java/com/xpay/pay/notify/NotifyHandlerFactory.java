package com.xpay.pay.notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpay.pay.model.StoreChannel.PaymentGateway;

@Service
public class NotifyHandlerFactory {

	@Autowired
	ChinaUmsNotifyHandler chinaUmsHandler;
	@Autowired
	ChinaUmsH5NotifyHandler chinaUmsH5Handler;
	@Autowired
	JuZhenNotifyHandler juZhenHandler;
	@Autowired
	KeFuNotifyHandler keFuHandler;
	@Autowired
	KekePayNotifyHandler keKeHandler;
	@Autowired
	MiaoFuNotifyHandler miaoFuHandler;
	@Autowired
	UPayNotifyHandler uPayHandler;
	
	public INotifyHandler getNotifyHandler(String uri) {
		if (uri.contains(PaymentGateway.CHINAUMSH5.name().toLowerCase()) || uri.contains(PaymentGateway.CHINAUMSWAP.name().toLowerCase())
				|| uri.contains(PaymentGateway.CHINAUMSALIPAY.name().toLowerCase())) {
			return chinaUmsH5Handler;
		} else if (uri.contains(PaymentGateway.CHINAUMS.name().toLowerCase())) {
			return chinaUmsHandler;
		} else if (uri.contains(PaymentGateway.UPAY.name().toLowerCase())) {
			return uPayHandler;
		} else if (uri.contains(PaymentGateway.JUZHEN.name().toLowerCase())) {
			return juZhenHandler;
		}  else if (uri.contains(PaymentGateway.KEKEPAY.name().toLowerCase())) {
			return keKeHandler;
		} else if (uri.contains(PaymentGateway.KEFU.name().toLowerCase())) {
			return keFuHandler;
		} else if (uri.contains(PaymentGateway.MIAOFU.name().toLowerCase())) {
			return miaoFuHandler;
		} else {
			return null;
		}
	}
}
