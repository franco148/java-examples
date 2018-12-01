package com.eextreme.tdd.legacycode;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

import com.eextreme.tdd.legacycode.LoanApplication;
import com.eextreme.tdd.legacycode.LoanCalculatorController;
import com.eextreme.tdd.legacycode.LoanRepository;

import static org.mockito.Mockito.*;

/**
 * Spy: Means that you do not actually replace the object, you still use the real underlying object but you
 * stub one of more of its methods.
 * 
 * In other words by using spying we can replace the method of an object but leave the left of the object.
 * It creates an instance of the object, but we are able to override one of its methods.
 * 
 * @author Franco
 *
 */
public class RepaymentAmountTests {
	
	@Spy
	LoanApplication loanApplication;
	
	LoanCalculatorController controller;
	
	@Before
	public void setup() {
		loanApplication = spy(new LoanApplication());
		controller = new LoanCalculatorController();
		
		LoanRepository repository = mock(LoanRepository.class);
		JavaMailSender mailSender = mock(JavaMailSender.class);
		RestTemplate restTemplate = mock(RestTemplate.class);
				
		controller.setData(repository);
		controller.setMailSender(mailSender);
		controller.setRestTemplate(restTemplate);
	}

	@Test
	public void test1YearLoanWholePounds() {
		
		loanApplication.setPrincipal(1200);
		loanApplication.setTermInMonths(12);
		doReturn(new BigDecimal(10)).when(loanApplication).getInterestRate();
				
		controller.processNewLoanApplication(loanApplication);
		
		assertEquals(new BigDecimal(110), loanApplication.getRepayment());
	}
	
	@Test
	public void test2YearLoanWholePounds() {
		
		loanApplication.setPrincipal(1200);
		loanApplication.setTermInMonths(24);
		doReturn(new BigDecimal(10)).when(loanApplication).getInterestRate();
				
		controller.processNewLoanApplication(loanApplication);
		
		assertEquals(new BigDecimal(60), loanApplication.getRepayment());
	}
	
	@Test
	public void test5YearLoanWithRounding() {
		
		loanApplication.setPrincipal(5000);
		loanApplication.setTermInMonths(60);
		doReturn(new BigDecimal(6.5)).when(loanApplication).getInterestRate();
				
		controller.processNewLoanApplication(loanApplication);
		
		assertEquals(new BigDecimal(111), loanApplication.getRepayment());
	}

}
