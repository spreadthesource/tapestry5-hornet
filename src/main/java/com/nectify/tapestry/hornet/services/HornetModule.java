package com.nectify.tapestry.hornet.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.LibraryMapping;

import com.nectify.hornet.Hornet;
import com.nectify.hornet.HornetImpl;

/**
 * Loads Hornet Connector.
 */
public class HornetModule {

	public Hornet buildHornet(
			@Inject @Symbol(HornetSymbols.REDIS_HOST) String host,
			@Inject @Symbol(HornetSymbols.REDIS_PORT) int port,
			@Inject @Symbol(HornetSymbols.TTL) int ttl) {
		return new HornetImpl(host, port, ttl);
	}

	public void contributeFactoryDefaults(
			MappedConfiguration<String, String> configuration) {
		configuration.add(HornetSymbols.REDIS_HOST, "localhost");
		configuration.add(HornetSymbols.REDIS_PORT, "80");
		configuration.add(HornetSymbols.TTL, "120");
		configuration.add(HornetSymbols.HORNET_HOST, "localhost");
		configuration.add(HornetSymbols.HORNET_PORT, "80");
	}

	public static void contributeComponentClassResolver(
			Configuration<LibraryMapping> configuration) {
		configuration.add(new LibraryMapping("hornet",
				"com.nectify.tapestry.hornet"));
	}

}
