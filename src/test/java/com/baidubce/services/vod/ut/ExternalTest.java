package com.baidubce.services.vod.ut;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vod.VodClient;
import com.baidubce.services.vod.model.CreateMediaResourceResponse;
import com.baidubce.services.vod.model.PlayableUrl;
import com.baidubce.services.vod.model.PlaybackCode;

public class ExternalTest {
    private static Logger logger = LoggerFactory.getLogger(ExternalTest.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRegion() throws FileNotFoundException, InterruptedException {
        // external account
        String AK = "9ea040b91e974376a28367f23721e6fb";
        String SK = "0b555564eefa453bbc5b2305db047c23";

        DefaultBceCredentials cred = new DefaultBceCredentials(AK, SK);
        BceClientConfiguration vodConfig = new BceClientConfiguration().withCredentials(cred);
        VodClient vodClient = new VodClient(vodConfig);
        
        logger.info(vodClient.getEndpoint().toString());
        
    }
    
    @Test
    @Ignore
    public void testSample() throws FileNotFoundException, InterruptedException {
        // external account
        String AK = "9ea040b91e974376a28367f23721e6fb";
        String SK = "0b555564eefa453bbc5b2305db047c23";

        DefaultBceCredentials cred = new DefaultBceCredentials(AK, SK);
        BceClientConfiguration vodConfig = new BceClientConfiguration().withCredentials(cred);
        VodClient vodClient = new VodClient(vodConfig);
        
        // create a media ID
        File file = new File("/Users/hdp/Movies/GailSophicha.mp4");
        String title = "Gail Sophicha";
        String description = "Back To December";
        String mediaId = vodClient.createMediaResource(title, description, file).getMediaId();
        logger.info("[meidaId] = " + mediaId);

        // get its attribute
        String status;
        do {
            Thread.sleep(2000);
            status = vodClient.getMediaResource(mediaId).getStatus();
            logger.info("[status] = " + status);
        } while (!status.equals("PUBLISHED"));
        logger.info("[mediaId] = " + vodClient.getMediaResource(mediaId));

        // get playable URL and web player code
        PlayableUrl result = vodClient.getPlayableUrl(mediaId).getResult();
        logger.info("[url] = " + result.getFile() + ", [cover] = " + result.getCover());
        
        List<PlaybackCode> codes = vodClient.getPlayerCode(mediaId, 250, 160, true).getCodes();
        for (int i = 0; i < codes.size(); i++) {
            logger.info("[codeType] = " + codes.get(i).getCodeType() + ", [source] = " + codes.get(i).getSourceCode());
        }

        String newTitle = "New Title";
        String newDesc = "New Description";
        vodClient.updateMediaResource(mediaId, newTitle, newDesc);
        logger.info("After update attributes: " + vodClient.getMediaResource(mediaId));

        vodClient.updateMediaResource(mediaId, title, description);
        
        // disable and publish again
        vodClient.stopMediaResource(mediaId);
        assertEquals("DISABLED", vodClient.getMediaResource(mediaId).getStatus());
        
        vodClient.publishMediaResource(mediaId);
        assertEquals("PUBLISHED", vodClient.getMediaResource(mediaId).getStatus());
        
        vodClient.deleteMediaResource(mediaId);
        try {
            status = vodClient.getMediaResource(mediaId).getStatus();
            logger.info("[status] = " + status);
            fail("Failed to delete resource " + mediaId);
        } catch (Exception e) {
            // Should throw out exception here

        }
    }

    @Test
    @Ignore
    public void testCreatMediaResource() throws InterruptedException {
        String sourceBucket = "bce-media-sdk";
        String sourceKey = "GailSophicha.mp4";
        
        // external account
        final String AK = "9ea040b91e974376a28367f23721e6fb";
        final String SK = "0b555564eefa453bbc5b2305db047c23";

        DefaultBceCredentials cred = new DefaultBceCredentials(AK, SK);
        BceClientConfiguration vodConfig = new BceClientConfiguration().withCredentials(cred);
        VodClient vodClient = new VodClient(vodConfig);

        String title = "GailSophicha";
        String description = "";
        CreateMediaResourceResponse response =
                vodClient.createMediaResource(sourceBucket, sourceKey, title, description);
        String mediaId  = response.getMediaId();

        // make sure it is published
        String status;
        do {
            Thread.sleep(2000);
            status = vodClient.getMediaResource(mediaId).getStatus();
            logger.info("[status] = " + status);
        } while (!status.equals("PUBLISHED"));
        logger.info("[mediaId] = " + vodClient.getMediaResource(mediaId));
        
    }
}
