package com.tccins.template.invoice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tccins.template.common.LayoutModule;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InvoiceController extends LayoutModule {

	/**
     * 택배 송장 조회 페이지
     * @param model
     * @return
     */
	@RequestMapping("/invoice/inquiry.do")
    public String invoiceInquiry(Model model) {
    	return "invoice/inquiry";
    }
}
