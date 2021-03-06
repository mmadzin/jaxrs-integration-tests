package com.redhat.resource.basic;

import com.redhat.resource.basic.resource.CovariantReturnSubresourceLocatorsRootProxy;
import com.redhat.resource.basic.resource.CovariantReturnSubresourceLocatorsSubProxy;
import com.redhat.resource.basic.resource.CovariantReturnSubresourceLocatorsSubProxyRootImpl;
import com.redhat.resource.basic.resource.CovariantReturnSubresourceLocatorsSubProxySubImpl;
import com.redhat.utils.HttpResponseCodes;
import com.redhat.utils.PortProviderUtil;
import com.redhat.utils.TestUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

/**
 * @tpSubChapter Resources
 * @tpChapter Integration tests
 * @tpTestCaseDetails Test return value of covariant with locators.
 * @tpSince RESTEasy 3.0.16
 */
@RunWith(Arquillian.class)
@RunAsClient
public class CovariantReturnSubresourceLocatorsTest {

    @Deployment
    public static Archive<?> deployUriInfoSimpleResource() {
        WebArchive war = TestUtil.prepareArchive(CovariantReturnSubresourceLocatorsTest.class.getSimpleName());
        war.addClasses(CovariantReturnSubresourceLocatorsRootProxy.class, CovariantReturnSubresourceLocatorsSubProxy.class);
        return TestUtil.finishContainerPrepare(war, null, CovariantReturnSubresourceLocatorsSubProxyRootImpl.class,
                CovariantReturnSubresourceLocatorsSubProxySubImpl.class);
    }

    /**
     * @tpTestDetails Test basic path
     * @tpSince RESTEasy 3.0.16
     */
    @Test
    public void basicTest() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(PortProviderUtil.generateURL("/path/sub/xyz",
                CovariantReturnSubresourceLocatorsTest.class.getSimpleName())).request().get();
        Assert.assertEquals(HttpResponseCodes.SC_OK, response.getStatus());
        Assert.assertEquals("Wrong content of response", "Boo! - xyz", response.readEntity(String.class));
        response.close();
        client.close();
    }
}