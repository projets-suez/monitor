package com.predic8.membrane.core.http;

import junit.framework.TestCase;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.DeleteMethod;

import com.predic8.membrane.core.HttpRouter;
import com.predic8.membrane.core.rules.ForwardingRule;
import com.predic8.membrane.core.rules.ForwardingRuleKey;
import com.predic8.membrane.core.rules.Rule;

public class MethodTest extends TestCase {

	private HttpRouter router;
	
	@Override
	protected void setUp() throws Exception {
		Rule rule = new ForwardingRule(new ForwardingRuleKey("localhost", "*", ".*", 4000), "predic8.de", "80");
		router = new HttpRouter();
		router.getRuleManager().addRuleIfNew(rule);
	}
	
	public void testDELETE() throws Exception {
		HttpClient client = new HttpClient();
		
		DeleteMethod delete = new DeleteMethod("http://localhost:4000/aaaa");
		delete.setRequestHeader("Content-Type", "text/xml;charset=UTF-8");
		delete.setRequestHeader("SOAPAction", "");
		
		int status = client.executeMethod(delete);
		System.out.println(status);
	}
		
	@Override
	protected void tearDown() throws Exception {
		router.getTransport().closeAll();
	}

}