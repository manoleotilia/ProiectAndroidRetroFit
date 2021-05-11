package com.example.proiectandroidretrofit;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

public class ParsareCurs {

    private double curs_euro;

    public double getCurs_euro() {
        return curs_euro;
    }

    public boolean parsare(String xmlData) {
        boolean status = true;
        String valoareText = "";
        boolean EUR_GASIT= false;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("rate".equalsIgnoreCase(tagName)) {
                            if (xmlPullParser.getAttributeValue(null, "currency").equalsIgnoreCase("eur")) {
                                EUR_GASIT=true;

                            }
                        }
                        break;
                    case XmlPullParser.TEXT:
                        valoareText = xmlPullParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(EUR_GASIT)
                        {   EUR_GASIT=false;
                            curs_euro=Double.valueOf(valoareText);
                        }

                }
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            status = false;
            e.printStackTrace();
        } catch (IOException e) {
            status = false;
            e.printStackTrace();
        }
        return status;
    }
}
