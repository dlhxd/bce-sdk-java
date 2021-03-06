package com.baidubce.services.ses;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.conf.SesConf;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;
import com.baidubce.services.ses.model.VerifyEmailRequest;

@RunWith(Parameterized.class)
public class SesClientVerifyEmailAbnorQaTest {
	private SesClient client;

	@Parameter(0)
	public String inEmailAddr;
	@Parameter(1)
	public String expException;
	@Parameter(2)
	public String expMessage;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays
				.asList(new Object[][] {
						{ null, "java.lang.IllegalArgumentException",
								"object emailAddress should not be null or empty" },
						{ "", "java.lang.IllegalArgumentException",
								"object emailAddress should not be null or empty" },
						{ " ", "java.lang.IllegalArgumentException",
								"object emailAddress should not be null or empty" },
						{ "abc", "com.baidubce.BceServiceException",
								"user id or mail addr empty or format error (Status Code: 500; Error Code: 3" },
				});
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() {
		this.client = new SesClient(
				new SesClientConfiguration()
						.withCredentials(
								new DefaultBceCredentials(SesConf.SES_AK,
										SesConf.SES_SK)).withEndpoint(
								SesConf.SES_ENDPOINT));
	}

	@After
	public void tearDown() {
		this.client.shutdown();
	}

	@Test
	public void test() throws ClassNotFoundException, InterruptedException {
		thrown.expect((Class<? extends Throwable>) Class.forName(expException));
		thrown.expectMessage(expMessage);

		this.client.verifyEmail(inEmailAddr);
		Thread.sleep(1000);
	}

	@Test
	public void testRequest() throws ClassNotFoundException,
			InterruptedException {
		thrown.expect((Class<? extends Throwable>) Class.forName(expException));
		thrown.expectMessage(expMessage);

		this.client.verifyEmail(new VerifyEmailRequest()
				.withEmailAddress(inEmailAddr));
		Thread.sleep(1000);

	}

}
