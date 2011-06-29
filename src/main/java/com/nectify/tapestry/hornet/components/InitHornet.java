package com.nectify.tapestry.hornet.components;

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
 * 
 */
public class InitHornet {

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

	@Parameter(required = true)
	private String channel;

	void beginRender(MarkupWriter writer) {
		writer
				.writeRaw(String
						.format(
								"<script src=\"http://%s:%s/socket.io/socket.io.js\"></script>",
								host, port));
		writer.writeRaw(String.format(
				"<script src=\"http://%s:%s/hornet/hornet.js\"></script>",
				host, port));
	}

	void afterRender(MarkupWriter writer) {
		String token = hornet.createAccessToken(channel);
		support
				.addScript(
						"var hornet = new Hornet('%s', %s, '%s', '%s');hornet.connect();",
						host, port, channel, token);
	}

}