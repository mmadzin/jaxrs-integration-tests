package com.redhat.resource.basic;

import com.redhat.resource.basic.resource.MultiInterfaceResLocatorIntf1;
import com.redhat.resource.basic.resource.MultiInterfaceResLocatorIntf2;
import com.redhat.resource.basic.resource.MultiInterfaceResLocatorResource;
import com.redhat.resource.basic.resource.MultiInterfaceResLocatorSubresource;
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
 * @tpSince RESTEasy 3.0.16
 */
@RunWith(Arquillian.class)
@RunAsClient
public class MultiInterfaceResLocatorTest {

    @Deployment
    public static Archive<?> deploy() {
        WebArchive war = TestUtil.prepareArchive(MultiInterfaceResLocatorTest.class.getSimpleName());
        war.addClass(MultiInterfaceResLocatorIntf1.class);
        war.addClass(MultiInterfaceResLocatorIntf2.class);
        return TestUtil.finishContainerPrepare(war, null, MultiInterfaceResLocatorResource.class, MultiInterfaceResLocatorSubresource.class);
    }

    private String generateURL(String path) {
        return PortProviderUtil.generateURL(path, MultiInterfaceResLocatorTest.class.getSimpleName());
    }

    /**
     * @tpTestDetails Test for resource with more interfaces.
     * @tpSince RESTEasy 3.0.16
     */
    @Test
    public void test() throws Exception {
        Client client = ClientBuilder.newClient();
        Response response = client.target(generateURL("/test/hello1/")).request().get();
        String entity = response.readEntity(String.class);
        Assert.assertEquals(HttpResponseCodes.SC_OK, response.getStatus());
        Assert.assertEquals("Wrong content of response", "resourceMethod1", entity);

        response = client.target(generateURL("/test/hello2/")).request().get();
        entity = response.readEntity(String.class);
        Assert.assertEquals(HttpResponseCodes.SC_OK, response.getStatus());
        Assert.assertEquals("Wrong content of response", "resourceMethod2", entity);
    }
}
