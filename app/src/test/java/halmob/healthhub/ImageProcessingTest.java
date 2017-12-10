package halmob.healthhub;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by RIDVAN SIRMA on 12/10/2017.
 */
public class ImageProcessingTest {
    @Test
    public void decodeYUV420SPtoRedSumTest() throws Exception {
        byte[] data = new byte[38016];
        int width = 176;
        int height = 144;
        assertEquals(0,ImageProcessing.decodeYUV420SPtoRedAvg(data,width,height));

    }

    @Test
    public void decodeYUV420SPtoRedAvgTest() throws Exception {
        byte[] data = new byte[38016];
        int width = 176;
        int height = 144;

        int sum=0;
    }

}