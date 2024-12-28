package repo.ebook;

import java.util.Collection;
import java.util.Collections;

import org.elasticsearch.Version;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.Settings.Builder;
import org.elasticsearch.env.Environment;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.internal.InternalSettingsPreparer;
import org.elasticsearch.plugins.Plugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import repo.ebook.plugin.SerbianPlugin;

@Configuration
public class ElasticSearchConfiguration {

        //load value from application.property
	    @Value("${spring.data.elasticsearch.cluster-name}")
	    private String clusterName;
	    
		@SuppressWarnings("resource")
		public Client nodeClient() {
			
			Builder settings = Settings.settingsBuilder();
			//enable bind on localhost:9200 for node client
            settings.put("node.local", true);
            settings.put("cluster.name", clusterName);
            //expose elasticsearch on http://localhost:9200
            settings.put("http.enabled", true);
            settings.put("path.home", "data");
            settings.put("path.log", "log");
            settings.put("index.analysis.version", "1.0.0");

            //Create node client with plugins, add plugins to built in elsticsearch
            Node node = new NodeWithPlugins(InternalSettingsPreparer.prepareEnvironment(settings.build(), null), Version.CURRENT,
                    Collections.singletonList(SerbianPlugin.class)).start();
			return node.client();
		}
	    
	    @Bean
	    public ElasticsearchTemplate elasticsearchTemplate() {
	        return new ElasticsearchTemplate(nodeClient());
	    }

	    
		private class NodeWithPlugins extends Node {
		    protected NodeWithPlugins(Environment environment, Version version, Collection<Class<? extends Plugin>> list) {
		        super(environment, version, list);
		    }
		}
}
