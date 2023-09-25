package com.project.LoanApp;

import com.project.LoanApp.entity.Loans;
import com.project.LoanApp.testRepository.TestRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoanAppApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	@Autowired
	private TestRepository repository;
	private static RestTemplate restTemplate;
	@BeforeAll
	public static void initializeRestTemplate(){
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port + "");


	}
	@AfterEach
	void clearDatabase() {
		repository.deleteAll();
	}

	@Test
	public void addLoan() {
		LocalDate duedate= LocalDate.of(2023,12,22);
		Loans la = new Loans("L1","C1","LEN1",10000,5000,duedate ,1,
				duedate,0.01,"");

		Loans response= restTemplate.postForObject(baseUrl+"/loans/add",la,Loans.class);
		Assertions.assertEquals(1,repository.findAll().size());

	}

	@Test
	public void getAllLoan(){
		LocalDate duedate= LocalDate.of(2023,12,22);
		Loans la = new Loans("L1","C1","LEN1",10000,5000,duedate ,1,
				duedate,0.01,"");

		Loans response= restTemplate.postForObject(baseUrl+"/loans/add",la,Loans.class);

		Loans la1 = new Loans("L1","C1","LEN1",10000,5000,duedate ,1,
				duedate,0.01,"");

		Loans response1= restTemplate.postForObject(baseUrl+"/loans/add",la1,Loans.class);
		Assertions.assertEquals(2,repository.findAll().size());
	}

	@Test
	public void getLoanById(){
		LocalDate duedate= LocalDate.of(2023,12,22);
		LocalDate paymentdate = LocalDate.of(2021,12,04);
		Loans la1 = new Loans("L1","C1","LEN1",10000,5000,paymentdate ,1,
				duedate,0.01,"");

		Loans response= restTemplate.postForObject(baseUrl+"/loans/add",la1,Loans.class);
		String s = response.getLoanid();

		Loans getReponse = restTemplate.getForObject(baseUrl+"/loans/loanid/{id}",Loans.class,s);
		Assertions.assertEquals(s,getReponse.getLoanid());
	}

	@Test
	public void getLoanByCustomerId(){
		LocalDate duedate= LocalDate.of(2023,12,22);
		LocalDate paymentdate = LocalDate.of(2021,12,04);
		Loans la1 = new Loans("L1","C1","LEN1",10000,5000,paymentdate ,1,
				duedate,0.01,"");

		Loans response= restTemplate.postForObject(baseUrl+"/loans/add",la1,Loans.class);
		List<Loans> getReponse = restTemplate.getForObject(baseUrl + "/loans/customerid/{id}", List.class, "C1");
		Assertions.assertEquals(1,getReponse.size());
	}

	@Test
	public void getLoanByLenderId(){
		LocalDate duedate= LocalDate.of(2023,12,22);
		LocalDate paymentdate = LocalDate.of(2021,12,04);
		Loans la1 = new Loans("L1","C1","LEN1",10000,5000,paymentdate ,1,
				duedate,0.01,"");

		Loans response= restTemplate.postForObject(baseUrl+"/loans/add",la1,Loans.class);

		List<Loans> getReponse = restTemplate.getForObject(baseUrl + "/loans/lenderid/{lenderid}", List.class, "LEN1");
		Assertions.assertEquals(1,getReponse.size());
	}

	@Test
	public void getAggregateByLender(){
		LocalDate duedate= LocalDate.of(2023,12,22);
		Loans la = new Loans("L1","C1","LEN1",10000,5000,duedate ,1,
				duedate,0.01,"");

		Loans response= restTemplate.postForObject(baseUrl+"/loans/add",la,Loans.class);

		Loans la1 = new Loans("L2","C1","LEN2",10000,5000,duedate ,1,
				duedate,0.01,"");

		Loans response1= restTemplate.postForObject(baseUrl+"/loans/add",la1,Loans.class);

		List<Loans> getresponse = restTemplate.getForObject(baseUrl+"/loans/aggregate/lender",List.class);

		Assertions.assertEquals(2,getresponse.size());
	}

	@Test
	public void getAggregateByCustomer(){
		LocalDate duedate= LocalDate.of(2023,12,22);
		Loans la = new Loans("L1","C1","LEN1",10000,5000,duedate ,1,
				duedate,0.01,"");

		Loans response= restTemplate.postForObject(baseUrl+"/loans/add",la,Loans.class);

		Loans la1 = new Loans("L2","C2","LEN2",10000,5000,duedate ,1,
				duedate,0.01,"");

		Loans response1= restTemplate.postForObject(baseUrl+"/loans/add",la1,Loans.class);

		List<Loans> getresponse = restTemplate.getForObject(baseUrl+"/loans/aggregate/customer",List.class);

		Assertions.assertEquals(2,getresponse.size());
	}

	@Test
	public void getAggregateByInterest(){
		LocalDate duedate= LocalDate.of(2023,12,22);
		Loans la = new Loans("L1","C1","LEN1",10000,5000,duedate ,2,
				duedate,0.01,"");

		Loans response= restTemplate.postForObject(baseUrl+"/loans/add",la,Loans.class);

		Loans la1 = new Loans("L2","C1","LEN2",10000,5000,duedate ,1,
				duedate,0.01,"");

		Loans response1= restTemplate.postForObject(baseUrl+"/loans/add",la1,Loans.class);

		List<Loans> getresponse = restTemplate.getForObject(baseUrl+"/loans/aggregate/interest",List.class);

		Assertions.assertEquals(2,getresponse.size());
	}
}


