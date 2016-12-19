package bind.kripton76Errors;

import java.util.Map;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType(value="root", allFields=true)
public class BeanAttribute76Map {
	
	@BindXml(xmlType=XmlType.VALUE)
	public Map<String, Byte> valueStringByteMap;
	
}

