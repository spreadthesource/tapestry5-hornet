package com.nectify.tapestry.hornet.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import com.nectify.hornet.Hornet;
import com.nectify.tapestry.hornet.services.HornetSymbols;

/**
 * Include this component in your head section to include hornet script.
 */
public class HornetListener
{

    @Inject
    @Symbol(HornetSymbols.HORNET_URI)
    private String hornetUri;

    @Inject
    private JavaScriptSupport support;

    @Inject
    private Hornet hornet;

    /**
     * The name of channel to open a connection with.
     */
    @Parameter(required = true, defaultPrefix = BindingConstants.PROP)
    private String[] channels;

    /**
     * The hornet connection token
     */
    @Parameter(defaultPrefix = BindingConstants.PROP)
    private String token;

    /**
     * Add JS imports.
     * 
     * @param writer
     */
    void beginRender(MarkupWriter writer)
    {
        support.importJavaScriptLibrary(String.format("%s/hornet/hornet.js", hornetUri));
        
        token = hornet.createAccessToken(channels);
    }

    /**
     * Init hornet client.
     * 
     * @param writer
     */
    void afterRender(MarkupWriter writer)
    {
        String hornetVar = support.allocateClientId("hornet");

        JSONArray channelsAsJson = new JSONArray();
        for (String channel : channels)
        {
            channelsAsJson.put(channel);
        }

        support
                .addScript(
                        "var %s = new Hornet({ uri:'%s', channels: %s, token: '%s' }); hornet.connect(); T5.define('hornet', hornet);",
                        hornetVar,
                        hornetUri,
                        channelsAsJson,
                        token);
    }
}