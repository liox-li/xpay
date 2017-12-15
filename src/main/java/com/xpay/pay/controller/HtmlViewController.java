package com.xpay.pay.controller;

import com.xpay.pay.model.Order;
import com.xpay.pay.model.StoreChannel.PaymentGateway;
import com.xpay.pay.proxy.PaymentRequest;
import com.xpay.pay.proxy.ips.quick.IpsQuickProxy;
import com.xpay.pay.service.OrderService;
import com.xpay.pay.service.PaymentService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sunjian on Date: 2017/12/15 下午2:12
 * Description:
 */
@Controller
@RequestMapping("/h5pay")
public class HtmlViewController {

  @Autowired
  private OrderService orderService;

  @Autowired
  private IpsQuickProxy ipsQuickProxy;

  @Autowired
  private PaymentService paymentService;

  @RequestMapping("/{orderNo}")
  public ModelAndView pay(@PathVariable("orderNo") String orderNo) {
    Order order = orderService.findActiveByOrderNo(orderNo);
    if (order == null) {
      return new ModelAndView("error");
    }
    if (PaymentGateway.IPSQUICK.equals(order.getStoreChannel().getPaymentGateway())) {
      PaymentRequest paymentRequest = paymentService.toPaymentRequest(order);
      String reqParam = ipsQuickProxy.getReqParam(paymentRequest);
      Map<String, String> model = new HashMap<>();
      model.put("pGateWayReq", reqParam);
      return new ModelAndView("ips_quick", model);
    }

    return new ModelAndView("error");
  }
}