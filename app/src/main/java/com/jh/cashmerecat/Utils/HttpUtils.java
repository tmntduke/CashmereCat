package com.jh.cashmerecat.Utils;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Map;

/**
 * 请求web service
 * Created by tmnt on 2016/12/7.
 */
public class HttpUtils {

    private final static String NAMESPACE = ConfigUtils.NAMESPACE;
    private final static String wsdl = ConfigUtils.WSDL;

    public static String requestWebservice(String method, Map<String, Object> params) {
        String result = "";

        SoapObject soapObject = new SoapObject(NAMESPACE, method);
        if (params != null) {
            for (String key : params.keySet()) {
                soapObject.addProperty(key, params.get(key));
            }
        }

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = soapObject;
        envelope.dotNet = true;
        HttpTransportSE httpTransportSE = new HttpTransportSE(wsdl, 10000);
        try {
            httpTransportSE.call(NAMESPACE + method, envelope);
            if (envelope.getResponse() != null) {
                result = envelope.getResponse().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return result;

    }

}
