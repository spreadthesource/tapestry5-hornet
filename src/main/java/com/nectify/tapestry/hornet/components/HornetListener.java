package com.nectify.tapestry.hornet.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import com.nectify.hornet.Hornet;
import com.nectify.tapestry.hornet.services.HornetSymbols;

/**
 * Include this component in your head section to include hornet script.
 * 
 * @author ccordenier
 */
public class HornetListener
{

    @Inject
    @Symbol(HornetSymbols.HORNET_HOST)
    private String host;

    @Inject
    @Symbol(HornetSymbols.HORNET_PORT)
    private int port;

    @Inject
    private JavaScriptSupport support;

    @Inject
    private Hornet hornet;

    /**
     * The name of channel to open a connection with.
     */
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String channel;

    /**
     * The event to listen to.
     */
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String event;

    /**
     * The javascript handler method to callback on event.
     */
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String handler;

    /**
     * Add JS imports.
     * 
     * @param writer
     */
    void beginRender(MarkupWriter writer)
    {
        support.importJavaScriptLibrary(String.format(
                "http://%s:%s/socket.io/socket.io.js",
                host,
                port));
        support.importJavaScriptLibrary(String.format("http://%s:%s/hornet/hornet.js", host, port));
    }

    /**
     * Init hornet client.
     * 
     * @param writer
     */
    void afterRender(MarkupWriter writer)
    {
        String hornetVar = support.allocateClientId("hornet");
        String token = hornet.createAccessToken(channel);
        support.addScript(
                "var %s = new Hornet('%s', %s, '%s', '%s');hornet.connect();",
                hornetVar,
                host,
                port,
                channel,
                token);
        support.addScript("%s.on('%s', %s);", hornetVar, event, handler);
    }
}