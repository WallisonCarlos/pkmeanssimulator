package br.com.ppcsimulator.utils.runsimulator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DockerComposeUtils {
	
	static List<String> hosts = new ArrayList<>();
	private static final String NETWORk = "interscsimulator"; 
	private static String CONFIG_PATH = "base_scenario_distributed-";
	public static List<String> getHosts(Integer number) {
		for (Integer i = hosts.size();i < number && hosts.size() < number;i++) {
			hosts.add(UUID.randomUUID().toString()
					.concat("@host#.com")
					.replace("#", Integer.toString(i))
					);
		}
		return hosts;
	}
	
	private static String createServices(Integer number) {
		StringBuilder services = new StringBuilder("");
		services.append("services:\n");
		int i = 0;
		for (String host : getHosts(number)) {
			services.append("\tc"+i+":\n")
				.append("\t\tbuild:\n" + 
						"      \t\t\tcontext: .\n" + 
						"      \t\t\tdockerfile: ./Dockerfile\n" + 
						"      \t\t\tenvironment:\n" + 
						"        \t\t\t\tUSER: 'root'\n" + 
						"        \t\t\t\tCONFIG_PATH: '/src/mock-simulators/smart_city_model/base_scenario_distributed-patition-"+i+"/config.xml'\n")
				.append("\t\tcontainer_name: ").append(host.split("@")[1]).append("\n")
				.append(getCommands(host))
				.append("\t\tvolumes:\n"
						+ "\t\t\t- .:/home/eduardo/volume:/src/mock-simulators/smart_city_model/output/"+i+"\n")
				.append("\t\tnetworks: \n\t\t\t- ".concat(NETWORk))
				.append("\n\n\n");
			i++;
		}
		return services.toString();
	}
	
	private static String getCommands(String host) {
		return new StringBuilder("\t\tcommands: [")
				.append("\"erl -noshell -name "+host+" -setcookie cookie\"")
				.append(", \"\"")
				.append("]")
				.toString();
	}
	
	private static String getEjabberd(Integer number) {
		String ejabberd = "ejabberd:\n"
				+ "\timage: rroemhild/ejabberd "
				+ "\n\tports:"
					+ "\n\t\t- 5222:5222"
					+ "\n\t\t- 5269:5269"
					+ "\n\t\t- 5280:5280"
				+ "\n\tcontainer_name: host"+(number)+".com"
				+ "\n\tenvironment:"
					+ "\n\t\t- ERLANG_NODE=ejabberd@host"+(number)+".com"
					+ "\n\t\t- XMPP_DOMAIN=test.io"
					+ "\n\t\t- ERLANG_COOKIE=cookie"
					+ "\n\t\t- EJABBERD_ADMINS=admin@test.io"
					+ "\n\t\t- EJABBERD_USERS=admin@test.io:admin"
					+ "\n\t\t- EJABBERD_SKIP_MODULES_UPDATE=true"
				+ "\n\tnetworks:"
					+ "\n\t\t- ".concat(NETWORk);
		return ejabberd;
	}
	
	private static String getNetworks() {
		return "\nnetworks:"
					+"\n\t"+NETWORk+":"
				    	+"\n\t\tdriver: bridge";
	}
	
	public static String content(Integer number) {
		StringBuilder content = new StringBuilder("version: '3'\n");
		content.append(createServices(number))
			.append(getEjabberd(number))
			.append(getNetworks());
		return content.toString();
	}
}
