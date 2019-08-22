package com.cwca.service;


import com.cwca.common.pay_invoice.invoice.BillingRequestVo;
import com.cwca.common.pay_invoice.pay.UnifiedPaymentRequestVo;
import com.cwca.vo.ResultVO;

import java.util.List;

/**
 * 调用支付 开票 服务
 *
 * @Author: liforever
 * @Date: 2019/5/15 14:34
 */
public interface PayInvoiceExpressServ {

    ResultVO checkPayState(List<String> applyId);

}

