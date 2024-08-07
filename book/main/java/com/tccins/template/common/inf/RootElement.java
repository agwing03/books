package com.tccins.template.common.inf;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tccins.template.payment.PaymentVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="",propOrder= {"resultCode","resultMsg","item","node2"})
public class RootElement {
	
	@XmlElement(name = "node1")
    private Node1 node1;
	
    @Getter
    @Setter
    @XmlRootElement(name = "node1")
	public static class Node1{
        private String resultCode;
        private String resultMsg;
        private PaymentVO Item;
        
        @XmlElement(name = "node2")
        private Node2 node2;
        
        
        @Getter
        @Setter
        @XmlRootElement(name = "node2")
        public static class Node2{
            private String code;
            @XmlElementWrapper(name = "Items")
            private List<PaymentVO> Items;
        }
    }
	
	/*
	 * @Getter
	 * 
	 * @Setter
	 * 
	 * @XmlRootElement(name = "node2") public static class Node2{ private
	 * List<PaymentVO> Items; }
	 */
   
}
