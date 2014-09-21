package  com.mongolia.website.controler.json;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import net.sf.sojo.common.WalkerInterceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.json.JsonStringWriter;
import org.springframework.web.servlet.view.json.JsonWriterConfiguratorTemplateRegistry;
import org.springframework.web.servlet.view.json.writer.sojo.MapGraphWalker;
import org.springframework.web.servlet.view.json.writer.sojo.SojoConfig;
import org.springframework.web.servlet.view.json.writer.sojo.SojoJsonWriterConfiguratorTemplate;

public class OwnSojoJsonStringWriter implements JsonStringWriter {

	

	protected final Log logger = LogFactory.getLog(getClass());

	private boolean convertAllMapValues;
	private boolean enableJsonConfigSupport;
	private String keepValueTypesMode;

	public void convertAndWrite(
			Map model,
			JsonWriterConfiguratorTemplateRegistry configuratorTemplateRegistry,
			Writer writer, BindingResult br) throws IOException {

		SojoConfig conf = null;

		SojoJsonWriterConfiguratorTemplate configuratorTemplate = (SojoJsonWriterConfiguratorTemplate) configuratorTemplateRegistry
				.findConfiguratorTemplate(SojoJsonWriterConfiguratorTemplate.class
						.getName());

		if (enableJsonConfigSupport && configuratorTemplate != null) {
			conf = (SojoConfig) configuratorTemplate.getConfigurator();
		}

		if (conf == null)
			conf = new SojoConfig();

		MapGraphWalker walker = new MapGraphWalker();
		walker.getObjectUtil().setWithSimpleKeyMapper(false);
		walker.setIgnoreNullValues(conf.isIgnoreNullValues());
		walker.setExcludedProperties(conf.getExcludedProperties());

		for (WalkerInterceptor interceptor : conf.getInterceptors()) {
			walker.addInterceptor(interceptor);
		}

		OwnJsonViewWalkerInterceptor jsonInterceptor = new OwnJsonViewWalkerInterceptor();
		jsonInterceptor.setIgnoreNullValues(conf.isIgnoreNullValues());
		jsonInterceptor.setConvertAllMapValues(conf.isConvertAllMapValues()
				|| convertAllMapValues);
		if (conf.getKeepValueTypesMode() != null)
			jsonInterceptor.setKeepValueTypesMode(conf.getKeepValueTypesMode());
		else if (keepValueTypesMode != null) {
			jsonInterceptor.setKeepValueTypesMode(keepValueTypesMode);
		} else {
			jsonInterceptor.setKeepValueTypesMode(MODE_KEEP_VALUETYPES_NONE);
		}
		if (br != null) {
			jsonInterceptor.setPropertyEditorRegistry(br
					.getPropertyEditorRegistry());
			jsonInterceptor.setObjectName(br.getObjectName());
			walker.setObjectName(br.getObjectName());
		}

		walker.addInterceptor(jsonInterceptor);

		walker.walk(model);

		if (logger.isDebugEnabled())
			logger.debug(jsonInterceptor.getJsonString());

		writer.write(jsonInterceptor.getJsonString());
	}

	public void setConvertAllMapValues(boolean convertAllMapValues) {
		this.convertAllMapValues = convertAllMapValues;
	}

	public void setEnableJsonConfigSupport(boolean enableJsonConfigSupport) {
		this.enableJsonConfigSupport = enableJsonConfigSupport;
	}

	public void setKeepValueTypesMode(String keepValueTypesMode) {
		this.keepValueTypesMode = keepValueTypesMode;
	}
	
}
