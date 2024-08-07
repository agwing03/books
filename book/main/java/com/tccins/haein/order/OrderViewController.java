package com.tccins.haein.order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tccins.template.common.LayoutModule;
import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.common.util.CommonDateUtil;
import com.tccins.template.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

/**
 * 주문목록 조회 (view)
 * @author 정주석_tccins
 * @since 2022.09.21
 * @version 1.0
 * @see
 *
 *  수정일           수정자                    수정내용             
 *  -----------    --------    ---------------------------
 *  2022.09.21     정주석                   최초 생성
 * 
 */

@Controller
@RequiredArgsConstructor
public class OrderViewController extends LayoutModule{

	 private final OrderService orderService;

	/**
	 * 주문목록 목록 페이지
	 * @param req
	 * @param commandMap
	 * @param model 
	 * @return
	 * @throws Exception
	 */
    @GetMapping("/order/orderList.do")
	public String orderList( HttpServletRequest req
							,@ModelAttribute("params") SearchDto params
							,Model model) throws Exception 
	{
		//주문 목록 조회
		PagingResponse<OrderVO> response = orderService.selectOrderList(params);
		model.addAttribute("response", response);
		
		//초기날짜
		if(params.getSrchBgnDtm() == null) {
			String today = CommonDateUtil.getToday(); 
			String bgnDt = CommonDateUtil.addMonth(today, -1);   
			
			params.setSrchEndDtm(CommonDateUtil.formatDate(today, "-"));
			params.setSrchBgnDtm(CommonDateUtil.formatDate(bgnDt, "-"));
		}
		
        model.addAttribute("params", params);
        
		return "haein/order/orderList";
	}

}
